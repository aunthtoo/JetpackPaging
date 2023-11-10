import org.jetbrains.kotlin.konan.properties.loadProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.devtools.ksp")
}

val apiKey: String =
  loadProperties(File(rootDir, "local.properties").path).getProperty("apiKey")

android {
  namespace = "com.aunkhtoo.jetpackpaging"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.aunkhtoo.jetpackpaging"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildConfigField("String", "API_KEY", apiKey)
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.3"
  }

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

//noinspection UseTomlInstead
dependencies {
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
  implementation("androidx.activity:activity-compose:1.8.0")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

  implementation(platform("androidx.compose:compose-bom:2023.10.01"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")

  //paging
  implementation("androidx.paging:paging-compose:3.2.1")

  //coil
  implementation("io.coil-kt:coil-compose:2.5.0")

  //networking
  //okhttp
  implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
  implementation("com.squareup.okhttp3:okhttp")
  implementation("com.squareup.okhttp3:logging-interceptor")
  //retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
  //moshi
  implementation("com.squareup.moshi:moshi-adapters:1.14.0")
  ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
  implementation("com.squareup.moshi:moshi-kotlin:1.14.0")

  //timber
  implementation("com.jakewharton.timber:timber:5.0.1")

  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}