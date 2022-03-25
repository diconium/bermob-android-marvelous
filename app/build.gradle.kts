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

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

// Kotlin
    implementation(KotlinX.coroutines.android)
    implementation(KotlinX.serialization.json)

// Android
    implementation(AndroidX.core)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.constraintLayout)

// HTTP
    implementation(Square.okHttp3)
    implementation(Square.retrofit2)

// Testing
    testImplementation(Testing.junit4)
    testImplementation(KotlinX.coroutines.test)

    androidTestImplementation(AndroidX.test.ext.junitKtx)
    androidTestImplementation(AndroidX.test.espresso.core)
}
