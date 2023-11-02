

import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
   id("androidx.navigation.safeargs.kotlin")

//    id("com.google.devtools.ksp")



}
val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties =  Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))


android {

    namespace = "com.example.news_mvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.news_mvvm"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String","MY_KEY",apikeyProperties["MY_KEY"].toString())
        buildConfigField("String","MY_URL",apikeyProperties["MY_URL"].toString())
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled= false
        }
        release {

            isMinifyEnabled = true
            isShrinkResources = true
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
        freeCompilerArgs = arrayOf(
            "-Xjvm-default=all",
        ).toList()
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }

    kapt {
        correctErrorTypes = true
        useBuildCache = true
    }
    hilt {
        enableTransformForLocalTests = true
        enableExperimentalClasspathAggregation = true
    }

//    kapt {
//        generateStubs = true
//    }
}

dependencies {
    val lifecycle_version = "2.6.2"

    val room_version = "2.6.0"
    val retrofit_version = "2.9.0"
    val glide_version = "4.16.0"

    val nav_version = "2.7.4"
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    kapt ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")



    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")


//room
    implementation("androidx.room:room-runtime:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
    //glide
    implementation ("com.github.bumptech.glide:glide:$glide_version")
    kapt ("com.github.bumptech.glide:ksp:$glide_version")
    //mock webServer
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
    testImplementation ("com.google.truth:truth:1.1.4")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    // Kotlin navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

}