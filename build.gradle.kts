plugins {
    id("java")
    id("com.diffplug.spotless") version "7.0.0.BETA2"
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "com.github.toastshaman"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.1.0")
    implementation("io.vavr:vavr:0.10.4")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.oneeyedmen:okeydoke:2.0.3")
    testImplementation("org.assertj:assertj-core:3.26.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_23
    targetCompatibility = JavaVersion.VERSION_23
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

spotless {
    java {
        toggleOffOn()
        googleJavaFormat()
    }
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}

tasks.withType<JavaCompile> {
    finalizedBy(tasks.spotlessApply)
}
