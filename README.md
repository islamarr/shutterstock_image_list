# ShutterStock

About this project
--------------
🚀 List of awesome pictures from ShutterStock.

🛠 Simple implementation for MVVM architecture pattern and clean code.

🛠 SOLID principles.

🛠 Unit tests included.

🛠 Standard Coding Style.

🛠 Support Tablet screens.



Architecture pattern Used
--------------
[MVVM][1]

![architecture pattern][2]

Libraries Used
--------------

* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
* [Test][4] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
    * [Data Binding][11] - Declaratively bind observable data to UI elements.
    * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
    * [LiveData][13] - Build data objects that notify views when the underlying database changes.
    * [Navigation][14] - Handle everything needed for in-app navigation.
    * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
      asynchronous tasks for optimal execution.
    * [Repository][3] - A Module that handle data operations, You can consider repositories to be mediators between different data sources.
    * [Kotlin Flows][21] - A stream of data that can be computed asynchronously.
    * [Paging 3][22] - For loading and display pages of data from a larger dataset from local storage or over network.
* Third party and miscellaneous libraries
    * [Retrofit][20] - A simple library that is used for network transaction.
    * [Glide][90] For image loading.
    * [Hilt][92]: For [dependency injection][93]
    * [Kotlin Coroutines][91] For managing background threads with simplified code and reducing needs for callbacks.

Technical choices
--------------
**MVVM**
- Loose coupling between View & ViewModel, ViewModel has no reference to the View. So it isn't affected by configuration changes.
- Aware by lifecycle. ViewModel save data even after rotate mobile.
- Easy to Test.

**RecyclerView**
- In RecyclerView, it is mandatory to use ViewHolder pattern Which optimize the performance.
- DiffUtil callback Which optimize the performance.

**Activities vs Fragments**

- I have used a single-activity architecture which allowed me to take full advantage of the Navigation component, which mean that a single activity that manages and host multiple fragments.
- The fragment is more lite weight than Activity.

**Hilt vs Dagger2 vs Koin**

- Hilt is built on top of the Dagger, and it comes with some advantages like simplify Dagger code and create a standard set of components and scopes to ease setup.
- Hilt does not need factories for ViewModel, koin need.
- Hilt generate the code in the compile time, while koin in runtime. 

**Glide**

- Glide very effective for almost any case where you need to fetch, resize, cache and display a remote image.
- Support round pictures, thumbnail and placeholder which I needed in this project.


What's next
--------------
- I wish I could have implemented caching data, but found problems that will take some time, 
  so maybe complete it in the coming iteration.
- Also the UI needs to be much fancier.
- Unit tests are still need to be written.
- I wish I could have extend it to Clean Architecture, maybe update it in the coming iteration.

Other Projects
--------------
[Sona3][30]
[Recorder][31]
[Prayer Now][32]
[Mn Ahyaha][33]


[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/jetpack/guide
[2]: https://github.com/islamarr/GitHubRepos/blob/master/app/src/main/res/drawable/mvvm_diagram.png
[3]: https://developer.android.com/jetpack/guide#fetch-data
[4]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[20]: https://square.github.io/retrofit
[21]: https://developer.android.com/kotlin/flow
[22]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[30]: https://github.com/islamarr/Sona3
[31]: https://github.com/islamarr/recorder
[32]: https://play.google.com/store/apps/details?id=com.AppRocks.now.prayer
[33]: https://play.google.com/store/apps/details?id=com.Ihsan.Ahyaha
[90]: https://bumptech.github.io/glide/
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://developer.android.com/training/dependency-injection/hilt-android
[93]: https://developer.android.com/training/dependency-injection

