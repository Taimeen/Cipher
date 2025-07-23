plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.spam.spamdetection"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.spam.spamdetection"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {
    // Core libraries
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity:1.7.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // RecyclerView for list views
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    // Gson for JSON parsing
    implementation("com.google.code.gson:gson:2.10.1")

    // TensorFlow Lite for ML model loading and inference
    implementation("org.tensorflow:tensorflow-lite:2.12.0")

    // Permissions handling
    implementation("androidx.core:core:1.12.0")

    // Unit testing
    testImplementation("junit:junit:4.13.2")

    // Android testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5") 
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}
