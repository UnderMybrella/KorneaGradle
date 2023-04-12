import dev.brella.kornea.gradle.registerFillReadmeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val enableDokka = project.property("enableDokka").toString().toBoolean()

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("com.gradle.plugin-publish")
    id("dev.brella.kornea")
    `kotlin-dsl`
}

version = "2.1.0"

if (enableDokka) {
    java {
        withSourcesJar()
        withJavadocJar()
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
//        freeCompilerArgs += listOf("-Xcontext-receivers")
    }
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    if (enableDokka) dokkaHtmlPlugin("org.jetbrains.dokka:javadoc-plugin:1.6.21")
}

tasks.test {
    useJUnitPlatform()
}

pluginBundle {
    website = "https://github.com/UnderMybrella/kornea-gradle"
    vcsUrl = "https://github.com/UnderMybrella/kornea-gradle"
    description =
        "Plugin for assisting with Kotlin development, especially dealing with Multi-Platform and Multi-Module projects."

    tags = listOf("kotlin", "kornea")
}

gradlePlugin {
    plugins {
        create("kornea-gradle") {
            id = "dev.brella.kornea"
            displayName = "Kornea-Gradle"
            implementationClass = "dev.brella.kornea.gradle.KorneaPlugin"
        }
    }
}

registerFillReadmeTask("fillReadme") {
    inputFile.set(File(projectDir, "README_TEMPLATE.md"))
    outputFile.set(File(projectDir, "README.md"))

    version("%VERSION%")
}

if (enableDokka) {
    tasks.named<Jar>("javadocJar") {
        from(tasks.named("dokkaJavadoc"))
    }
}