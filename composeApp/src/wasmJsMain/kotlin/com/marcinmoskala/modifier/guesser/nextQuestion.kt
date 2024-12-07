package com.marcinmoskala.composeexercises.ui.samples.guesser

fun nextQuestion(modifiersNumber: Int): Question {
    val answer = modifiers.shuffled()
        .take(modifiersNumber)
    return Question(
        answer = answer,
        allOptions = answer.permutations()
            .let { if (it.size > MAX_OPTIONS) limitOptions(it, answer) else it }
            .shuffled(),
    )
}

private const val MAX_OPTIONS = 30

fun <T> limitOptions(options: Set<T>, answer: T): Set<T> {
    val optionsWithoutAnswer = options - answer
    return setOf(answer) + optionsWithoutAnswer.shuffled().take(MAX_OPTIONS - 1)
}

fun <T> List<T>.permutations(): Set<List<T>> = when {
    isEmpty() -> setOf()
    size == 1 -> setOf(listOf(get(0)))
    else -> {
        val element = get(0)
        drop(1).permutations()
            .flatMap { sublist -> (0..sublist.size).map { i -> sublist.plusAt(i, element) } }
            .toSet()
    }
}

internal fun <T> List<T>.plusAt(index: Int, element: T): List<T> = when {
    index !in 0..size -> throw Error("Cannot put at index $index because size is $size")
    index == 0 -> listOf(element) + this
    index == size -> this + element
    else -> dropLast(size - index) + element + drop(index)
}