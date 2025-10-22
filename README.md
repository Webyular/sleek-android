# sleek-android
## Project Overview
This project is developed as part of the **NIT3213 Mobile Application Development** course.  
The application demonstrates modern Android development practices, including API integration, user interface design, and dependency injection using **Hilt**.

The app consists of three main screens:
1. **Login Screen** – authenticates the user via API.
2. **Dashboard Screen** – displays a list of entities retrieved from the backend.
3. **Details Screen** – shows full details of a selected entity.

Due to the unavailability of the official NIT3213 API, the app integrates a **mock backend using Beeceptor**, which simulates API responses while maintaining realistic network behavior.

## How to Run the Project

### Prerequisites
Before running the app, make sure you have the following installed:
- **Android Studio Ladybug | 2024.2.1** or newer  
- **JDK 17** or later  
- **Android SDK 33 (Android 13)** or higher  
- An **Android Emulator** or physical Android device with internet access  

---

### Steps to Build and Run

**Clone or extract the project**
```bash
git clone <your-repo-link>
```
or download and extract the `.zip` file into your preferred workspace folder.

**Open in Android Studio**
- Launch Android Studio  
- Click **File → Open**  
- Select the project folder (e.g., `s8114326Assignment2/`)

**Sync Gradle**
- Android Studio will automatically detect the Gradle files.  
- If prompted, click **“Sync Now”** to download dependencies.

**Check App Module Settings**
Ensure:
- **Compile SDK Version:** 34  
- **Minimum SDK Version:** 26  
- **Build Gradle Plugin:** 8.1.0 or higher

These are set in `build.gradle` and `gradle.properties`.

**Set Up the Mock API (Beeceptor)**
Since the official `nit3213api.onrender.com` endpoint may be unavailable,  
the app uses a **mock API** hosted on Beeceptor for testing.

#### Beeceptor Configuration
Base URL (inside `NetworkModule.kt`):
```kotlin
private const val BASE_URL = "https://nit3213-footcray.free.beeceptor.com/"
```

Beeceptor endpoints:
- **POST** `/footscray/auth` → returns:
  ```json
  { "keypass": "demoTopic" }
  ```
- **GET** `/dashboard/demoTopic` → returns:
  ```json
  {
    "entities": [
      {
        "property1": "Android Basics",
        "property2": "Lecture 1",
        "description": "Getting started with Android development."
      },
      {
        "property1": "UI Design",
        "property2": "Lecture 3",
        "description": "Understanding layouts and RecyclerView."
      }
    ],
    "entityTotal": 2
  }

You can view live request logs at [Beeceptor Dashboard](https://beeceptor.com)

### Run the App

**Select the app module**  
**Click “Run” ** or press **Shift + F10**  
Choose your emulator or device  

When the app launches:
1. The **Login Screen** appears  
   - Username: `Webster`  
   - Password: `8114326`
2. Tap **Login**  
3. The app will fetch mock data and navigate to the **Dashboard**  
4. Tap any entity to open the **Details Screen**
