import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.gradle.kotlin.kotlin-dsl") version "2.3.3"

    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.dokka") version "1.6.21"
    id("com.gradle.plugin-publish") version "1.0.0-rc-2"
}

group = "dev.brella"
version = "1.1.0"

repositories {
    mavenCentral()
    gradlePluginPortal()
    mavenLocal()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    dokkaHtmlPlugin("org.jetbrains.dokka:javadoc-plugin:1.6.21")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

//tasks.kotlinSourcesJar {}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf("-Xcontext-receivers")
    }
}

pluginBundle {
    website = "https://github.com/UnderMybrella/kornea-gradle"
    vcsUrl = "https://github.com/UnderMybrella/kornea-gradle"

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

tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
}

configure<PublishingExtension> {
    repositories {
        maven(url = "${rootProject.buildDir}/repo")
    }

    publications {
        create<MavenPublication>("pluginMaven")
    }
}