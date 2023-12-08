import com.android.build.gradle.internal.api.BaseVariantOutputImpl

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    plugins {
        alias(libs.plugins.androidApplication)
        alias(libs.plugins.kotlinAndroid)
        alias(libs.plugins.kotlin.parcelize)
        alias(libs.plugins.kotlin.kapt)
        alias(libs.plugins.kotlin.serialization)

        alias(libs.plugins.google.hilt)
        alias(libs.plugins.google.ksp)
    }
}

android {
    namespace = "ir.one_developer.batmanmovies"
    compileSdk = 34

    defaultConfig {
        applicationId = "ir.one_developer.batmanmovies"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }

        buildConfigField("String", "BASE_URL", "\"https://www.omdbapi.com\"")
    }

    buildTypes {
        release {
            isDebuggable = false
            multiDexEnabled = true
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            versionNameSuffix = "-DEBUG"
            applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    hilt {
        enableAggregatingTask = true
    }

    kapt {
        correctErrorTypes = true
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    applicationVariants.all {
        outputs.all {
            val variantOutputImpl = this as BaseVariantOutputImpl
            variantOutputImpl.outputFileName = "Batman-Movies-V${versionName}.apk"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.navigation.compose)

    // Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.bundles.hilt)

    // Room ORM
    ksp(libs.room.compiler)
    implementation(libs.bundles.room)

    implementation(libs.bundles.coil)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.accompanist)

    ksp(libs.squareup.moshi.codegen)
    implementation(libs.bundles.retrofit)
}