plugins {
    kotlin("jvm") version "2.2.0"
    id("org.sonarqube") version "5.1.0.4882"
    jacoco
    `maven-publish`
    signing
}

group = "github.emiilia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
kotlin {
    jvmToolchain(21)
}

sonar {
    properties {
        property("sonar.projectKey", "github.emiilia:demoic")
        property("sonar.projectName", "DemoIC") 
        property("sonar.host.url", System.getenv("SONAR_HOST_URL") ?: "http://localhost:9000")
        property("sonar.token", System.getenv("SONAR_TOKEN") ?: "admin")
        property("sonar.sources", "src/main/kotlin")
        property("sonar.tests", "src/test/kotlin")
        property("sonar.java.binaries", "build/classes/kotlin/main")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
    isSkipProject = true
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            
            pom {
                name.set("DemoIC")
                description.set("Demo Kotlin project with basic mathematical operations")
                url.set("https://github.com/emiilia/demoic")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("emiilia")
                        name.set("Emiilia")
                        email.set("emiilia@github.com")
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/emiilia/demoic.git")
                    developerConnection.set("scm:git:git@github.com:emiilia/demoic.git")
                    url.set("https://github.com/emiilia/demoic")
                }
            }
        }
    }
    
    repositories {
        maven {
            name = "NexusLocal"
            val releasesRepoUrl = "http://localhost:8081/repository/maven-releases/"
            val snapshotsRepoUrl = "http://localhost:8081/repository/maven-snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            isAllowInsecureProtocol = true
            credentials {
                username = System.getenv("NEXUS_USERNAME") ?: "admin"
                password = System.getenv("NEXUS_PASSWORD") ?: "admin123"
            }
        }
    }
}