plugins {
    java
    idea
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.hibernate.orm") version "6.4.4.Final"
    id("com.sngular.scs-multiapi-gradle-plugin") version "5.4.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.kafka:spring-kafka")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:kafka")
    testImplementation("org.testcontainers:postgresql")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

hibernate {
    enhancement {
        enableAssociationManagement.set(true)
    }
}


//openapimodel {
//
//}

asyncapimodel {
    specFile {
        filePath = "/src/main/resources/specs/demo.asyncapi.yml"
        consumer {
            ids = "publishOperation"
            apiPackage = "com.sngular.apigenerator.asyncapi.business_model.model.event.consumer"
            modelPackage = "com.sngular.apigenerator.asyncapi.business_model.model.event"
        }
        supplier {
            ids = "subscribeOperation"
            apiPackage = "com.sngular.apigenerator.asyncapi.business_model.model.event.producer"
            modelPackage = "com.sngular.apigenerator.asyncapi.business_model.model.event"
        }
        streamBridge {
            ids = "streamBridgeOperation"
            apiPackage = "com.sngular.apigenerator.asyncapi.business_model.model.event.producer"
            modelPackage = "com.sngular.apigenerator.asyncapi.business_model.model.event"
        }
        overWriteModel = true
    }
}

