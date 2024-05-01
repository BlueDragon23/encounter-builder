plugins {
    java
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.node-gradle.node") version "7.0.1"
    id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
}

group = "dev.aidang"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "dev.aidang.encounters.EncountersApplication"
        )
    }
}

spotless {
    java {
        palantirJavaFormat()
    }
}

node {
    nodeProjectDir.set(file("${projectDir}/svelte"))
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // junit
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:3.25.1")

    testImplementation("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.jar {
    // Only use the spring jar
    enabled = false
}

tasks.processResources {
    from("svelte/build") {
        into("static")
    }
}


