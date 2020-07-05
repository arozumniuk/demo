plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("org.unbroken-dome.test-sets") version "3.0.1"
    id("idea")
    id("java")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

idea {
    module {
        inheritOutputDirs = true
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

tasks {
    "wrapper"(Wrapper::class) {
        gradleVersion = "6.3"
        distributionType = Wrapper.DistributionType.ALL
    }
}

