package dev.brella.kornea.gradle

import org.gradle.api.Project

@KorneaDsl
public inline fun Project.kotlinxSerialisationModules(block: @KorneaDsl KotlinxSerialisationModuleDependencies.() -> Unit) =
    KotlinxSerialisationModuleDependencies(this).block()

@KorneaDsl
public inline fun Project.kotlinxCoroutinesModules(block: @KorneaDsl KotlinxCoroutinesModuleDependencies.() -> Unit) =
    KotlinxCoroutinesModuleDependencies(this).block()

@KorneaDsl
public inline fun Project.customModules(group: String, moduleName: String, block: @KorneaDsl CustomModuleDependencies.() -> Unit) =
    CustomModuleDependencies(this, group, null, moduleName).block()

@KorneaDsl
public inline fun Project.customModules(group: String, baseModule: String?, moduleName: String, block: @KorneaDsl CustomModuleDependencies.() -> Unit) =
    CustomModuleDependencies(this, group, baseModule, moduleName).block()

public abstract class GroupedModuleDependencies(
    val project: Project,
    group: String,
    baseModule: String?,
    val moduleName: String,
) {
    public val baseSpec: String by lazy {
        buildString {
            append(group)
            append(':')
            baseModule?.let(this::append)
        }
    }

    public inline fun of(module: String, defaultVersion: String? = null) =
        project.versioned("${baseSpec}${module}", moduleName, defaultVersion)
}

public class CustomModuleDependencies(project: Project, group: String, baseModule: String?, moduleName: String) :
    GroupedModuleDependencies(project, group, baseModule, moduleName)

public class KotlinxSerialisationModuleDependencies(project: Project) : GroupedModuleDependencies(
    project,
    "org.jetbrains.kotlinx",
    "kotlinx-serialization-",
    KOTLINX_SERIALISATION_MODULE_NAME
) {
    public inline fun core(defaultVersion: String? = null) =
        of("core", defaultVersion)

    public inline fun cbor(defaultVersion: String? = null) =
        of("cbor", defaultVersion)

    public inline fun hocon(defaultVersion: String? = null) =
        of("hocon", defaultVersion)

    public inline fun json(defaultVersion: String? = null) =
        of("json", defaultVersion)

    public inline fun properties(defaultVersion: String? = null) =
        of("properties", defaultVersion)

    public inline fun protobuf(defaultVersion: String? = null) =
        of("protobuf", defaultVersion)
}

public class KotlinxCoroutinesModuleDependencies(project: Project) :
    GroupedModuleDependencies(project, "org.jetbrains.kotlinx", "kotlinx-coroutines-", KOTLINX_COROUTINES_MODULE_NAME) {
    public inline fun core(defaultVersion: String? = null) =
        of("core", defaultVersion)

    public inline fun test(defaultVersion: String? = null) =
        of("test", defaultVersion)

    public inline fun debug(defaultVersion: String? = null) =
        of("debug", defaultVersion)

    public inline fun reactive(defaultVersion: String? = null) =
        of("reactive", defaultVersion)

    public inline fun reactor(defaultVersion: String? = null) =
        of("reactor", defaultVersion)

    public inline fun rx2(defaultVersion: String? = null) =
        of("rx2", defaultVersion)

    public inline fun rx3(defaultVersion: String? = null) =
        of("rx3", defaultVersion)

    public inline fun android(defaultVersion: String? = null) =
        of("android", defaultVersion)

    public inline fun javafx(defaultVersion: String? = null) =
        of("javafx", defaultVersion)

    public inline fun swing(defaultVersion: String? = null) =
        of("swing", defaultVersion)

    public inline fun jdk8(defaultVersion: String? = null) =
        of("jdk8", defaultVersion)

    public inline fun guava(defaultVersion: String? = null) =
        of("guava", defaultVersion)

    public inline fun slf4J(defaultVersion: String? = null) =
        of("slf4j", defaultVersion)

    public inline fun playServices(defaultVersion: String? = null) =
        of("play-services", defaultVersion)
}