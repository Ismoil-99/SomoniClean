plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    kotlin("android.extensions")
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.ktx)
    implementation (Dependencies.UI.appCompat)
    implementation (Dependencies.UI.material)
    implementation (Dependencies.UI.constraintlayout)
    implementation(Dependencies.Paging.paging)
    testImplementation (Dependencies.Tests.testImplementation)
    androidTestImplementation(Dependencies.Tests.androidTestImplementation_junit)
    androidTestImplementation (Dependencies.Tests.androidTestImplementation_espresso)

    implementation(Dependencies.Fragment.fragment)
    implementation(Dependencies.OtherDepend.delegate_viewbinding)


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
    implementation(Dependencies.Lifecycle.lifecycle_runtime)

    //coroutines
    implementation(Dependencies.Coroutines.coroutines)

    //hilt
    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.kapt)

    implementation(Dependencies.OtherDepend.guava)

}
kapt{
    correctErrorTypes = true
}