package cz.lastaapps.jetpackreleaseupdater

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory
import java.io.ByteArrayInputStream

fun parseMaster(xml: String): List<String> = parseMaster(ByteArrayInputStream(xml.toByteArray()))

fun parseMaster(input: InputStream): List<String> {

    val dbFactory = DocumentBuilderFactory.newInstance()
    val dBuilder = dbFactory.newDocumentBuilder()
    val doc: Document = dBuilder.parse(input)
    doc.documentElement.normalize()

    val nList: NodeList = doc.getElementsByTagName("metadata").item(0).childNodes

    val list = ArrayList<String>()

    for (temp in 0 until nList.length) {
        val nNode: Node = nList.item(temp)

        if (nNode.nodeType == Node.ELEMENT_NODE)
            list.add(nNode.nodeName)
    }

    return list.sorted()
}

fun parseGroup(xml: String): List<String> = parseGroup(ByteArrayInputStream(xml.toByteArray()))

fun parseGroup(input: InputStream): List<String> {

    val dbFactory = DocumentBuilderFactory.newInstance()
    val dBuilder = dbFactory.newDocumentBuilder()
    val doc: Document = dBuilder.parse(input)
    doc.documentElement.normalize()

    val nList: NodeList = doc.childNodes.item(0).childNodes

    val list = ArrayList<String>()

    for (temp in 0 until nList.length) {
        val nNode: Node = nList.item(temp)

        if (nNode.nodeType == Node.ELEMENT_NODE)
            list.add(nNode.nodeName)
    }

    return list.sorted()
}
