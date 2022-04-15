## Kornea Gradle plugin

Plugin for assisting with Kotlin development.

In `settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
        maven(url = "https://maven.brella.dev")
        gradlePluginPortal()
    }
}
```

In `build.gradle.kts`:

```kotlin
plugins {
    id("dev.brella.kornea") version "1.0.1"
}
```

### Multiplatform Source Sets

```kotlin
defineSourceSet("coroutine", dependsOn = "common", includedIn = listOf("jvm", "js")) {
    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLINX_COROUTINES_VERSION")
        implementation("org.jetbrains.kotlinx:atomicfu:$KOTLINX_ATOMICFU_VERSION")
    }
}

defineSourceSet("native", dependsOn = "common")
```

### Multi-Module Builds

In `settings.gradle.kts`:

```kotlin
fun includeSubprojects(path: List<String>, dir: File) {
    dir.listFiles(File::isDirectory)
        ?.forEach { projectDir ->
            if (projectDir.name.equals("buildSrc", true)) return@forEach

            val newPath = path + projectDir.name
            if (File(projectDir, "build.gradle").exists() || File(projectDir, "build.gradle.kts").exists()) {
                val pathName = newPath.joinToString(":", prefix = ":")
                val projectName = newPath.joinToString("-", prefix = "${rootProject.name}-")
                include(pathName)
                project(pathName).name = projectName

                println("Loading $projectName @ $pathName")
            }

            includeSubprojects(newPath, projectDir)
        }
}

includeSubprojects(emptyList(), rootDir)
```

In `build.gradle.kts` for clients:
```kotlin
implementation(projectFrom("analysis", "api"))
implementation(projectFrom("analysis", "spotify"))
```