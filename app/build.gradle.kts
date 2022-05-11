import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
}

val localProperties = with(File("local.properties")) {
    Properties().also {
        if (exists()) {
            it.load(FileInputStream(this))
        }
    }
}

val marvelPublicKey: String by localProperties
val marvelPrivateKey: String by localProperties

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.diconium.bermob.marvelous"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "marvelPublicKey", "\"${marvelPublicKey}\"")
        buildConfigField("String", "marvelPrivateKey", "\"${marvelPrivateKey}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
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

    lint {
        abortOnError = false
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {

// Kotlin
    implementation(KotlinX.coroutines.android)
    implementation(KotlinX.serialization.json)

// Android
    implementation(AndroidX.core)
    implementation(AndroidX.core.ktx)
    // Arch
    implementation(AndroidX.appCompat)
    implementation(AndroidX.activity)
    implementation(AndroidX.activity.ktx)
    implementation(AndroidX.fragment)
    implementation(AndroidX.fragment.ktx)
    implementation(AndroidX.lifecycle.liveData)
    implementation(AndroidX.lifecycle.liveDataKtx)
    implementation(AndroidX.lifecycle.viewModel)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.viewModelSavedState)
    implementation(AndroidX.lifecycle.process)
    // UI
    implementation(Google.android.material)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.recyclerView)

// Compose
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.ui.graphics)
    implementation(AndroidX.compose.ui.text)
    implementation(AndroidX.compose.runtime)
    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.compose.foundation)
    implementation(AndroidX.compose.animation)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.material.icons.core)
    implementation(AndroidX.compose.material.ripple)
    implementation(AndroidX.lifecycle.viewModelCompose)
    implementation(AndroidX.constraintLayout.compose)

// HTTP
    implementation(Square.okHttp3)
    implementation(Square.retrofit2)
    implementation(JakeWharton.retrofit2.converter.kotlinxSerialization)
    implementation(Libs.glide)

// Testing
    testImplementation(Testing.junit4)
    testImplementation(KotlinX.coroutines.test)

    androidTestImplementation(AndroidX.test.ext.junitKtx)
    androidTestImplementation(AndroidX.test.espresso.core)
}
