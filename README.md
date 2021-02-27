# Jetpack release updater

This app has been created as an extension for [Jetpack release tracket](https://github.com/lmj0011/jetpack-release-tracker/) ([my fork](https://github.com/Lastaapps/jetpack-release-tracker)) for updating ```name.lmj0011.jetpackreleasetracker.helpers.AndroidXLibraryDataset.kt``` file. It creates Kotlin object with info about ```androidx``` packages in Google Maven repository. See an output example.

```kotlin
package name.lmj0011.jetpackreleasetracker.helpers

object AndroidXLibraryDataset {
    val data = listOf(
        AndroidXLibrary(
            "https://dl.google.com/android/maven2/androidx/activity/group-index.xml",
            "https://developer.android.com/jetpack/androidx/releases/activity",
            "androidx.activity",
            listOf("activity", "activity-compose", "activity-ktx"),
        ),
        AndroidXLibrary(
            "https://dl.google.com/android/maven2/androidx/ads/group-index.xml",
            "https://developer.android.com/jetpack/androidx/releases/ads",
            "androidx.ads",
            listOf("ads-identifier", "ads-identifier-common", "ads-identifier-provider"),
        ),
        AndroidXLibrary(
            "group-index.xml url",
            "developer.android.com release url",
            "package.name",
            listOf("artifat1", "artifat2", "artifat3"),
        ),...
    )
}
```

## Usage

You can just run main function in ```cz.lastaapps.jetpackreleaseupdater.Main.kt``` or update constants located in this file. Using them, you can customize output parameters like ```fileName```, ```packageName```, ```className``` and ```libClassName```.



## Validity check

App automatically checks if the URLs are valid. If not, you will be prompted in logs. You can disable this behavior in ```Main.kt``` . Know issues are, that ```androidx.gaming``` and ```androidx.ui``` generates 'invalid' URLs. But they still redirect to the right location.




## License
```
Copyright 2021 Petr Laštovička

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```