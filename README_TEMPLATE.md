## Kornea Gradle plugin

Plugin for assisting with Kotlin development, especially dealing with Multi-Platform and Multi-Module projects.

### Setting Up

In `build.gradle.kts`:

```kotlin
plugins {
    id("dev.brella.kornea") version "%VERSION%"
}
```

## Features

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

### Dependency Management

kornea-gradle provides a way to store version information, and then access it later when we're defining dependencies.

To define versions: 
`build.gradle.kts`
```kotlin
defineVersion("ktor", "2.0.0")
defineVersions("ktor" to "2.0.0")
defineVersions {
    ktor("2.0.0")
    version("ktor", "2.0.0")
    "ktor" .. "2.0.0"
}
```

To define a dependency:
`build.gradle.kts`
```kotlin
dependencies {
    //When calling versioned, we pass the spec, then the module name
    implementation(versioned("io.ktor:ktor-core", "ktor"))
    
    //When we call ktorModule, it constructs a spec from `io.ktor:ktor-$module`
    api(ktorModule("core"))
}
```

Version information will be checked locally, then if it does not exist the root project will be queried. 
This allows, in multi-module projects, a higher level of dependency coordination.