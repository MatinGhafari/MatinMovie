plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
}

android {
    namespace = "ir.matin.matinfilm"
    compileSdk = 35

    defaultConfig {
        applicationId = "ir.matin.matinfilm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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


    // اضافه کردن dependency برای androidx.fragment
    implementation("androidx.fragment:fragment-ktx:1.6.0")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Retrofit for network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Retrofit Gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Dagger-Hilt for dependency injection
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Kotlin coroutines for asynchronous programming
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Android Lifecycle components
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")

    // Gson for JSON parsing
    implementation("com.google.code.gson:gson:2.10.1")

    // Jsoup for HTML parsing
    implementation("org.jsoup:jsoup:1.16.1")

    // OkHttp logging interceptor (optional, for logging network requests)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    //Room database
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //Shimmer
    implementation ("com.github.mikekpl:shimmer-recyclerview-x:2.0.0")

    //Veil
    implementation("com.github.skydoves:androidveil:1.1.4")

    //Rating Bar
    implementation ("me.zhanghai.android.materialratingbar:library:1.4.0")

    //Android Youtube Player
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")


}