plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.developer.android.dev.technologia.androidapp.mvvmunittesting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.developer.android.dev.technologia.androidapp.mvvmunittesting"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")

    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.google.code.gson:gson:2.11.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    ksp("com.github.bumptech.glide:ksp:4.16.0")

    testImplementation ("com.squareup.okhttp3:mockwebserver:4.12.0")

    testImplementation ("org.mockito:mockito-core:5.12.0")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}