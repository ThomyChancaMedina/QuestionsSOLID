plugins {
    id("java-library")
    id("kotlin")
}



dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation( "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.10")
}
