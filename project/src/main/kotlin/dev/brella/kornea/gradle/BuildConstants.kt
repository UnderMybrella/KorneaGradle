package dev.brella.kornea.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.InetAddress
import java.net.UnknownHostException
import java.time.Clock
import kotlin.reflect.typeOf

//public data class BuildConstant(val name: String, val type: KType, public val isConstant: Boolean, val supplier: () -> Any?)
abstract class BuildConstantsTask : DefaultTask() {
//    @get:Input
//    abstract val sourceSet: Property<SourceDirectorySet>

    @get:OutputDirectory
    @get:Optional
    abstract val outputDirectory: RegularFileProperty

    @get:Input
    @get:Optional
    abstract val fileName: Property<String>
    @get:Input
    @get:Optional
    abstract val filePackage: Property<String>
    @get:Input
    @get:Optional
    abstract val nullableTypeSafety: Property<Boolean>

    @get:Internal
    val backing: MutableList<() -> String> = ArrayList()

    public fun addLine(block: () -> String) =
        backing.add(block)

    public inline fun add(crossinline block: StringBuilder.(nullableTypeSafety: Boolean) -> Unit) =
        addLine { buildString { block(this, nullableTypeSafety.getOrElse(true)) } }

    public inline fun addRaw(name: String, type: String, value: String) =
        add {
            append("public ")
            append("val ")
            append(name)
            append(": ")
            append(type)
            append(" = ")
            append(value)
        }

    public inline fun addRaw(line: String) =
        addLine(line::toString)

    @OptIn(ExperimentalStdlibApi::class)
    public inline fun <reified T> add(name: String, crossinline supplier: () -> T) {
        val type = typeOf<T>()

        add { nullableTypeSafety ->
            val value = supplier()
            val isNulled = (nullableTypeSafety && type.isMarkedNullable) || (!nullableTypeSafety && value == null)
            val constant = if (isNulled) false
            else when (type.classifier) {
                Byte::class -> true
                Short::class -> true
                Int::class -> true
                Long::class -> true
                Float::class -> true
                Double::class -> true
                String::class -> true

                else -> false
            }

            append("public ")
            if (constant) append("const ")
            append("val ")
            append(name)
            append(": ")

            if (isNulled) {
                when (type.classifier) {
                    Byte::class -> append("Byte? = ").append(value?.toString()).append("?.toByte()")
                    Short::class -> append("Short? = ").append(value?.toString()).append("?.toShort()")
                    Int::class -> append("Int? = ").append(value?.toString()).append("?.toInt()")
                    Long::class -> append("Long? = ").append(value?.toString()).append("?.toLong()")
                    Float::class -> append("Float? = ").append(value?.toString()).append("?.toFloat()")
                    Double::class -> append("Double? = ").append(value?.toString()).append("?.toDouble()")
                    String::class ->
                        if (value == null) append("String? = null")
                        else append("String? = \"").append(value).append('"')

                    //                is KClass<*> -> {
                    //                    when (classifier.qualifiedName) {
                    //                        Array::class.qualifiedName -> {
                    //                        }
                    //                        else -> error("Unknown class $type")
                    //                    }
                    //                }

                    else -> error("Unknown type $type")
                }
            } else {
                when (val classifier = type.classifier) {
                    Byte::class -> append("Byte = ").append(value).append(".toByte()")
                    Short::class -> append("Short = ").append(value).append(".toShort()")
                    Int::class -> append("Int = ").append(value).append(".toInt()")
                    Long::class -> append("Long = ").append(value).append(".toLong()")
                    Float::class -> append("Float = ").append(value).append(".toFloat()")
                    Double::class -> append("Double = ").append(value).append(".toDouble()")
                    String::class -> append("String = \"").append(value).append('"')

                    //                is KClass<*> -> {
                    //                    when (classifier.qualifiedName) {
                    //                        Array::class.qualifiedName -> {
                    //                        }
                    //                        else -> error("Unknown class $type")
                    //                    }
                    //                }

                    else -> error("Unknown type $type")
                }
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    public inline fun <reified T> add(name: String, value: T) {
        val type = typeOf<T>()

        add { nullableTypeSafety ->
            val isNulled = (nullableTypeSafety && type.isMarkedNullable) || (!nullableTypeSafety && value == null)
            val constant = if (isNulled) false
            else when (type.classifier) {
                Byte::class -> true
                Short::class -> true
                Int::class -> true
                Long::class -> true
                Float::class -> true
                Double::class -> true
                String::class -> true

                else -> false
            }

            append("public ")
            if (constant) append("const ")
            append("val ")
            append(name)
            append(": ")

            if (isNulled) {
                when (type.classifier) {
                    Byte::class -> append("Byte? = ").append(value?.toString()).append("?.toByte()")
                    Short::class -> append("Short? = ").append(value?.toString()).append("?.toShort()")
                    Int::class -> append("Int? = ").append(value?.toString()).append("?.toInt()")
                    Long::class -> append("Long? = ").append(value?.toString()).append("?.toLong()")
                    Float::class -> append("Float? = ").append(value?.toString()).append("?.toFloat()")
                    Double::class -> append("Double? = ").append(value?.toString()).append("?.toDouble()")
                    String::class ->
                        if (value == null) append("String? = null")
                        else append("String? = \"").append(value).append('"')
                }
            } else {
                when (val classifier = type.classifier) {
                    Byte::class -> append("Byte = ").append(value).append(".toByte()")
                    Short::class -> append("Short = ").append(value).append(".toShort()")
                    Int::class -> append("Int = ").append(value).append(".toInt()")
                    Long::class -> append("Long = ").append(value).append(".toLong()")
                    Float::class -> append("Float = ").append(value).append(".toFloat()")
                    Double::class -> append("Double = ").append(value).append(".toDouble()")
                    String::class -> append("String = \"").append(value).append('"')

                    //                is KClass<*> -> {
                    //                    when (classifier.qualifiedName) {
                    //                        Array::class.qualifiedName -> {
                    //                        }
                    //                        else -> error("Unknown class $type")
                    //                    }
                    //                }

                    else -> error("Unknown type $type")
                }
            }
        }
    }

    public inline operator fun <reified T> String.invoke(supplier: () -> T) =
        add(this, supplier())

    public inline operator fun <reified T> String.invoke(value: T) =
        add(this, value)

    public inline fun addProperty(name: String, key: String) =
        add<String?>(name) { System.getProperty(key) }

    public inline fun addEnv(name: String, key: String) =
        add<String?>(name) { System.getenv(key) }

    public inline fun addProcessOutput(name: String, crossinline supplier: () -> ProcessBuilder) =
        add<String>(name) {
            val process = supplier()
                .start()

            val processOutput = BufferedInputStream(process.inputStream)
            val baos = ByteArrayOutputStream()
            val buffer = ByteArray(8192)

            while (process.isAlive) {
                val read = processOutput.read(buffer)
                if (read != -1) baos.write(buffer, 0, read)
            }

            while (true) {
                val read = processOutput.read(buffer)
                if (read == -1) break

                baos.write(buffer, 0, read)
            }

            baos.toByteArray().decodeToString().trim()
        }

    public inline fun gitCommitShortHash(name: String) =
        addProcessOutput(name) { ProcessBuilder("git", "rev-parse", "--short", "HEAD") }

    public inline fun gitCommitHash(name: String) =
        addProcessOutput(name) { ProcessBuilder("git", "rev-parse", "HEAD") }

    public inline fun gitBranch(name: String) =
        addProcessOutput(name) { ProcessBuilder("git", "symbolic-ref", "--short", "HEAD") }

    public inline fun gitCommitMessage(name: String) =
        addProcessOutput(name) { ProcessBuilder("git", "log", "-1", "--pretty=%B") }

    public inline fun gradleVersion(name: String) =
        add(name) { project.version.toString() }

    public inline fun gradleGroup(name: String) =
        add(name) { project.group.toString() }

    public inline fun gradleName(name: String) =
        add(name) { project.name }

    public inline fun gradleDisplayName(name: String) =
        add(name) { project.displayName }

    public inline fun gradleDescription(name: String) =
        add(name) { project.description }

    public inline fun buildTimeEpoch(name: String) =
        add(name) { System.currentTimeMillis() }

    public inline fun buildTimeUtcEpoch(name: String) =
        add(name) { Clock.systemUTC().millis() }

    public inline fun hostname(name: String) =
        add(name) {
            System.getenv("COMPUTERNAME")
                ?: System.getenv("HOSTNAME")
                ?: try {
                    InetAddress.getLocalHost().hostName
                } catch (uhe: UnknownHostException) {
                    null
                }
        }

    public inline fun jvmVersion(name: String) =
        addProperty(name, "java.version")

    public inline fun jvmDate(name: String) =
        addProperty(name, "java.version.date")

    public inline fun jvmVendor(name: String) =
        addProperty(name, "java.vendor")

    public inline fun jvmClassVersion(name: String) =
        addProperty(name, "java.class.version")

    public inline fun jvmCompiler(name: String) =
        addProperty(name, "java.compiler")

    public inline fun osName(name: String) =
        addProperty(name, "os.name")

    public inline fun osArch(name: String) =
        addProperty(name, "os.arch")

    public inline fun osVersion(name: String) =
        addProperty(name, "os.version")

    public inline fun userName(name: String) =
        addProperty(name, "user.name")

    public fun setOutputInSourceSet(sourceSet: SourceDirectorySet, output: File = File(project.buildDir, "generated/kornea")) {
//        this.sourceSet.set(sourceSet)
        this.outputDirectory.set(output)

        sourceSet.srcDir(output)
    }

    @TaskAction
    public fun build() {
        val sourceDir =
            this.outputDirectory.asFile.getOrElse(File(project.buildDir, "generated/kornea"))

        val filePackage = this.filePackage.getOrElse("dev.brella.kornea")
        val fileName = this.fileName.getOrElse("BuildConstants")
        val outputDir = File(sourceDir, filePackage.replace('.', '/'))
        outputDir.mkdirs()

        val outputFile = File(outputDir, "${fileName}.kt")
        outputFile.writeText(buildString {
            append("package ")
            appendLine(filePackage)
            appendLine()

            append("public object ")
            append(fileName)
            appendLine(" {")

            backing.forEach { line ->
                append('\t')
                appendLine(line())
            }

            append("}")
        })
    }

    init {
        group = "kornea"
    }
}

//fun KotlinSingleTargetExtension.buildConstants() {
//}
//
//fun KotlinMultiplatformExtension.buildConstants() {
//
//}

//public inline fun KotlinSingleTargetExtension.buildConstants(taskName: String = "buildConstants", builder: BuildConstantsBuilder.() -> Unit) =
//    this.

public fun Project.registerBuildConstantsTask(
    name: String,
    configure: BuildConstantsTask.() -> Unit,
): TaskProvider<BuildConstantsTask> =
    tasks.register(name, configure)