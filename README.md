# RickAndMortyApp

An Android application that displays characters from the Rick and Morty universe using the [Rick and Morty API](https://rickandmortyapi.com/). The app is built following Clean Architecture principles with modular features and paging support.

## Installation

Clone the repository:

```bash
git clone https://github.com/gibranhit/RickAndMortyApp
```

Open it with the latest stable version of Android Studio (recommended: Hedgehog or newer).

## Features

- List of characters with images, names, and statuses
- Detail screen with full character info and episodes
- Pagination
- Error handling with retry support
- Modularized by feature
- Compose UI with MVI architecture

## Project Structure

The app follows SOLID principles and is structured into the following layers:

1. **UI (Presentation)**
    - Jetpack Compose UI
    - MVI pattern
    - Navigation component
    - Hilt ViewModels

2. **Domain**
    - Use cases and business logic
    - Pure Kotlin module (no Android dependencies)

3. **Data**
    - Repositories implementation
    - Retrofit for network access

4. **Core Modules**
    - Network setup
    - DI setup

## Tech Stack

* [Kotlin](https://kotlinlang.org/)
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit](https://square.github.io/retrofit/)
* [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
* [Coroutines](https://developer.android.com/kotlin/coroutines)

## Demo

Coming soon...

## Author

Héctor Gibran Reyes Álvarez  
[LinkedIn](https://www.linkedin.com/in/gibran-reyes-429992171/)