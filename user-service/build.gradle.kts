dependencies {
    implementation(project(":common"))
}

tasks {
    bootJar {
        enabled = true
    }

    jar {
        enabled = true
    }
}