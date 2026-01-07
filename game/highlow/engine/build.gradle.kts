plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    iosX64()
    iosSimulatorArm64()
    iosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":card:core"))
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.kotlin.test)
        }
    }
}