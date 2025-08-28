<div align="center">

![Banner](/images/editor_banner.jpg)
----
[![CI](https://github.com/Rosemoe/CodeEditor/actions/workflows/gradle.yml/badge.svg?event=push)](https://github.com/Rosemoe/CodeEditor/actions/workflows/gradle.yml)
[![GitHub license](https://img.shields.io/github/license/Rosemoe/CodeEditor)](https://github.com/Rosemoe/CodeEditor/blob/main/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.Rosemoe.sora-editor/editor.svg?label=Maven%20Central)]((https://search.maven.org/search?q=io.github.Rosemoe.sora-editor%20editor))   
[![Telegram](https://img.shields.io/badge/Join-Telegram-blue)](https://t.me/rosemoe_code_editor)
[![QQ](https://img.shields.io/badge/Join-QQ_Group-ff69b4)](https://jq.qq.com/?_wv=1027&k=n68uxQws)

sora-editor 是一款面向 Android 的高性能代码编辑器组件，提供语法高亮、自动补全、诊断标记、事件系统等丰富能力，并支持 TextMate、Monarch、Tree-sitter 以及可选的 LSP 集成。

</div>

阅读其他语言版本: [English](README.md), [简体中文](README.zh-cn.md), [Español](README.es.md), [日本語](README.jp.md)

***项目仍在持续开发中，欢迎提交 Issue 和 PR***

### 核心特性

- [x] 增量语法高亮
- [x] 智能自动补全（支持 [Code Snippets](https://macromates.com/manual/en/snippets)）
- [x] 自动缩进与代码块辅助线
- [x] 撤销/重做、搜索/替换、自动换行
- [x] 显示不可打印字符、文本放大镜、粘性滚动
- [x] 括号对高亮、诊断信息标记
- [x] 事件系统（可订阅编辑器事件）
- [x] TextMate 与 Tree-sitter 语言解析
- [x] 可选 Monarch 语法模块与 LSP（语言服务器）模块

### 模块概览

- `editor`: 核心编辑器控件与基础 API
- `language-textmate`: TextMate 语法/主题支持
- `language-monarch`: Monarch 语法支持（与 VS Code Monaco 类似）
- `language-treesitter`: Tree-sitter 解析器集成
- `language-java`: Java 语言示例实现
- `editor-lsp`: LSP 客户端集成（实验性，提供补全/格式化/诊断等）
- `app`: 演示应用
- `bom`: 平台 BOM（对齐各模块版本）

### 平台与要求

- JDK: 17
- Android Gradle Plugin: 见仓库构建配置
- compileSdk: 35，targetSdk: 35
- minSdk: 21（`editor-lsp` 等高 API 模块 minSdk 26）

### 通过 Maven Central 安装

推荐使用 BOM 对齐版本（以 0.23.5 为例）。

Gradle Kotlin DSL:

```kotlin
dependencies {
    implementation(platform("io.github.Rosemoe.sora-editor:bom:0.23.5"))

    // 核心编辑器
    implementation("io.github.Rosemoe.sora-editor:editor")

    // 按需选择语言/扩展模块
    implementation("io.github.Rosemoe.sora-editor:language-textmate")
    implementation("io.github.Rosemoe.sora-editor:language-monarch")
    implementation("io.github.Rosemoe.sora-editor:language-treesitter")
    implementation("io.github.Rosemoe.sora-editor:editor-lsp") // 需 minSdk 26
}
```

如不使用 BOM，可直接指定版本：`io.github.Rosemoe.sora-editor:editor:0.23.5`。

### 快速上手

1) 在布局中添加编辑器控件：

```xml
<io.github.rosemoe.sora.widget.CodeEditor
    android:id="@+id/editor"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:lnPanelPosition="center"
    app:lnPanelPositionMode="follow" />
```

2) 在代码中设置语言与文本（示例为 TextMate/Java）：

```kotlin
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage

val editor: CodeEditor = findViewById(R.id.editor)
val language = TextMateLanguage.create("source.java", true)
editor.setEditorLanguage(language)
editor.setText("""
public class Hello {\n  public static void main(String[] args) {\n    System.out.println("Hello, World");\n  }\n}
""".trimIndent())
```

也可以按需使用：

- Java 语言（示例实现）：`editor.setEditorLanguage(JavaLanguage())`
- Monarch 语言：`editor.setEditorLanguage(MonarchLanguage.create(...))`
- Tree-sitter 语言：`editor.setEditorLanguage(TsLanguage(...))`

LSP 集成（实验性）在 `editor-lsp` 模块中，支持 `textDocument/completion`、`textDocument/formatting`、`textDocument/diagnostic` 等能力，推荐通过 socket 方式连接独立语言服务进程。

### 文档

- 快速开始: `https://project-sora.github.io/sora-editor-docs/guide/getting-started`
- 全部文档: `https://project-sora.github.io/sora-editor-docs/`
- 入口推荐：
  - 编辑器概览: `https://project-sora.github.io/sora-editor-docs/guide/editor-overview`
  - 属性参考: `https://project-sora.github.io/sora-editor-docs/reference/xml-attributes`
  - 文档仓库: `https://github.com/project-sora/sora-editor-docs`

### 截图

<div style="overflow: hidden">
<img src="/images/general.jpg" alt="GeneralAppearance" width="40%" align="bottom" />
<img src="/images/problem_indicators.jpg" alt="ProblemIndicator" width="40%" align="bottom" />
</div>

### 从源码构建与演示

```bash
git clone https://github.com/Rosemoe/sora-editor.git
cd sora-editor
./gradlew :app:installDebug   # 安装演示应用
# 或构建所有可发布的 AAR
./gradlew bundleAll
```

> 构建要求：请确保本地已安装 JDK 17（Gradle 将使用 17 号工具链）。如未安装，可在 `gradle.properties` 启用工具链自动下载：
>
> ```
> org.gradle.java.installations.auto-detect=true
> org.gradle.java.installations.auto-download=true
> ```
>
> 提示：演示应用使用了已配置的签名文件，仅供开发测试使用。

### 讨论与支持

- QQ 群: `734652304`（`https://qm.qq.com/q/kKBqRsVrQ4`）
- Telegram: `https://t.me/rosemoe_code_editor`

### 贡献者

<a href="https://github.com/Rosemoe/sora-editor/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Rosemoe/sora-editor" />
</a>

### 许可证

```
sora-editor - the awesome code editor for Android
https://github.com/Rosemoe/sora-editor
Copyright (C) 2020-2024  Rosemoe

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
USA

Please contact Rosemoe by email 2073412493@qq.com if you need
additional information or have any questions
```

### 鸣谢

感谢 [JetBrains](https://www.jetbrains.com/?from=CodeEditor) 为本项目提供的 [IntelliJ IDEA](https://www.jetbrains.com/idea/?from=CodeEditor) 等 IDE 的免费开源授权。

[<img src=".github/jetbrains-variant-3.png" width="200"/>](https://www.jetbrains.com/?from=CodeEditor)
