# Shopping Management App

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An Android shopping-list manager with user authentication. Users register and log in with a username and password, then manage a personal shopping list — adding products with quantities and removing them — with a Hebrew-language UI.

## Screenshots

| Login | Shopping List | Register |
|---|---|---|
| ![Login](screenshots/login.png) | ![Shopping List](screenshots/shopping_list.png) | ![Register](screenshots/register.png) |

## Features

- **User Registration** — Create a new account; credentials are persisted via `UserRepository`
- **Login** — Authenticate with existing credentials; navigate between Login and Register screens
- **Shopping List Management** — Add products by name and quantity; adding an existing product accumulates its quantity
- **Remove Items** — Delete individual products from the list with a single tap
- **Session Logout** — Clears the current user and shopping list and returns to the Login screen
- **Hebrew UI** — All labels and buttons are in Hebrew ("ברוך הבא", "הוסף", "הסר", "התנתק")

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose (Material 3) |
| State management | `remember` / `mutableStateOf` |
| Data persistence | `UserRepository` (SharedPreferences-based) |
| Navigation | Composable state machine (`login` / `register` / `main`) |
| Build system | Gradle with Kotlin DSL + Version Catalog |
| Min SDK | 24 |
| Target / Compile SDK | 34 |
| AGP | 8.6.0 |
| Kotlin | 1.9.0 |

## Project Structure

```
ShoppingManagementApp/
├── app/
│   └── src/main/
│       ├── java/com/example/shoppingmanagementapp/
│       │   ├── MainActivity.kt              # Entry point; AppContent navigation host
│       │   ├── data/
│       │   │   └── UserRepository.kt        # User persistence layer
│       │   └── ui/
│       │       ├── screens/
│       │       │   ├── LoginScreen.kt       # Login form
│       │       │   ├── RegisterScreen.kt    # Registration form
│       │       │   └── MainScreen.kt        # Shopping list UI
│       │       └── theme/                   # Material 3 theme
│       └── AndroidManifest.xml
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
└── settings.gradle.kts
```

## Screen Flow

```
LoginScreen  ──(success)──►  MainScreen  ──(logout)──►  LoginScreen
     │
 (register)
     ▼
RegisterScreen  ──(done)──►  LoginScreen
```

## How to Build

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- Android SDK with API level 34 platform installed
- JDK 17+

### Steps

1. Clone the repository and open the `ShoppingManagementApp` folder in Android Studio.
2. Allow Gradle to sync and download all dependencies.
3. Connect a device or start an AVD (API 24+).
4. Click **Run > Run 'app'** or press `Shift+F10`.

### Command-line build

```bash
./gradlew assembleDebug
```

Output APK: `app/build/outputs/apk/debug/app-debug.apk`

---

## 🇮🇱 תיעוד בעברית

### מה הפרויקט עושה

אפליקציית Android לניהול רשימת קניות אישית עם מערכת התחברות מלאה. המשתמש נרשם ומתחבר עם שם משתמש וסיסמה, ולאחר מכן יכול לנהל רשימת קניות — להוסיף מוצרים עם כמויות, לעדכן כמות קיימת (הוספת מוצר שכבר קיים מצטברת לכמות שלו), ולהסיר פריטים בלחיצה אחת. כל ממשק המשתמש כתוב בעברית.

### טכנולוגיות

| שכבה | טכנולוגיה |
|---|---|
| שפת תכנות | Kotlin |
| ממשק משתמש | Jetpack Compose (Material 3) |
| ניהול מצב | `remember` / `mutableStateOf` |
| שמירת נתונים | `UserRepository` מבוסס SharedPreferences |
| ניווט בין מסכים | מכונת מצבים מבוססת Composable |
| מערכת בנייה | Gradle עם Kotlin DSL + Version Catalog |
| גרסת SDK מינימלית | 24 (Android 7.0) |
| גרסת SDK יעד | 34 (Android 14) |

### הוראות התקנה והפעלה

**דרישות מקדימות:**
- Android Studio Hedgehog (2023.1.1) ומעלה
- Android SDK עם API רמה 34
- JDK 17 ומעלה

**שלבי הרצה ב-Android Studio:**
1. שכפל את הריפוזיטורי ופתח את תיקיית `ShoppingManagementApp` ב-Android Studio.
2. המתן לסיום סנכרון Gradle והורדת כל התלויות.
3. חבר מכשיר Android (API 24+) או צור AVD (אמולטור).
4. לחץ על **Run > Run 'app'** או הקש `Shift+F10`.

**בנייה משורת פקודה:**
```bash
./gradlew assembleDebug
```
קובץ ה-APK ייווצר בנתיב: `app/build/outputs/apk/debug/app-debug.apk`

### מבנה הפרויקט

```
ShoppingManagementApp/
├── app/
│   └── src/main/
│       ├── java/com/example/shoppingmanagementapp/
│       │   ├── MainActivity.kt              # נקודת כניסה; מארח ניווט AppContent
│       │   ├── data/
│       │   │   └── UserRepository.kt        # שכבת שמירת משתמשים
│       │   └── ui/
│       │       ├── screens/
│       │       │   ├── LoginScreen.kt       # מסך התחברות
│       │       │   ├── RegisterScreen.kt    # מסך הרשמה
│       │       │   └── MainScreen.kt        # ממשק רשימת הקניות
│       │       └── theme/                   # ערכת נושא Material 3
│       └── AndroidManifest.xml
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
└── settings.gradle.kts
```

**זרימת מסכים:**
- **מסך התחברות** — המסך ההתחלתי; ניתן לעבור לרישום או להתחבר
- **מסך הרישום** — יצירת חשבון חדש, לאחריו חזרה להתחברות
- **מסך ראשי** — ניהול רשימת הקניות; התנתקות מחזירה למסך ההתחברות
