package com.example.elegantcode.dsl

class Td {
    var content = ""
    fun html() = "\n\t\t<td>$content</td>"
}

class Tr {
    private val children = ArrayList<Td>()

    fun td(block: Td.() -> String) {
        val td = Td()
        td.content = td.block()
        children.add(td)
    }

    fun html(): String {
        val builder = StringBuilder()
        builder.apply {
            append("\n\t<tr>")
            for (childTag in children) {
                append(childTag.html())
            }
            append("\n\t</tr>")
        }
        return builder.toString()
    }
}

class Table {
    private val children = ArrayList<Tr>()

    fun tr(block: Tr.() -> Unit) {
        val tr = Tr()
        tr.block()
        children.add(tr)
    }

    fun html(): String {
        return with(StringBuilder()) {
            append("<table>")
            for (childTag in children) {
                append(childTag.html())
            }
            append("\n</table>")
            toString()
        }
    }
}

fun table(block: Table.() -> Unit): Table {
    val table = Table()
    table.block()
    return table
}

fun main() {
    val table =
        table {
            tr {
                td { "Apple" }
                td { "Grape" }
                td { "Orange" }
            }

            tr {
                td { "Pear" }
                td { "Banana" }
                td { "Watermelon" }
            }
        }

    println(table.html())
}