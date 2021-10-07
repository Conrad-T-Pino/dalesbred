plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    val kotlinVersion = "1.5.31"
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.3.2")
    implementation("pl.allegro.tech.build:axion-release-plugin:1.13.3")
}
