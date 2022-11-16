plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
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

    // Testing
    testImplementation("junit:junit:4.13.2")

    implementation(project(":domain"))
}