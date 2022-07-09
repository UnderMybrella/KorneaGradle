package dev.brella.kornea.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register

abstract class FillReadmeVersionTask : DefaultTask() {
    @get:InputFile
    abstract val inputFile: RegularFileProperty

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Internal
    val replacements: MutableMap<String, () -> String> = HashMap()

    public fun addReplacement(key: String, replacement: () -> String) =
        replacements.put(key, replacement)

    public inline fun version(key: String) =
        addReplacement(key) { project.version.toString() }

    @TaskAction
    fun fill() {
        val inputFile = this.inputFile.asFile.get()
        val outputFile = this.outputFile.asFile.get()

        inputFile.bufferedReader().useLines { lines ->
            outputFile.printWriter().use { writer ->
                val builder = StringBuilder()

                lines.forEach { line ->
                    builder.clear()
                    builder.append(line)

                    replacements.forEach { (k, v) ->
                        val index = builder.indexOf(k)
                        if (index != -1) builder.replace(index, index + k.length, v())
                    }

                    writer.println(builder)
                }
            }
        }
    }

    init {
        group = "kornea"
    }
}

public fun Project.registerFillReadmeTask(
    name: String,
    configure: FillReadmeVersionTask.() -> Unit = {},
): TaskProvider<FillReadmeVersionTask> =
    tasks.register(name, configure)