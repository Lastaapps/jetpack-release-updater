package cz.lastaapps.jetpackreleaseupdater

/**
 * Filter packages based on the options in Main.kt
 * */
fun filter(packages: List<String>): List<String> {
    return packages.filter {
        when {
            it.startsWith("androidx") -> androidx
            it.startsWith("android.arch") -> androidArch
            it.startsWith("com.android") -> android
            it.startsWith("google") -> google
            else -> others
        }
    }
}