import dev.brella.kornea.gradle.registerFillReadmeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//    id("org.gradle.kotlin.kotlin-dsl") version "2.3.3"

    kotlin("jvm") version "1.5.31" apply false
    id("org.jetbrains.dokka") version "1.5.31" apply false
    id("com.gradle.plugin-publish") version "1.0.0-rc-2" apply false
    id("dev.brella.kornea") version "1.3.0"
    `kotlin-dsl` apply false
}

subprojects {
    apply(plugin = "maven-publish")

    group = "dev.brella"

    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
//        freeCompilerArgs += listOf("-Xcontext-receivers")
        }
    }

    configure<PublishingExtension> {
        repositories {
            maven(url = "${rootProject.buildDir}/repo")
        }

        publications {
            create<MavenPublication>("pluginMaven")
        }
    }
}

registerFillReadmeTask("fillReadme") {
    inputFile.set(File(projectDir, "README_TEMPLATE.md"))
    outputFile.set(File(projectDir, "README.md"))

    addReplacement("%PROJECT_VERSION%") { project(":KorneaGradleProject").version.toString() }
    addReplacement("%SETTINGS_VERSION%") { project(":KorneaGradleSettings").version.toString() }
}
