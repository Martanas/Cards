plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    iosX64()
    iosSimulatorArm64()
    iosArm64()

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
