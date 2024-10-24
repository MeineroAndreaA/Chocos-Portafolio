plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.aam.chocos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aam.chocos"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

        buildFeatures {
            buildConfig = true
            compose = true
        }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = false
        }
    }

    sourceSets {
        getByName("test") {
            assets.srcDirs("src/test/assets/")
        }
        getByName("androidTest") {
            assets.srcDirs("src/androidTest/assets/")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.support.annotations)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation (libs.coil.compose)
    implementation(libs.hilt.android)
    implementation(libs.googleid)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.annotation)
    implementation(libs.hilt.common)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation (libs.lottie.compose)
    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    testImplementation(libs.androidx.core)
    testImplementation(libs.androidx.runner)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.mockwebserver)


    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    // LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)
    // Lifecycles only (without ViewModel or LiveData)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    // Lifecycle utilities for Compose
    implementation (libs.androidx.lifecycle.runtime.compose)

    // Saved state module for ViewModel
    implementation (libs.lifecycle.viewmodel.savedstate)

    // Annotation processor
    ksp (libs.androidx.lifecycle.compiler)
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation (libs.androidx.lifecycle.common.java8)

    // optional - helpers for implementing LifecycleOwner in a Service
    implementation (libs.androidx.lifecycle.service)

    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    implementation (libs.androidx.lifecycle.process)

    // optional - ReactiveStreams support for LiveData
    implementation (libs.androidx.lifecycle.reactivestreams.ktx)

    // optional - Test helpers for Lifecycle runtime
    testImplementation (libs.androidx.lifecycle.runtime.testing)


    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)

    //Android Credential
    implementation(libs.play.services.auth)
    implementation(libs.androidx.credentials)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}