plugins {
    kotlin("jvm") version "2.1.20"
    id("com.google.devtools.ksp") version "2.1.20-2.0.0"
}

group = "org.kotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-core:2.1.0")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:2.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}