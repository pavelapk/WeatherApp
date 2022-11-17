plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
}

android {
    namespace = "ru.pavelapk.weatherapp.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "WEATHER_API_URL",
            "\"https://api.open-meteo.com/\""
        )

        buildConfigField(
            "String",
            "GEO_API_URL",
            "\"http://geodb-free-service.wirefreethought.com/\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Kotlin Serialization
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    // Retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // Logging
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")

    // Room
    val roomVersion = "2.4.3"
    api("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Google services
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    // Testing
    testImplementation("junit:junit:4.13.2")

    implementation(project(":domain"))
}