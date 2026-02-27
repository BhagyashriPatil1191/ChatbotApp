# Chat App

A simple chat application built using Jetpack Compose, Clean Architecture,
StateFlow, Coroutines, and MVVM.
![App Screenshot](images/ChatApp.png)
---
## Project Structure

presentation/
ChatViewModel
ChatScreen

domain/
SendMessageUseCase
ChatRepository (interface)

data/
ChatRepositoryImpl

## 🏗 Architecture

The project follows Clean Architecture with 3 layers:

- Presentation (ViewModel, UI - Compose)
- Domain (Model, UseCases, Repository interface)
- Data (Repository implementation)

---

## 🛠 Tech Stack

- Kotlin
- Jetpack Compose
- Coroutines 
- StateFlow (Used as it is lifecycle agnostic)
- Hilt (Dependency Injection)
- JUnit & Mockito (Testing)

---

## ▶️ How to Build & Run

1. Clone the repository
2. Open in Android Studio Hedgehog or newer
3. Sync Gradle
4. Run on emulator or physical device (API 24+)

## 🧪 Running Tests

To run unit tests:

- Right click test package → Run Tests

## 📦 Key Features

- Send messages
- Auto reply simulation after 5 second
- Auto-scroll Chat view
- Unit tests for Presentation, UseCase & Repository
- Edge cases not handled
- Testing coverage

## Future Improvements

- TBD

## 👤 Author

Bhagyashri Patil

## 📄 License

This project is open-source and available under the MIT License.
