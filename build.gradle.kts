plugins {
  id("roundalib") version "0.4.0"
}

repositories {
  maven("https://jitpack.io")
}

dependencies {
  implementation("io.github.llamalad7:mixinextras-fabric:0.2.2")
  annotationProcessor("io.github.llamalad7:mixinextras-fabric:0.2.2")
  include("io.github.llamalad7:mixinextras-fabric:0.2.2")
}

sourceSets {
  main {
    resources {
      srcDir("src/main/generated")
      exclude(".cache/**")
    }
  }
}

loom {
  runs {
    register("datagen") {
      tasks.importMixins.get().enabled = false
      tasks.mergeLanguageFiles.get().enabled = false

      server()
      name("Data Generation")

      runDir("build/datagen")

      vmArg("-Dfabric-api.datagen")
      vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
      vmArg("-Dfabric-api.datagen.modid=${project.property("mod_id").toString()}")
    }
    register("datagenClient") {
      tasks.importMixins.get().enabled = false
      tasks.mergeLanguageFiles.get().enabled = false

      client()
      name("Data Generation Client")

      runDir("build/datagen")

      vmArg("-Dfabric-api.datagen")
      vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
      vmArg("-Dfabric-api.datagen.modid=${project.property("mod_id").toString()}")
    }
  }
}
