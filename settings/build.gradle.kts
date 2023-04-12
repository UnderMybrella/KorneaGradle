import dev.brella.kornea.gradle.registerFillReadmeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("com.gradle.plugin-publish")
    id("dev.brella.kornea")
    `kotlin-dsl`
}

version = "1.0.1"

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    dokkaHtmlPlugin("org.jetbrains.dokka:javadoc-plugin:1.6.21")
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
            id = "dev.brella.kornea.settings"
            displayName = "Kornea-Gradle Settings"
            implementationClass = "dev.brella.kornea.gradle.settings.KorneaSettingsPlugin"
        }
    }
}

//registerFillReadmeTask("fillReadme") {
//    inputFile.set(File(projectDir, "README_TEMPLATE.md"))
//    outputFile.set(File(projectDir, "README.md"))
//
//    version("%VERSION%")
//}

tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
}