pluginManagement {
    repositories {
//        maven(url = "./build/repo")
        gradlePluginPortal()
    }
}

include(":project", ":settings")

project(":project").name = "KorneaGradleProject"
project(":settings").name = "KorneaGradleSettings"