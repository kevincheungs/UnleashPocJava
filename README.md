# UnleashPocJava

Just run the main.java file.

Explanation of configs
```
UnleashConfig config = UnleashConfig.builder()

  .appName("default") // maps to project name on UI
  
  .instanceId("compy1") // unique instance name for connecting device
  
  .environment("production") // maps to environment name you are connecting to. API key needs to match this.
  
  .unleashAPI("http://localhost:4242/api/") // URL for your unleash instance
  
  .apiKey(apiKey) // API key generated on UI. Needs to match env.
  
  .fallbackStrategy(new CustomStrategy()) // Any custom strategies
  
  .synchronousFetchOnInitialisation(true) // Waits for synch
  
  .build();
```
