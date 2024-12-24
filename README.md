# 📱 MatinMovie 💚

MatinMovie is an open-source Android movie application built with **Kotlin** and follows the **MVVM (Model-View-ViewModel)** architectural pattern. The app is designed to showcase movies by integrating with **The Movie Database (TMDB)** API.

---

## Features

- **Browse Popular Movies**: Explore the latest trending movies.
- **Detailed Movie Information**: View movie descriptions, ratings, and more.
- **Search Movies**: Quickly find your favorite movies.
- **Elegant Animations**: Integrated with Shimmer and Veil for smooth UI transitions.
- **Offline Support**: Caches data locally using Room Database.

---

## ScreenShots





  splash online           | offline      | 
:-------------------------:|   :-------------------------:|
<img src="https://s8.uupload.ir/files/screenshot_2024-12-23-22-46-59-726_ir.matin.matinfilm_8scs.jpg">| <img src="https://s8.uupload.ir/files/screenshot_2024-12-23-22-47-19-932_ir.matin.matinfilm_min9.jpg"> |

Home                    | Home |
:-------------------------:|:-------------------------:|
  <img src="https://s8.uupload.ir/files/screenshot_2024-12-23-22-48-08-263_ir.matin.matinfilm_20gz.jpg">| <img src="https://s8.uupload.ir/files/screenshot_2024-12-23-22-47-19-932_ir.matin.matinfilm_min9.jpg">| 
  



## Tech Stack

- **Programming Language**: Kotlin
- **Architecture**: MVVM
- **Dependency Injection**: Dagger-Hilt
- **Navigation**: Android Navigation Component
- **Asynchronous Programming**: Kotlin Coroutines
- **JSON Parsing**: Gson
- **Network Requests**: Retrofit and OkHttp Logging Interceptor
- **Database**: Room
- **UI Components**:
  - Glide (for image loading)
  - MaterialRatingBar
  - Shimmer RecyclerView
  - Android Veil (for skeleton loading)

---

## Libraries Used

- **AndroidX Fragment**
- **Navigation Component**
- **Retrofit**
- **Dagger-Hilt**
- **Kotlin Coroutines**
- **Android Lifecycle Components**
- **Gson**
- **Jsoup**
- **OkHttp Logging Interceptor**
- **Room Database**
- **UI Libraries**:
  - **Glide**
  - **Shimmer RecyclerView**
  - **Android Veil**
  - **MaterialRatingBar**

---

## API Integration

MatinMovie integrates with the **TMDB (The Movie Database)** API to fetch movie data.

### Prerequisites

You need a valid API key to run the project.

1. Sign up at [The Movie Database](https://www.themoviedb.org/).
2. Generate your API key from the developer settings.
3. Add the API key to the project configuration.

---

## Getting Started

### Cloning the Repository

```bash
git clone https://github.com/your-username/MatinMovie.git
```

### Running the Project

1. Open the project in Android Studio.
2. Add your TMDB API key in the `gradle.properties` or as a constant in the project.
3. Build and run the app on your device or emulator.

---

## Contributions

Contributions are welcome! If you find a bug or have a feature request, feel free to open an issue or submit a pull request.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

