package dev.brella.kornea.gradle

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler

public inline fun RepositoryHandler.mavenBrella(name: String = "UnderMybrella's Maven") =
    maven {
        setName(name)
        setUrl("https://maven.brella.dev")
    }

public inline fun DependencyHandler.korneaAnnotations(version: String) =
    kornea("annotations", version)

public inline fun DependencyHandler.korneaApollo(version: String) =
    kornea("apollo", version)

public inline fun DependencyHandler.korneaBase(version: String) =
    kornea("base", version)

public inline fun DependencyHandler.korneaConfig(version: String) =
    kornea("config", version)

public inline fun DependencyHandler.korneaErrors(version: String) =
    kornea("errors", version)

public inline fun DependencyHandler.korneaImg(version: String) =
    kornea("img", version)

public inline fun DependencyHandler.korneaIO(version: String) =
    kornea("io", version)

public inline fun DependencyHandler.korneaModelling(version: String) =
    kornea("modelling", version)

public inline fun DependencyHandler.korneaToolkit(version: String) =
    kornea("toolkit", version)

public inline fun DependencyHandler.kornea(module: String, version: String) =
    create("dev.brella:kornea-$module:$version")