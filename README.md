To run, use:

```
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

To deploy, use below command and copy the contents of the `composeApp/build/dist/wasmJs/productionExecutable/` dir to `docs/` dir.

```
./gradlew wasmJsBrowserDistribution
```

