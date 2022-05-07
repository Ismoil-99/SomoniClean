plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-android-extensions")
    id ("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId  = "com.example.somoni"
        minSdk  = 21
        targetSdk =32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility  = JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.Kotlin.ktx)
    implementation (Dependencies.UI.appCompat)
    implementation (Dependencies.UI.material)
    implementation (Dependencies.UI.constraintlayout)
    testImplementation (Dependencies.Tests.testImplementation)
    androidTestImplementation(Dependencies.Tests.androidTestImplementation_junit)
    androidTestImplementation (Dependencies.Tests.androidTestImplementation_espresso)


    //navigation
    implementation(Dependencies.NavigationComponent.navigation_ui)
    implementation(Dependencies.NavigationComponent.navigation_fragment)
    implementation(Dependencies.NavigationComponent.navigation_dynamic_features)

    //retrofit
    implementation(Dependencies.Retrofit.retrofit2)
    implementation(Dependencies.Retrofit.retrofit2_converter_moshi)
    implementation(Dependencies.Retrofit.retrofit2_converter_gson)

    //lifecycle
    implementation(Dependencies.Lifecycle.lifecycle_viewmodel)
    implementation(Dependencies.Lifecycle.lifecycle_livedata)
    implementation(Dependencies.Lifecycle.kapt)

    //coroutines
    implementation(Dependencies.Coroutines.coroutines)

    //hilt
    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.kapt)
}
kapt{
    correctErrorTypes = true
}