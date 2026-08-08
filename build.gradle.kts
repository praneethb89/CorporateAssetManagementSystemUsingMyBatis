plugins {
    id("java")
}

group = "com.crimsonlogic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Core MyBatis Framework
    implementation("org.mybatis:mybatis:3.5.19")

    // MySQL Driver
    runtimeOnly("com.mysql:mysql-connector-j:9.6.0")
}

tasks.test {
    useJUnitPlatform()
}