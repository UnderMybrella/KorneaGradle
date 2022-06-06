package dev.brella.kornea.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

context(Project)
public inline fun DependencyHandler.versioned(
    spec: String,
    module: String,
    defaultVersion: String? = null,
): Dependency {
    val version = extra.get("${module}_VERSION") ?: rootProject.extra.get("${module}_VERSION") ?: defaultVersion

    return if (version == null) {
        if (spec.contains(':')) {
            create(spec)
        } else {
            create("$spec:$module")
        }
    } else {
        if (spec.contains(':')) {
            create("$spec:$version")
        } else {
            create("$spec:$module:$version")
        }
    }
}

context(Project)
public inline fun DependencyHandler.ktor(module: String, defaultVersion: String? = null) =
    versioned("io.ktor:ktor-$module", KTOR_MODULE_NAME, defaultVersion)

context(Project)
public inline fun DependencyHandler.kotlinx(
    module: String,
    versionKey: String,
    defaultVersion: String? = null,
) = versioned("org.jetbrains.kotlinx:$module", versionKey)

context(Project)
public inline fun DependencyHandler.kotlinxCoroutines(module: String, defaultVersion: String? = null) =
    kotlinxModule("kotlinx-coroutines-$module", KOTLINX_COROUTINES_MODULE_NAME, defaultVersion)

context(Project)
public inline fun DependencyHandler.kotlinxSerialisation(module: String, defaultVersion: String? = null) =
    kotlinxModule("kotlinx-serialization-$module", KOTLINX_SERIALISATION_MODULE_NAME, defaultVersion)

context(Project)
public inline fun DependencyHandler.brella(
    module: String,
    versionKey: String,
    defaultVersion: String? = null,
) = versioned("dev.brella:$module", versionKey, defaultVersion)

context(Project)
public inline fun DependencyHandler.kornea(module: String, defaultVersion: String? = null) =
    brellaModule("kornea-$module", "kornea-$module", defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaAnnotations(defaultVersion: String? = null) =
    korneaModule("annotations", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaApollo(defaultVersion: String? = null) =
    korneaModule("apollo", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaBase(defaultVersion: String? = null) =
    korneaModule("base", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaConfig(defaultVersion: String? = null) =
    korneaModule("config", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaErrors(defaultVersion: String? = null) =
    korneaModule("errors", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaImg(defaultVersion: String? = null) =
    korneaModule("img", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaIO(defaultVersion: String? = null) =
    korneaModule("io", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaModelling(defaultVersion: String? = null) =
    korneaModule("modelling", defaultVersion = defaultVersion)

context(Project)
public inline fun DependencyHandler.korneaToolkit(defaultVersion: String? = null) =
    korneaModule("toolkit", defaultVersion = defaultVersion)