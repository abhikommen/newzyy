# Newzyy 📷

[![Build (NewsApi)](https://github.com/Zlagi/blogfy-api/actions/workflows/run-build.yml/badge.svg)](https://github.com/Zlagi/blogfy-api/actions/workflows/run-build.yml)
[![Android Lint](https://github.com/Zlagi/Blogfy/actions/workflows/Lint.yml/badge.svg?branch=testing)](https://github.com/Zlagi/Blogfy/actions/workflows/Lint.yml)
[![Testing](https://github.com/Zlagi/Blogfy/actions/workflows/Testing.yml/badge.svg?branch=testing)](https://github.com/Zlagi/Blogfy/actions/workflows/Testing.yml)
[![Release](https://github.com/Zlagi/Blogfy/actions/workflows/Release.yml/badge.svg?branch=testing)](https://github.com/Zlagi/Blogfy/actions/workflows/Release.yml)

## 💡 About the Project

This fully functional app serves as an example of how to create an Android application utilising Uncle Bob's Clean Architecture methodology.

The trick of the project is to demonstrate best practices, provide a set of guidelines, and present modern Android Application Architecture that is modular, scalable, maintainable and testable, suitable for bigger teams and long application lifecycle management.

***You can Install and test latest Newzyy Android app 👇***

[![Newzyy](https://img.shields.io/badge/Blogfy✅-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/Zlagi/Blogfy/releases)

## 💡 API:

This is our *REST API* built using Ktor Framework deployed on *Heroku*.  
Navigate to the link in below 👇

https://github.com/Zlagi/blogfy-api 🔥

## Features 👓

- [x] Basic authentication (News API)
- [x] Biometric authentication
- [x] Fetch blogs from NewsApi
- [x] Tests (mocks and fakes).

## Screenshots ✨
<div align="center">
  <img src="https://user-images.githubusercontent.com/30040958/198406961-41132141-250b-4db8-9b82-f6eafc12c347.jpg" width="230px" />
  <img src="https://user-images.githubusercontent.com/30040958/198406941-30db3658-287d-4ef2-bfad-51e00731bf40.jpg" width="230px" />
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
- [LeakCanary](https://square.github.io/leakcanary/) - Memory leak detection library for Android.
- [Truth](https://truth.dev/) - Performing assertions in tests.
- [Mockk](https://mockk.io/) - Mocking library for Kotlin.
- [Robolectric](https://mockk.io/) - Robolectric is a framework that brings fast and reliable unit tests to Android. Tests run inside the JVM on your             workstation in seconds.
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.

## Upcoming improvements
- Pagination Support on latest news feed
- Improve code quality.
- Notification on latest news.

## Known issues
- Leaky memory: this issue produced when the user navigate from FeedFragment to BlogDetailFragment before the image loading is complete and vice-versa.

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
