package dev.brella.kornea.gradle.settings

import org.gradle.api.initialization.Settings
import java.io.File

fun Settings.includeSubprojects(path: List<String>, dir: File) {
    dir.listFiles(File::isDirectory)?.forEach { projectDir ->
        if (projectDir.name.equals("buildSrc", true)) return@forEach

        val newPath = path + projectDir.name
        if (File(projectDir, "build.gradle").exists() || File(projectDir, "build.gradle.kts").exists()) {
            val pathName = newPath.joinToString(":", prefix = ":")
            val projectName = newPath.joinToString("-", prefix = "${rootProject.name}-")
            include(pathName)
            project(pathName).name = projectName
        }

        includeSubprojects(newPath, projectDir)
    }
}