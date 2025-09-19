# OneStore Android Project

This project is configured for modern Android development using the latest Gradle and Android Gradle Plugin (AGP) versions.

---

## 📦 Project Setup

- **Android Gradle Plugin (AGP):** 8.5.2
- **Gradle:** 8.7
- **Java:** 17
- **Compile SDK:** 34
- **Build Tools:** Not explicitly defined (AGP automatically picks the correct version, currently 34.0.0)

---

## ⚙️ Requirements

Before building, ensure you have the following installed:

1. **Java 17**
    - Verify with:
      ```bash
      java -version
      ```
    - Set `JAVA_HOME` to your JDK 17 installation, e.g.
      ```
      C:\Program Files\Java\jdk-17
      ```

2. **Android SDK 34**
    - Install via Android Studio SDK Manager:
        - **Android 14 (API 34) SDK Platform**
        - **Android SDK Build-Tools 34.0.0**

3. **Gradle Wrapper**
    - Already configured in `gradle/wrapper/gradle-wrapper.properties`:
      ```properties
      distributionUrl=https\://services.gradle.org/distributions/gradle-8.7-all.zip
      ```

---

## 🔑 Important Configuration Notes

1. **Namespace**
    - Required by AGP 8+.
    - Defined in `app/build.gradle`:
      ```gradle
      android {
          namespace "com.example.onestore"
      }
      ```

2. **Repositories**
    - `jcenter()` was removed since it is deprecated.
    - Use:
      ```gradle
      repositories {
          google()
          mavenCentral()
      }
      ```

3. **Build Tools**
    - Do **not** specify `buildToolsVersion`. AGP automatically uses a supported version (currently 34.0.0).

---

## 🚀 Building the Project

From the project root:

```bash
./gradlew clean build
```

---

## 🚀 Building Release

From the project root:

```bash
./gradlew assembleRelease
```