plugins {
    id("io.spring.dependency-management")
    id("org.springframework.boot")
    id("org.unbroken-dome.test-sets")
}

dependencyManagement {
    dependencies {
        dependency("org.projectlombok:lombok:1.18.12")
        dependency("org.testng:testng:7.1.0")
        dependency("uk.com.robust-it:cloning:1.9.12")
        dependency("io.rest-assured:rest-assured:4.1.2")
        dependency("io.rest-assured:json-path:4.1.2")
        dependency("io.rest-assured:xml-path:4.1.2")
        dependency("com.github.javafaker:javafaker:1.0.1")
        dependency("org.assertj:assertj-core:3.6.1")
        dependency("com.fasterxml.jackson.core:jackson-databind:2.11.1")
        dependency("org.seleniumhq.selenium:selenium-java:3.13.0")
        dependency("io.qameta.allure:allure-testng:2.13.3")
    }
}

testSets {
    "uiTests"() {
        dependencies {
            testImplementation("org.springframework:spring-test")
            testImplementation("org.springframework.boot:spring-boot-test")
            testImplementation("org.testng:testng")
            testImplementation("uk.com.robust-it:cloning")
            testImplementation("com.github.javafaker:javafaker")
            testImplementation("org.assertj:assertj-core")
            testImplementation("org.seleniumhq.selenium:selenium-java")
            testImplementation("io.qameta.allure:allure-testng")
        }
    }
    "apiTests"() {
        dependencies {
            testImplementation(project(":shared:test-utils"))
            /* testImplementation("org.springframework.boot:spring-boot-configuration-processor")
             testAnnotationProcessor("org.springframework.boot:spring-boot-configuration-processor")*/
            testImplementation("org.springframework:spring-test")
            testImplementation("org.springframework.boot:spring-boot-test")
            testImplementation("org.testng:testng")
            testImplementation("uk.com.robust-it:cloning")
            testImplementation("com.github.javafaker:javafaker")
            testAnnotationProcessor("org.projectlombok:lombok")
            testImplementation("org.projectlombok:lombok")
            testImplementation("io.rest-assured:rest-assured")
            testImplementation("io.rest-assured:json-path")
            testImplementation("io.rest-assured:xml-path")
            testImplementation("org.assertj:assertj-core")
            testImplementation("com.fasterxml.jackson.core:jackson-databind")
            testImplementation("io.qameta.allure:allure-testng")
        }
    }
}

tasks {
    named<Test>("apiTests") {
        useTestNG() {
            useDefaultListeners = true
            reports.html.destination = file("$buildDir/html")
            preserveOrder = true
        }
        outputs.upToDateWhen { false }
    }

    named<Test>("uiTests") {
        useTestNG() {
            useDefaultListeners = true
            reports.html.destination = file("$buildDir/html")
            preserveOrder = true
        }
        mustRunAfter("apiTests")
        outputs.upToDateWhen { false }
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
