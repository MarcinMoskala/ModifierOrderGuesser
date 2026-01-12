package com.marcinmoskala.composeexercises.ui.samples.guesser

import com.marcinmoskala.modifier.guesser.trackEvent

sealed interface GameState
data object Start : GameState
data class Playing(val difficulty: Difficulty, val question: Question, val score: Int, val livesUsed: Int, val livesLeft: Int) : GameState
data class GameOver(val score: Int, val difficulty: Difficulty) : GameState

enum class Difficulty(val modifiers: Int, val possibilities: Int, val weight: Double) {
    Easy(2, 2, 1.0),
    Medium(3, 4, 2.0),
    Hard(4, 6, 3.0),
    Extreme(6, 10, 4.0),
}

fun start(difficulty: Difficulty): GameState {
    trackEvent("level_start", mapOf("difficulty" to difficulty.name))
    return Playing(
        difficulty = difficulty,
        question = nextQuestion(difficulty.modifiers),
        livesUsed = 0,
        livesLeft = 3,
        score = 0,
    )
}

fun onAnswerGiven(state: Playing, answer: List<ModifierOption>, question: Question): GameState {
    val correct = answer == question.answer
    trackEvent(
        "answer_given", mapOf(
            "difficulty" to state.difficulty.name,
            "correct" to correct.toString(),
            "score" to state.score.toString()
        )
    )
    val livesLeft = state.livesLeft - if (correct) 0 else 1
    val livesUsed = state.livesUsed + if (correct) 0 else 1
    if (livesLeft <= 0) {
        trackEvent("game_over", mapOf("difficulty" to state.difficulty.name, "score" to state.score.toString()))
        return GameOver(state.score, state.difficulty)
    }

    return Playing(
        difficulty = state.difficulty,
        question = nextQuestion(question.answer.size),
        livesUsed = livesUsed,
        livesLeft = livesLeft,
        score = state.score + if (correct) 1 else 0,
    )
}

fun onSkip(state: Playing): GameState {
    trackEvent("question_skipped", mapOf("difficulty" to state.difficulty.name))
    return state.copy(
        question = nextQuestion(state.question.answer.size),
    )
}