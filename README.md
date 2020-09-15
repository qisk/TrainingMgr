# TrainingMgr

## 产品简介
兴趣班管理工具，用一个不断迭代的实际产品，去验证各种Android新技术（组件化，JetPack，ButterKnife, Dagger，Material Design等），其中一些框架都用到了APT编译时技术。

## 开发环境
操作系统：macOS Catalina 10.15.6

IDE：Android Studio 4.1 Build #AI-193

Gradle版本：gradle-5.2.1（需要自行修改gradle-warpper的distributionUrl路径）

Gradle plugin版本：3.4.1

Android SDK版本：API 30（Android R）


## 分支描述
master：最新代码分支

components_empty_framework：组件化初始框架分支

components_customrouter_livedataevent：使用自定义Router实现组件间页面跳转，使用livedata实现组件间事件通讯


## Android新技术简介

### 组件化优势：
- 组件，既可以作为library，又可以单独作为application，便于单独编译单独测试，大大的提高了编译和开发效率；
- （业务）组件，可有自己独立的版本，业务线互不干扰，可单独编译、测试、打包、部署
- 各业务线共有的公共模块开发为组件，作为依赖库供各业务线调用，减少重复代码编写，减少冗余，便于维护
- 通过gradle配置文件，可对第三方库的引入进行统一管理，避免版本冲突，减少冗余库
- 通过gradle配置文件，可对各组件实现library与application间便捷切换，实现项目的按需加载

https://blog.csdn.net/hailong0529/article/details/89392064

### JetPack和AndroidX：

Jetpack 是一个由多个库组成的套件，可帮助开发者遵循最佳做法，减少样板代码并编写可在各种 Android 版本和设备中一致运行的代码，让开发者精力集中编写重要的代码。

AndroidX 概览
androidx 命名空间中的工件包含 Android Jetpack 库。与支持库一样，androidx 命名空间中的库与 Android 平台分开提供，并向后兼容各个Android 版本。

AndroidX 对原始Android支持库进行了重大改进，后者现在已不再维护。androidx软件包完全取代了支持库，不仅提供同等的功能，而且提供了新的库。

### ButterKnife：

​ButterKnife是注解中相对简单易懂的很不错的开源框架，是一个专注于Android系统的View注入框架, 以前总是要写很多findViewById来找到View对象，有了ButterKnife可以很轻松的省去这些步骤。使用ButterKnife对性能基本没有损失，因为ButterKnife用到的注解并不是在运行时反射的，而是在编译的时候生成新的class 。

### Dagger2：
Dagger2是编译时依赖注入框架，也就是说，这个框架解决的问题是，在编译阶段动态生成依赖注入代码，有别于其他依赖注入框架是利用反射或者运行时再动态生成字节码。

### Material Design：
中文名：材料设计语言，是由Google推出的全新的设计语言，谷歌表示，这种设计语言旨在为手机、平板电脑、台式机和“其他平台”提供更一致、更广泛的“外观和感觉”。
谷歌的想法是让谷歌平台上的开发者掌握这个新框架，从而让所有应用就有统一的外观，就像是苹果向开发者提出的设计原则一样。谷歌还基于这种新的设计语言对本公司旗舰应用进行了重新设计，包括安卓和网页端的Gmail和Calendar。经过了重新设计的Gmail，界面更干净、更简约。在安卓平台上，这种新界面被称为Material，支持各种新动画效果，具有内置的实时UI阴影，以及可在不同屏幕之间切换的hero元素。