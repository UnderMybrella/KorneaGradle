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

public const val KTOR_MODULE_NAME: String = "ktor"
public const val KOTLINX_SERIALISATION_MODULE_NAME: String = "kotlinx-serialisation"
public const val KOTLINX_COROUTINES_MODULE_NAME: String = "kotlinx-coroutines"

public const val KORNEA_ANNOTATIONS_MODULE_NAME: String = "kornea-annotations"
public const val KORNEA_APOLLO_MODULE_NAME: String = "kornea-apollo"
public const val KORNEA_BASE_MODULE_NAME: String = "kornea-base"
public const val KORNEA_CONFIG_MODULE_NAME: String = "kornea-config"
public const val KORNEA_ERRORS_MODULE_NAME: String = "kornea-errors"
public const val KORNEA_IMG_MODULE_NAME: String = "kornea-img"
public const val KORNEA_IO_MODULE_NAME: String = "kornea-io"
public const val KORNEA_MODELLING_MODULE_NAME: String = "kornea-modelling"
public const val KORNEA_TOOLKIT_MODULE_NAME: String = "kornea-toolkit"

public inline fun Project.getExtraOrNull(key: String): Any? =
    (if (extra.has(key)) extra[key] else null) ?: if (rootProject.extra.has(key)) rootProject.extra[key] else null

public inline fun ExtensionAware.define(name: String, value: Any?) = extra.set(name, value)
public inline fun ExtensionAware.define(vararg pairs: Pair<String, Any?>) = pairs.forEach { (k, v) -> extra.set(k, v) }

public inline fun ExtensionAware.defineVersion(module: String, version: Any?) = extra.set("${module}_VERSION", version)
public inline fun ExtensionAware.defineVersions(vararg pairs: Pair<String, Any?>) =
    pairs.forEach { (module, version) -> extra.set("${module}_VERSION", version) }
public inline fun ExtensionAware.defineVersions(block: VersionDefiner.() -> Unit) =
    VersionDefiner(extra).block()

class VersionDefiner(val backing: ExtraPropertiesExtension) {
    public inline fun version(module: String, version: Any?) =
        backing.set(module, version)

    public inline fun ktor(version: Any?) =
        version(KTOR_MODULE_NAME, version)

    public inline fun kotlinxSerialisation(version: Any?) =
        version(KOTLINX_SERIALISATION_MODULE_NAME, version)

    public inline fun kotlinxCoroutines(version: Any?) =
        version(KOTLINX_COROUTINES_MODULE_NAME, version)

    public inline fun korneaAnnotations(version: Any?) =
        version(KORNEA_ANNOTATIONS_MODULE_NAME, version)

    public inline fun korneaApollo(version: Any?) =
        version(KORNEA_APOLLO_MODULE_NAME, version)

    public inline fun korneaBase(version: Any?) =
        version(KORNEA_BASE_MODULE_NAME, version)

    public inline fun korneaConfig(version: Any?) =
        version(KORNEA_CONFIG_MODULE_NAME, version)

    public inline fun korneaErrors(version: Any?) =
        version(KORNEA_ERRORS_MODULE_NAME, version)

    public inline fun korneaImg(version: Any?) =
        version(KORNEA_IMG_MODULE_NAME, version)

    public inline fun korneaIO(version: Any?) =
        version(KORNEA_IO_MODULE_NAME, version)

    public inline fun korneaModelling(version: Any?) =
        version(KORNEA_MODELLING_MODULE_NAME, version)

    public inline fun korneaToolkit(version: Any?) =
        version(KORNEA_TOOLKIT_MODULE_NAME, version)

    public inline operator fun String.rangeTo(version: Any?) =
        version(this, version)
}

public inline fun Project.versioned(
    spec: String,
    module: String,
    defaultVersion: String? = null,
): Dependency {
    val version = getExtraOrNull("${module}_VERSION") ?:  defaultVersion

    return if (version == null) {
        if (spec.contains(':')) {
            dependencies.create(spec)
        } else {
            dependencies.create("$spec:$module")
        }
    } else {
        if (spec.contains(':')) {
            dependencies.create("$spec:$version")
        } else {
            dependencies.create("$spec:$module:$version")
        }
    }
}

public inline fun KotlinDependencyHandler.versioned(
    spec: String,
    module: String,
    defaultVersion: String? = null,
): Dependency = project.versioned(spec, module, defaultVersion)


/**
 * Creates a dependency on a ktor module with the specification `io.ktor:ktor-[module]`
 */
public inline fun Project.ktorModule(module: String, defaultVersion: String? = null) =
    versioned("io.ktor:ktor-$module", KTOR_MODULE_NAME, defaultVersion)

public inline fun Project.kotlinxModule(module: String, versionKey: String, defaultVersion: String? = null) =
    versioned("org.jetbrains.kotlinx:$module", versionKey)
public inline fun Project.kotlinxCoroutinesModule(module: String, defaultVersion: String? = null) =
    kotlinxModule("kotlinx-coroutines-$module", KOTLINX_COROUTINES_MODULE_NAME, defaultVersion)
public inline fun Project.kotlinxSerialisationModule(module: String, defaultVersion: String? = null) =
    kotlinxModule("kotlinx-serialization-$module", KOTLINX_SERIALISATION_MODULE_NAME, defaultVersion)

public inline fun Project.brellaModule(module: String, versionKey: String, defaultVersion: String? = null) =
    versioned("dev.brella:$module", versionKey, defaultVersion)
public inline fun Project.korneaModule(module: String, defaultVersion: String? = null) =
    brellaModule("kornea-$module", "kornea-$module", defaultVersion)
public inline fun Project.korneaAnnotationsModule(defaultVersion: String? = null) =
    korneaModule("annotations", defaultVersion = defaultVersion)
public inline fun Project.korneaApolloModule(defaultVersion: String? = null) =
    korneaModule("apollo", defaultVersion = defaultVersion)
public inline fun Project.korneaBaseModule(defaultVersion: String? = null) =
    korneaModule("base", defaultVersion = defaultVersion)
public inline fun Project.korneaConfigModule(defaultVersion: String? = null) =
    korneaModule("config", defaultVersion = defaultVersion)
public inline fun Project.korneaErrorsModule(defaultVersion: String? = null) =
    korneaModule("errors", defaultVersion = defaultVersion)
public inline fun Project.korneaImgModule(defaultVersion: String? = null) =
    korneaModule("img", defaultVersion = defaultVersion)
public inline fun Project.korneaIOModule(defaultVersion: String? = null) =
    korneaModule("io", defaultVersion = defaultVersion)
public inline fun Project.korneaModellingModule(defaultVersion: String? = null) =
    korneaModule("modelling", defaultVersion = defaultVersion)
public inline fun Project.korneaToolkitModule(defaultVersion: String? = null) =
    korneaModule("toolkit", defaultVersion = defaultVersion)



public inline fun KotlinDependencyHandler.ktorModule(module: String, defaultVersion: String? = null) =
    versioned("io.ktor:$module", KTOR_MODULE_NAME, defaultVersion)

public inline fun KotlinDependencyHandler.kotlinxModule(module: String, versionKey: String, defaultVersion: String? = null) =
    versioned("org.jetbrains.kotlinx:$module", versionKey)
public inline fun KotlinDependencyHandler.kotlinxCoroutinesModule(module: String, defaultVersion: String? = null) =
    kotlinxModule(module, KOTLINX_SERIALISATION_MODULE_NAME, defaultVersion)
public inline fun KotlinDependencyHandler.kotlinxSerialisationModule(module: String, defaultVersion: String? = null) =
    kotlinxModule(module, KOTLINX_SERIALISATION_MODULE_NAME, defaultVersion)

public inline fun KotlinDependencyHandler.brellaModule(module: String, versionKey: String, defaultVersion: String? = null) =
    versioned("dev.brella:$module", versionKey, defaultVersion)
public inline fun KotlinDependencyHandler.korneaModule(module: String, defaultVersion: String? = null) =
    brellaModule("kornea-$module", "kornea-$module", defaultVersion)
public inline fun KotlinDependencyHandler.korneaAnnotationsModule(defaultVersion: String? = null) =
    korneaModule("annotations", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaApolloModule(defaultVersion: String? = null) =
    korneaModule("apollo", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaBaseModule(defaultVersion: String? = null) =
    korneaModule("base", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaConfigModule(defaultVersion: String? = null) =
    korneaModule("config", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaErrorsModule(defaultVersion: String? = null) =
    korneaModule("errors", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaImgModule(defaultVersion: String? = null) =
    korneaModule("img", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaIOModule(defaultVersion: String? = null) =
    korneaModule("io", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaModellingModule(defaultVersion: String? = null) =
    korneaModule("modelling", defaultVersion = defaultVersion)
public inline fun KotlinDependencyHandler.korneaToolkitModule(defaultVersion: String? = null) =
    korneaModule("toolkit", defaultVersion = defaultVersion)