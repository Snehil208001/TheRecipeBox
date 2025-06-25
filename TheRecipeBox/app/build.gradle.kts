plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Remove kotlin("plugin.serialization") if you are using Gson for network calls
    // If you intend to use Kotlinx Serialization, you would keep this and change Retrofit's converter
}

android {
    namespace = "com.example.therecipebox" // Ensure this matches your actual package and Manifest
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.therecipebox" // Ensure this matches your actual package and Manifest
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Or your current compatible version
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // ViewModel extensions for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0") // Updated for consistency

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Gson conversion
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // Okhttp logging interceptor (for debugging network calls)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.7.0") // Coil for Compose (not Coil3 which is for KMP)
    // Remove coil3 dependencies if you are using coil-compose directly
    // implementation("io.coil-kt.coil3:coil-compose:3.2.0")
    // implementation("io.coil-kt.coil3:coil-network-okhttp:3.2.0")


    // Navigation Compose (if you plan to add navigation later)
    val nav_version = "2.7.0" // Check for latest stable version
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Remove kotlinx-serialization-json if you are primarily using Gson
    // implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
}