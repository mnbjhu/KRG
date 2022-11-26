import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("redis.clients:jedis:4.3.1")
    api(kotlin("reflect"))
    testApi("com.natpryce:konfig:1.6.10.0")
    testApi(kotlin("test"))
    testApi("org.amshove.kluent:kluent:1.72")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}