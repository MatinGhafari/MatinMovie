// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        // SafeArgs plugin
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.3")
    }
    repositories {
        maven ( url = uri("https://jitpack.io") )
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.8.3" apply false
}