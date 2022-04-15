import dev.brella.kornea.gradle.projectFrom
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    id("maven-publish")
    id("dev.brella.kornea") version "1.0.0"
    `kotlin-dsl`
}

group = "dev.brella"
version = "1.0.0"

repositories {
    mavenCentral()
    gradlePluginPortal()
    mavenLocal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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

configure<PublishingExtension> {
    repositories {
        maven(url = "${rootProject.buildDir}/repo")
    }
}