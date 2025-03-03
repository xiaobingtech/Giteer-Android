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
    id("com.google.devtools.ksp")
}

android {
    defaultConfig {
        applicationId = "io.github.rosemoe.sora.app"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        versionCode = Versions.versionCode
        versionName = Versions.versionName + "-" + System.currentTimeMillis()

        manifestPlaceholders.putAll(mapOf(
            "JPUSH_PKGNAME " to applicationId as Any,
            "JPUSH_APPKEY " to "0970c471173d9049eae6b865",
            "JPUSH_CHANNEL " to "default_developer"
        ))
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
        create("release") {
            storeFile = file("../keystore.jks")
            storePassword = "910529"
            keyAlias = "key"
            keyPassword = "910529"
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
    implementation(projects.languageMonarch)
    implementation(projects.editorLsp)
    implementation(projects.languageTreesitter)

    // Tree-sitter languages
    implementation(libs.tree.sitter.java)

    // Monarch Languages
    implementation(libs.monarch.language.pack)

    // Kotlin coroutines
    implementation(libs.kotlinx.coroutines)

    // Lua language server
    implementation(fileTree("dir" to "libs", "includes" to listOf("*.jar")))
    implementation(libs.lsp4j)

    debugImplementation(libs.leakcanary)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation(libs.jetpackmvvm)

    implementation(libs.mmkv)

    implementation(libs.webviewlib)

    implementation(libs.okhttp)
    implementation(libs.rxhttp)
    ksp(libs.rxhttp.compiler)

    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.rxlife.rxjava3)//管理RxJava3生命周期，页面销毁，关闭请求

    implementation(libs.utilcodex)

    implementation(libs.androidx.appcompat.v100)
    implementation(libs.refresh.layout.kernel)
    implementation(libs.refresh.header.classics)
    implementation(libs.refresh.footer.classics)
    implementation(libs.refresh.header.material)

    implementation(libs.baserecyclerviewadapterhelper4)

    implementation(libs.avatarimageview)

    implementation(libs.glide)
    ksp(libs.ksp)

    implementation(libs.core)
    implementation(libs.ext.tables)
    implementation(libs.image.glide)

    implementation(libs.atv)
    implementation(libs.print)

    implementation(libs.bigimageviewpager)
    implementation(libs.okhttp3.integration)

    implementation(libs.dkplayer.java)
    implementation(libs.dkplayer.ui)
    implementation(libs.player.exo)
    implementation(libs.videocache)

    implementation(libs.autolinktextviewv2)

    implementation(libs.xxpermissions)

    implementation(libs.loadsir)

    implementation(libs.chatkit)

    implementation(libs.jpush)

}
