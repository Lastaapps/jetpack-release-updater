package cz.lastaapps.jetpackreleaseupdater

import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/**
 * Checks all the libs given
 * @return map with entries - library as the key and boolean value with the resutl
 * */
fun checkAllLibs(libs: List<Library>): HashMap<Library, Boolean> {

    val map = HashMap<Library, Boolean>()

    for (lib in libs) {
        map[lib] = checkLib(lib)
    }

    return map
}

/**
 * Check lib's urls validity
 * @return if both urls are valid
 * */
private fun checkLib(lib: Library): Boolean {

    var failed = false

    if (!checkUrl(lib.groupIndexUrl)) {
        System.err.println("Check failed group - ${lib.packageName}:\t ${lib.groupIndexUrl}")
        failed = true
    }

    if (!checkUrl(lib.releasePageUrl)) {
        System.err.println("Check failed release - ${lib.packageName}:\t ${lib.releasePageUrl}")
        failed = true
    }

    return !failed
}

/**
 * Check specific url
 * @return if the urls is valid
 * */
private fun checkUrl(url: String): Boolean {

    if (url == "") return false

    return try {
        val urlConn = URL(url).openConnection() as HttpURLConnection
        urlConn.requestMethod = "HEAD"
        urlConn.instanceFollowRedirects = false
        urlConn.connect()

        if (urlConn.responseCode != 200) throw IllegalArgumentException("Wrong response")

        urlConn.disconnect()

        true
    } catch (e: Exception) {
        //e.printStackTrace()
        false
    }
}
