import org.gradle.kotlin.dsl.ktlint
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.m68476521.weather.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.m68476521.weather.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

ktlint {
    android = true // Enable Android-specific linting rules
    ignoreFailures = false // Fail the build if KtLint finds any issues
    reporters {
        reporter(reporterType = ReporterType.PLAIN) // Output KtLint results in plain text format
        reporter(reporterType = ReporterType.CHECKSTYLE)
        reporter(reporterType = ReporterType.SARIF)
        reporter(reporterType = ReporterType.HTML) // Output KtLint results in HTML format
    }

    filter {
        exclude("**/build/generated/**")
        exclude { element ->
            val path = element.file.path
            path.contains("**/shared/build/generated/**") || path.contains("**/build/**")
        }
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}
