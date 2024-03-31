import io.papermc.paperweight.util.fileExists

plugins {
  `java-library`
  id("io.papermc.paperweight.userdev")
  id("xyz.jpenilla.run-paper") version "2.2.3" // Adds runServer and runMojangMappedServer tasks for testing
  id("com.github.johnrengelman.shadow") version "8.1.1"
  `remap-plugin-src`
}

repositories {
  maven {
    url = uri("https://repo.codemc.io/repository/maven-snapshots/")
  }
}

group = "de.leghast"
version = "2.4.1"
description = "Create miniatures of your builds and use block displays with ease (Idea by reyzixDE)"

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
  api("net.wesjd:anvilgui:1.9.2-SNAPSHOT")
  paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
  // paperweight.foliaDevBundle("1.20.4-R0.1-SNAPSHOT")
  // paperweight.devBundle("com.example.paperfork", "1.20.4-R0.1-SNAPSHOT")

  // Shadow will include the runtimeClasspath by default, which implementation adds to.
  // Dependencies you don't want to include go in the compileOnly configuration.
  // Make sure to relocate shaded dependencies!
  implementation("cloud.commandframework", "cloud-paper", "1.8.4")
}

tasks {
  // Configure reobfJar to run when invoking the build task
  assemble {
    dependsOn(reobfJar)
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    // See https://openjdk.java.net/jeps/247 for more information.
    options.release.set(17)
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
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


  reobfJar {
    // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
    // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
    outputJar.set(layout.buildDirectory.file("/home/julius/Paper Development/plugins/Miniaturise-${project.version}.jar"))
  }


  shadowJar {
    // helper function to relocate a package into our package
    fun reloc(pkg: String) = relocate(pkg, "io.papermc.paperweight.testplugin.dependency.$pkg")

    // relocate cloud and it's transitive dependencies
    reloc("cloud.commandframework")
    reloc("io.leangen.geantyref")
  }


}


