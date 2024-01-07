plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    // Quarkus Libraries
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-qute")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-arc")


    // PDF Libraries
    /* Apache PDF BOX */
    implementation("com.openhtmltopdf:openhtmltopdf-pdfbox:1.0.10") // Sep 13, 2021 - LGPL 2.1
    implementation("com.openhtmltopdf:openhtmltopdf-core:1.0.10") // Sep 13, 2021 - LGPL 2.1

    /* OpenPDF(fork IText) */
    implementation("com.github.librepdf:openpdf:1.3.35") // Dec 27, 2023 - LGPL 2.1MPL 2.0
    implementation("org.xhtmlrenderer:flying-saucer-pdf:9.4.0") // Dec 30, 2023 - LGPL 2.1

    // Utils
    implementation("org.jsoup:jsoup:1.16.2")


    // Test Libraries
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "org.acme"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
