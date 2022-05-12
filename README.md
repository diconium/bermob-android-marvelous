# Marvelous Android

 An Android app to explore the Marvel API.

## Setup & Building

### JDK
The Android Studio built-in IDE is not very smart on understanding the kts build scripts.  
It's advisable to change on Preferences -> Build, Execution, Deployment -> Gradle to Temurin.

### Api Key

First, [get an API key](https://developer.marvel.com/signup) for the Marvel API.

Next, save the public and private keys on your `local.properties` file. For example:
```
marvelPublicKey=<key>
marvelPrivateKey=<key>
```
