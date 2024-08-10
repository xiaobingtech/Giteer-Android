/*******************************************************************************
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2024  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 ******************************************************************************/

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.23-1.0.19"
}

android {
    defaultConfig {
        applicationId = "io.github.rosemoe.sora.app"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        versionCode = Versions.versionCode
        versionName = Versions.versionName + "-" + System.currentTimeMillis()
    }
    signingConfigs {
        create("general") {
            storeFile = file("../debug.jks")
            storePassword = "114514"
            keyAlias = "debug"
            keyPassword = "114514"
            this.enableV1Signing = true
            this.enableV2Signing = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("general")
            proguardFiles("proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("general")
            proguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    androidResources {
        additionalParameters.add("--warn-manifest-validation")
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    packaging {
        resources.pickFirsts.addAll(
            arrayOf(
                "license/README.dom.txt",
                "license/LICENSE.dom-documentation.txt",
                "license/NOTICE",
                "license/LICENSE.dom-software.txt",
                "license/LICENSE",
            )
        )
    }
    namespace = "io.github.rosemoe.sora.app"
}

kotlin {
    ksp {
        arg("rxhttp_rxjava", "3.1.6")
        arg("rxhttp_package", "rxhttp")  //指定RxHttp类包名，可随意指定
    }
}

dependencies {
    implementation(libs.androidx.constraintlayout)
    implementation(libs.gms.instantapps)
    implementation(libs.androidx.activity)

    // Desugar
    coreLibraryDesugaring(libs.desugar)

    // androidx & material
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.lifecycle.runtime)

    // Editor
    implementation(projects.editor)
    implementation(projects.languageJava)
    implementation(projects.languageTextmate)
    implementation(projects.editorLsp)
    implementation(projects.languageTreesitter)

    // Tree-sitter languages
    implementation(libs.tree.sitter.java)

    // Kotlin coroutines
    implementation(libs.kotlinx.coroutines)

    // Lua language server
    implementation(fileTree("dir" to "libs", "includes" to listOf("*.jar")))
    implementation(libs.lsp4j)

    debugImplementation(libs.leakcanary)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation("com.github.hegaojian:JetpackMvvm:1.2.7")

    implementation("com.tencent:mmkv:1.3.9")

    implementation("cn.yc:WebViewLib:1.4.0")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.github.liujingxing.rxhttp:rxhttp:3.2.7")
    ksp("com.github.liujingxing.rxhttp:rxhttp-compiler:3.2.7")

    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("com.github.liujingxing.rxlife:rxlife-rxjava3:2.2.2")//管理RxJava3生命周期，页面销毁，关闭请求

    implementation("com.blankj:utilcodex:1.31.1")
}
