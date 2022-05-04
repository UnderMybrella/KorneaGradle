package dev.brella.kornea.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

public inline fun ExtensionAware.define(name: String, value: Any?) = extra.set(name, value)
public inline fun ExtensionAware.define(vararg pairs: Pair<String, Any?>) = pairs.forEach { (k, v) -> extra.set(k, v) }

public inline fun ExtensionAware.defineVersion(module: String, version: Any?) = extra.set("${module}_VERSION", version)
public inline fun ExtensionAware.defineVersions(vararg pairs: Pair<String, Any?>) =
    pairs.forEach { (module, version) -> extra.set("${module}_VERSION", version) }

public inline fun Project.versioned(
    spec: String,
    module: String,
    defaultVersion: String? = null
): Dependency {
    val version = rootProject.extra.get("${module}_VERSION") ?: defaultVersion
    return if (version == null) dependencies.create(spec) else dependencies.create("$spec:$version")
}

public inline fun KotlinDependencyHandler.versioned(
    spec: String,
    module: String,
    defaultVersion: String? = null
): Dependency = project.versioned(spec, module, defaultVersion)
