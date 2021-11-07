# HiteshKhandelwal_AlbumApp
## About
*A simple android app that loads Album list from (https://jsonplaceholder.typicode.com/) and stores it in persistence storage (i.e. Room Database). 
*Album list will be always loaded from local database. Remote data (from API) and Local data is always synchronized. 
- A single-activity pattern, using the Navigation component to manage fragment operations.
- MVVM Architecture
- Reactive UIs using LiveData observables and View Binding.
- Handles background tasks, coroutines and RxJava.
- Offline capability.
- Integrated with KT-Lint and sticked to its guidelines.
- Free from errors and crashes.
- Used co-routines wherever needed for making web service calls
- Minimum API level target is 23
- Used constraint layouts for responsive UI's

## Built With
- Kotlin - First class and official programming language for Android development.
- Rx-Java - For composing asynchronous and event-based programs by using observable sequences.
- Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.
- ViewModel - Stores UI-related data that isn't destroyed on UI changes.
- Room - SQLite object mapping library.
- Hilt - Dependency Injection Framework
- Navigation Component - Used for navigation, part of Android Jetpack
- Retrofit - A type-safe HTTP client for Android and Java.
- Gson - used to convert Java Objects into their JSON representation and vice versa.
- Ktlint -Ktlint is a static code analysis tool maintain by Pinterest. Linter and formatter for Kotlin code.

## Future Plans
- Unit & Instrumentation test cases to be added.
- Merge all code coverage reports at single place.
- Android Priority Job Queue or Work Manager can be used for offline storage support
- Reach code coverage atleast 80%.(TDD & BDD)
- Dark Mode theme