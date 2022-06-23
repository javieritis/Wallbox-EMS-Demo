plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.5.31"
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.wallbox.ems.demo"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.wallbox.ems.demo.base.runner.WallboxDemoHiltRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true

            //Create release build without signing
            signingConfig = signingConfigs.getByName("debug")
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
        allWarningsAsErrors = true
    }
    buildFeatures {
        viewBinding = true
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src/main/assets")
            }
        }
        listOf(getByName("test"), getByName("androidTest")).forEach { srcSet ->
            srcSet.java.srcDirs("src/testFixtures/java")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.palette:palette-ktx:1.0.0")
    implementation("androidx.activity:activity-ktx:1.4.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //MPAndroidChart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.42")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.1")
    kapt("com.google.dagger:hilt-compiler:2.42")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    //Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.38.1")
    kaptTest("com.google.dagger:hilt-android-compiler:2.42")

    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.38.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.42")

    //Mockito
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    androidTestImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    //Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")
}