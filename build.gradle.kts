// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version ("7.1.2") apply false
    id ("com.android.library") version ("7.1.2") apply false
    id ("org.jetbrains.kotlin.android") version ("1.6.20") apply false
    id ("org.jetbrains.kotlin.jvm") version ("1.6.20") apply false
}

buildscript {
    dependencies{
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${Dependencies.NavigationComponent.nav_version}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Dependencies.Hilt.hilt_version}")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
    }
}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}