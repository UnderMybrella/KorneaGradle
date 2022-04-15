package dev.brella.kornea.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

public inline fun DependencyHandler.projectFrom(rootName: String?, parent: String, module: String) =
    if (rootName == null) project(":$parent:$parent-$module")
    else project(":$parent:$rootName-$parent-$module")

public inline fun DependencyHandler.projectFrom(parent: String, module: String) =
    projectFrom(null, parent, module)

inline fun Project.defineSourceSet(newName: String, dependsOn: List<String>, noinline includedIn: (String) -> Boolean) =
    project.extensions.getByType<KotlinMultiplatformExtension>()
        .defineSourceSet(newName, dependsOn, includedIn)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: String,
    includedIn: List<String>,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, listOf(dependsOn), { it in includedIn }, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: List<String>,
    includedIn: List<String>,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, dependsOn, { it in includedIn }, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: String,
    vararg includedIn: String,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, listOf(dependsOn), { it in includedIn }, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: List<String>,
    vararg includedIn: String,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, dependsOn, { it in includedIn }, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: String,
    includedIn: String,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, listOf(dependsOn), { includedIn == it }, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: List<String>,
    includedIn: String,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, dependsOn, { includedIn == it }, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: String,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, listOf(dependsOn), null, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: List<String>,
    config: (KotlinSourceSet.() -> Unit)? = null
) =
    defineSourceSet(newName, dependsOn, null, config)

fun KotlinMultiplatformExtension.defineSourceSet(
    newName: String,
    dependsOn: List<String>,
    includedIn: ((String) -> Boolean)? = null,
    config: (KotlinSourceSet.() -> Unit)? = null
) {
    for (suffix in listOf("Main", "Test")) {
        val newSourceSet = sourceSets.maybeCreate("$newName$suffix")
        dependsOn.forEach { dep -> newSourceSet.dependsOn(sourceSets["$dep$suffix"]) }
        sourceSets.forEach { currentSourceSet ->
            val currentName = currentSourceSet.name
            if (currentName.endsWith(suffix)) {
                val prefix = currentName.removeSuffix(suffix)
                if (includedIn?.invoke(prefix) == true) currentSourceSet.dependsOn(newSourceSet)
            }
        }

        config?.invoke(newSourceSet)
    }
}

fun KotlinMultiplatformExtension.addCompilerArgs(vararg compilerArgs: String) =
    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + compilerArgs
            }
        }
    }

inline fun Project.multiplatform(noinline configuration: KotlinMultiplatformExtension.() -> Unit): Unit =
    configure(configuration)