package cz.lastaapps.jetpackreleaseupdater

import java.io.File
import java.nio.file.Files

/**
 * Saves data into a file
 * @param fileName can be file name or absolute path, same as File() constructor parameters
 * @return the absolute file path (path + name)
 * */
fun fileOutput(data: String, fileName: String): String {

    val file = File(fileName)
    if (!file.exists())
        file.createNewFile()

    Files.write(file.toPath(), data.toByteArray(Charsets.UTF_8))

    return file.absolutePath
}
