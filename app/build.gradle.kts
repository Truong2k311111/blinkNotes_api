plugins {
    id("com.android.application") // Đây là cách sử dụng plugin id trong Kotlin DSL
    id("org.jetbrains.kotlin.android") // Nếu bạn dùng Kotlin
}

android {
    compileSdkVersion(33)

    defaultConfig {
        applicationId = "com.example.app"
        minSdkVersion(21)
        targetSdkVersion(33)
    }

    buildTypes {
        release {
            //minifyEnabled = false
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
}
