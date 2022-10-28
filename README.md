# Newzyy 📰



## 💡 About the Project

This fully functional app serves as an example of how to create an Android application utilising Uncle Bob's Clean Architecture methodology.

By bringing Clean Architecture concepts to Android, this repository aims to continue the Clean Architecture movement. It is important to note that the goal is to utilise the characteristics of the Kotlin programming language as well as to draw inspiration from other intriguing methodologies, such as functional programming, and to learn from their successes and failures.
The trick of the project is to demonstrate best practices, provide a set of guidelines, and present modern Android Application Architecture that is modular, scalable, maintainable and testable, suitable for bigger teams and long application lifecycle management.

<img src="https://github.com/ydhnwb/android-clean-architecture/blob/main/docs/clean.png">

***You can Install and test latest Newzyy Android app 👇***

[![Newzyy](https://img.shields.io/badge/Blogfy✅-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/Zlagi/Blogfy/releases)

## 💡 API:

To retrieve the top headlines for a specified source, this application will use NewsApi (BBC news).👇
https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=$API_KEY 🔥

## Features 👓

- [x] Built On JetPack Compose (News API)
- [x] Biometric authentication
- [x] Fetch blogs from NewsApi
- [x] Tests (mocks and fakes).

## Constraints fulfilled :

• The app is written in Kotlin.
• The app is targeting latest android version platform 33.
• The app must is built using Android Studio 3.0+.
• The app supports both portrait and landscape modes without crashing at any time.
• The app is using using Retrofit2+ for HTTP/REST and GSON for JSON.
• The app includes unit tests

## Story 1: When the user launches the application, he should land in a screen where is possible to see top headlines for the specific news source

### Criteria Fulfilled:

• News provider name should be showed as a screen title 2. Headlines are presented in a list format. ✅
• Each cell should present the headline title ✅
• Headlines must be sorted by date ✅
• The user must be able to scroll through the list of headlines ✅
Each cell should present headline image, if available (download and cache it, don’t
bundle it) ✅

## Story 2: When the user taps on a headline, he should be taken to a new screen

### Criteria Fulfilled:

• Tapping on a headline presents a new screen. ✅
• Image, title, description and content should be displayed, if available ✅

## Bonus Story 3: When user opens the application, it should ask for a fingerprint identification, if available

### Criteria Fulfilled:

• If the device has a fingerprint scanner and it’s configured in the device, user should be required to use it when he opens the application ✅
• If the device doesn’t have fingerprint scanner or it’s not configured, then it should open normally ✅

## Bonus Story 4: A new flavor should be created to present news for another source

### Acceptance criteria:**

• User should land in a different news source if running another target ✅
• Headlines should be presented according to the target that was selected ✅

## Product Flavors

There are two flavors that present news for different-different sources. CNN and BBC.


    productFlavors {
        bbcNews {
            // Assigns this product flavor to the "version" flavor dimension.
            // If you are using only one dimension, this property is optional,
            // and the plugin automatically assigns all the module's flavors to
            // that dimension.
            dimension "version"
            applicationIdSuffix ".bbc"
            versionNameSuffix "-bbc"
            buildConfigField "String", "SOURCE", "\"bbc-news\""
            buildConfigField "String", "APP_NAME", "\"BBC News\""
        }
        cnnNews {
            dimension "version"
            applicationIdSuffix ".cnn"
            versionNameSuffix "-cnn"
            buildConfigField "String", "SOURCE", "\"cnn\""
            buildConfigField "String", "APP_NAME", "\"CNN News\""

        }
    }

## Screenshots ✨
<div align="center">
  <img src="https://user-images.githubusercontent.com/30040958/198726843-20d109e5-e37a-4d50-8a82-af0f71ceeec1.jpg" width="230px" />
  <img src="https://user-images.githubusercontent.com/30040958/198406941-30db3658-287d-4ef2-bfad-51e00731bf40.jpg" width="230px" />
  <img src="https://user-images.githubusercontent.com/30040958/198406961-41132141-250b-4db8-9b82-f6eafc12c347.jpg" width="230px" />
  </div>

## Built with 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that               sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust,           testable, and maintainable apps.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) Navigation refers to the interactions that allow users to     navigate across, into, and back out from the different pieces of content within your app.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) -
- [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
- [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Gson](https://github.com/google/gson) - A modern JSON library for Kotlin and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI             components for Android.
- [Truth](https://truth.dev/) - Performing assertions in tests.
- [Mockk](https://mockk.io/) - Mocking library for Kotlin.
- [Splash Screen Api](https://developer.android.com/develop/ui/views/launch/splash-screen/) - Mocking library for Kotlin.
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.

## Upcoming improvements
- Pagination Support on latest news feed
- Improve code quality.
- Notification on latest news.

Local Development
-----------------

Here are some useful Gradle/adb commands for executing this example:

* `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check.
* `./gradlew installDebug` - Install the debug apk on the current connected device.
* `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration).
* `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests.

## License

    Copyright 2022 Abhishek Dhyani.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.