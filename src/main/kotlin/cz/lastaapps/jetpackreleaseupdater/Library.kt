package cz.lastaapps.jetpackreleaseupdater

data class Library (
    val groupIndexUrl: String,
    val releasePageUrl: String,
    val packageName: String,
    val artifacts: List<String>
)