package cz.lastaapps.jetpackreleaseupdater

import java.net.URL

import java.io.InputStreamReader

import java.io.BufferedReader
import java.net.HttpURLConnection

/**
 * Downloads and parses main-index.xml from Google Maven Repository
 * */
fun loadMaster(): List<String> {

    val url = "https://dl.google.com/android/maven2/master-index.xml"

    val urlConn = URL(url).openConnection() as HttpURLConnection
    urlConn.connect()

    val xml = BufferedReader(InputStreamReader(urlConn.inputStream)).readText()

    urlConn.disconnect()

    return parseMaster(xml)
}

/**
 * Downloads and parses artifacts for package name given. The output is Library object
 * */
fun loadArtifact(name: String): Library {
    //url in maven repository, where group-index is located
    val url = artifactsUrl(name)
    //developers.android.com site with release notes
    val releaseUrl = releaseUrl(name)
    val list = loadGroup(url)

    return Library(
        url,
        releaseUrl,
        name,
        list,
    )
}

/**
 * Downloads and parses artifacts for package name given
 * */
private fun loadGroup(url: String): List<String> {
    val urlConn = URL(url).openConnection() as HttpURLConnection
    urlConn.connect()

    val xml = BufferedReader(InputStreamReader(urlConn.inputStream)).readText()

    urlConn.disconnect()

    return parseGroup(xml)
}

/**
 * Creates url of the package given in google maven repository
 * */
private fun artifactsUrl(artifact: String): String {
    var url = "https://dl.google.com/dl/android/maven2/"
    val parts = artifact.split(".")

    for (part in parts)
        url += "$part/"

    url += "group-index.xml"
    return url
}

/**
 * Creates url to developers.android.com site with release notes
 * Works for androidx only, otherwise returns ""
 * */
private fun releaseUrl(artifact: String): String {
    val parts = artifact.split(".")
    var url = ""

    when  {
        artifact.startsWith("androidx") -> {
            url += "https://developer.android.com/jetpack/androidx/releases/"

            when {
                //test have different system - everything on one page
                artifact.contains("test.") -> url += "test"

                //all the other packages
                else -> {
                    for (part in parts.subList(1, parts.size))
                        url += "$part-"
                    url = url.substring(0, url.length - 1)
                }
            }
        }
    }

    return url
}
