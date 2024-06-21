plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("io.github.goooler.shadow") version "8.1.7"
}

group = "de.leghast"
version = "2.4.5"
description = "Create miniatures of your builds and use block displays with ease (Idea by reyzixDE)"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

repositories {
    maven {
        url = uri("https://repo.codemc.io/repository/maven-snapshots/")
    }
    maven {
        url = uri("https://repo.codemc.io/repository/maven-public/")
    }
}

dependencies {
    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
    implementation("org.incendo", "cloud-paper", "2.0.0-beta.8")
    implementation("net.wesjd:anvilgui:1.9.5-SNAPSHOT")
}

tasks {
    compileJava {
        options.release = 21
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    reobfJar {
      outputJar = layout.buildDirectory.file("/home/julius/Paper Development/plugins/Miniaturise-${project.version}.jar")
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
        val props = mapOf(
            "name" to project.name,
            "version" to project.version,
            "description" to project.description,
            "apiVersion" to "1.20"
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    shadowJar {
        configurations = listOf(project.configurations.runtimeClasspath.get())
        mergeServiceFiles()

        fun reloc(pkg: String) = relocate(pkg, "de.leghast.dependency.$pkg")

        reloc("org.incendo.cloud")
        reloc("io.leangen.geantyref")
        reloc("net.wesjd.anvilgui")
    }
}




