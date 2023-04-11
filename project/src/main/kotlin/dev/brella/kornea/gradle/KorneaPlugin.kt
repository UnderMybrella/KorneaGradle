package dev.brella.kornea.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class KorneaPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.create<KorneaPluginExtension>("kornea")
    }
}