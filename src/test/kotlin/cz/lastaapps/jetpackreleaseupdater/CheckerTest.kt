package cz.lastaapps.jetpackreleaseupdater

import com.google.common.truth.Truth
import org.junit.Test

class CheckerTest {

    @Test
    fun checkAllLibs() {

        val correct = Library(
            "https://dl.google.com/dl/android/maven2/androidx/annotation/group-index.xml",
            "https://developer.android.com/jetpack/androidx/releases/annotation",
            "cz.lastaapps.jetpackreleaseupdater.getAndroidx.annotation",
            listOf("annotation", "annotation-experimental", "annotation-experimental-lint")
        )
        val wrongGroup = Library(
            "https://dl.google.com/dl/android/maven2/ERROR/androidx/annotation/group-index.xml",
            "https://developer.android.com/jetpack/androidx/releases/annotation",
            "cz.lastaapps.jetpackreleaseupdater.getAndroidx.annotation",
            listOf("annotation", "annotation-experimental", "annotation-experimental-lint")
        )
        val wrongRelease = Library(
            "https://dl.google.com/android/maven2/android/arch/work/group-index.xml",
            "https://developer.android.com/jetpack/androidx/releases/arch/persistence/room/",
            "cz.lastaapps.jetpackreleaseupdater.getAndroid.arch.work",
            listOf("work-runtime-ktx", "work-firebase", "work-runtime", "work-testing", "work-rxjava2"),
        )

        val results = checkAllLibs(listOf(correct, wrongGroup, wrongRelease))

        Truth.assertThat(results[correct]).isTrue()
        Truth.assertThat(results[wrongGroup]).isFalse()
        Truth.assertThat(results[wrongRelease]).isFalse()
    }
}