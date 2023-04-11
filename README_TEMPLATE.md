## Kornea Gradle plugin

Plugin for assisting with Kotlin development, especially dealing with Multi-Platform and Multi-Module projects.

### Setting Up

In `settings.gradle.kts`:

```kotlin
plugins {
    id("dev.brella.kornea.settings") version "%SETTINGS_VERSION%"
}
```

In `build.gradle.kts`:

```kotlin
plugins {
    id("dev.brella.kornea") version "%PROJECT_VERSION%"
}
```