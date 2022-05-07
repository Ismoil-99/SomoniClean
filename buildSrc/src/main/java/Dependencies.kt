object Dependencies{
    object Kotlin{
        private const val ktx_version ="1.7.0"
        const val ktx = "androidx.core:core-ktx:$ktx_version"
    }

    object UI{
        const val appCompat_version = "1.4.1"
        const val material_version = "1.5.0"
        const val constraintlayout_version = "2.1.3"

        const val appCompat="androidx.appcompat:appcompat:$appCompat_version"
        const val material = "com.google.android.material:material:$material_version"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:$constraintlayout_version"
    }

    object Tests{
        const val testImplementation_version = "4.13.2"
        const val androidTestImplementation_junit_version = "1.1.3"
        const val androidTestImplementation_espresso_version = "3.4.0"


        const val testImplementation = "junit:junit:$testImplementation_version"
        const val androidTestImplementation_junit = "androidx.test.ext:junit:$androidTestImplementation_junit_version"
        const val androidTestImplementation_espresso = "androidx.test.espresso:espresso-core:$androidTestImplementation_espresso_version"
    }


    object NavigationComponent{
        const val nav_version = "2.4.2"

        const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        const val navigation_ui = "androidx.navigation:navigation-ui-ktx:$nav_version"
        const val navigation_dynamic_features = "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"
    }

    object Retrofit{
        const val retrofit_version = "2.9.0"

        const val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val retrofit2_converter_gson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
        const val retrofit2_converter_moshi  = "com.squareup.retrofit2:converter-moshi:$retrofit_version"
    }

    object Lifecycle{
        const val lifecycle_version = "2.3.1"
        //ViewModel
        const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

        //LievData
        const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

        // Annotation processor
        const val kapt = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"

    }

    object Coroutines{
        const val coroutines_version = "1.3.9"

        const val coroutines ="org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    }

    object Hilt{
        const val hilt_version = "2.38.1"

        const val hilt = "com.google.dagger:hilt-android:$hilt_version"
        const val kapt = "com.google.dagger:hilt-android-compiler:$hilt_version"
    }

    object OtherDepend{

    }
}