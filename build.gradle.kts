plugins {
  id("roundalib") version "0.4.0"
}

repositories {
  maven("https://jitpack.io")
}

dependencies {
  implementation("io.github.llamalad7:mixinextras-fabric:0.3.2")
  annotationProcessor("io.github.llamalad7:mixinextras-fabric:0.3.2")
  include("io.github.llamalad7:mixinextras-fabric:0.3.2")
}
