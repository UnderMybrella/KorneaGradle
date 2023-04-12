package dev.brella.kornea.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.internal.tasks.properties.InputFilePropertyType
import org.gradle.api.internal.tasks.properties.OutputFilePropertyType
import org.gradle.api.internal.tasks.properties.PropertyValue
import org.gradle.api.internal.tasks.properties.PropertyVisitor
import org.gradle.api.tasks.*
import org.gradle.internal.fingerprint.DirectorySensitivity
import org.gradle.internal.fingerprint.LineEndingSensitivity
import org.gradle.kotlin.dsl.register
import java.util.concurrent.Callable

abstract class FillReadmeVersionTask : DefaultTask() {
    @get:InputFile
    abstract val inputFile: RegularFileProperty

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    public fun addStaticReplacement(key: String, replacement: Any?) {
        this.inputs.property(key, replacement)
    }

    public fun addReplacement(key: String, replacement: () -> Any?) {
        this.inputs.property(key, replacement)
    }

    public fun version(key: String) =
        addReplacement(key) { project.version }

    @TaskAction
    fun fill() {
        val inputFile = this.inputFile.asFile.get()
        val outputFile = this.outputFile.asFile.get()

        outputFile.printWriter().use { writer ->
            val builder = StringBuilder()
            builder.append(inputFile.readText())

            val inputs = this.inputs
            val visitor = ReplacementPropertyVisitor(builder)
            inputs.visitRegisteredProperties(visitor)
            writer.println(builder)
            builder.clear()

//                    replacements.forEach { (k, v) ->
//                        val index = builder.indexOf(k)
//                        if (index != -1) builder.replace(index, index + k.length, v())
//                    }
        }
    }

    init {
        group = "kornea"
    }
}

internal class ReplacementPropertyVisitor(val builder: StringBuilder) : PropertyVisitor {
    override fun visitInputFileProperty(
        propertyName: String,
        optional: Boolean,
        skipWhenEmpty: Boolean,
        directorySensitivity: DirectorySensitivity,
        lineEndingSensitivity: LineEndingSensitivity,
        incremental: Boolean,
        fileNormalizer: Class<out FileNormalizer>?,
        value: PropertyValue,
        filePropertyType: InputFilePropertyType,
    ) {
    }

    override fun visitInputProperty(propertyName: String, value: PropertyValue, optional: Boolean) {
        value.maybeFinalizeValue()
        val finalised = deepCall(value.call()).toString()

        var index = builder.indexOf(propertyName)
        while (index != -1) {
            builder.replace(index, index + propertyName.length, finalised)
            index = builder.indexOf(propertyName)
        }
    }

    override fun visitOutputFileProperty(
        propertyName: String,
        optional: Boolean,
        value: PropertyValue,
        filePropertyType: OutputFilePropertyType,
    ) {
    }

    override fun visitDestroyableProperty(value: Any) {}

    override fun visitLocalStateProperty(value: Any) {}

    private fun deepCall(prop: Any?): Any? =
        when (prop) {
            is Callable<*> -> deepCall(prop.call())
            is () -> Any? -> deepCall(prop.invoke())
            else -> prop
        }
}

public fun Project.registerFillReadmeTask(
    name: String,
    configure: FillReadmeVersionTask.() -> Unit = {},
): TaskProvider<FillReadmeVersionTask> =
    tasks.register(name, configure)