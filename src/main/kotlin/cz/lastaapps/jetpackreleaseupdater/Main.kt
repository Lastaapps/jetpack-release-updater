package cz.lastaapps.jetpackreleaseupdater

//constants used for filtering packages
const val androidx = true
//these don't get release urls
const val androidArch = false
const val android = false
const val google = false
const val others = false

//if urls validity should be checked
const val validityCheck = true

//constants for the generated file
const val fileName = "AndroidXLibraryDataset" //without extension
const val packageName = "name.lmj0011.jetpackreleasetracker.helpers"
const val className = "AndroidXLibraryDataset"
const val libClassName = "AndroidXLibrary"

/**
 * Runs the whole process
 * */
fun main() {

    println("Running")

    println("Fetching the main index")
    val names = loadMaster()

    println("Filtering")
    val filtered = filter(names)

    println("Fetching artifacts")
    val libs = filtered.map { loadArtifact(it) }

    if (validityCheck) {
        println("Checking urls validity")
        checkAllLibs(libs)
    }

    println("Creating data")
    val fileContent = exportAll(libs)

    println("Exporting into the output file")
    val filePath = fileOutput(fileContent, "$fileName.kt")
    println("File path: $filePath")

    println()
    println("Done! \nHave a nice day.")
}