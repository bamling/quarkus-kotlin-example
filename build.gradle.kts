import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    id("io.quarkus") version "0.21.1"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.50"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:0.21.1"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("io.quarkus:quarkus-mongodb-client")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured:4.1.0")
}

version = "0.0.1"

quarkus {
    setSourceDir(projectDir.absolutePath + "/src/main/kotlin")
}

allOpen {
    annotation("javax.enterprise.context.ApplicationScoped")
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}