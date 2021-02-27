package cz.lastaapps.jetpackreleaseupdater

import org.junit.Test

import com.google.common.truth.Truth.*

class ParserTest {

    @Test
    fun parseXML() {
        val xml = """
            <metadata>
              <cz.lastaapps.jetpackreleaseupdater.getAndroid.arch.core/>
              <cz.lastaapps.jetpackreleaseupdater.getAndroid.arch.lifecycle/>
            </metadata>
        """.trimIndent()
        val results = listOf("cz.lastaapps.jetpackreleaseupdater.getAndroid.arch.core", "cz.lastaapps.jetpackreleaseupdater.getAndroid.arch.lifecycle")

        val list = parseMaster(xml)

        assertThat(list).containsExactlyElementsIn(results)
    }

    @Test
    fun parseGroup() {
        val xml = """
            <cz.lastaapps.jetpackreleaseupdater.getAndroid.arch.persistence.room>
                <compiler versions="1.0.0-alpha1" />
                <support-db-impl versions="1.0.0-alpha1" />
            </cz.lastaapps.jetpackreleaseupdater.getAndroid.arch.persistence.room>
        """.trimIndent()
        val results = listOf("compiler", "support-db-impl")

        val list = parseGroup(xml)

        assertThat(list).containsExactlyElementsIn(results)
    }
}