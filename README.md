This is a fun game written in Kotlin using Compose for Wasm. You can play it under [https://marcinmoskala.com/ModifierOrderGuesser/](https://marcinmoskala.com/ModifierOrderGuesser/).

To run, use:

```
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

To deploy, use below command and copy the contents of the `composeApp/build/dist/wasmJs/productionExecutable/` dir to `docs/` dir.

```
./gradlew wasmJsBrowserDistribution
```

