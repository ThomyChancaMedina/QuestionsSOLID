plugins {
    id("java-library")
    id("kotlin")
}



dependencies {
    implementation(fileTree("libs"){include(listOf("*.jar"))})
    implementation( "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.10")
}

