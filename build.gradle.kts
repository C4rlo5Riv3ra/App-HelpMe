// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.navigation.safe.args.gradle.plugin)
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
}