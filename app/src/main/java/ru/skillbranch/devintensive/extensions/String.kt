package kz.school.devintensive.extensions

import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.truncate(index: Int): String {
    return "${this.substring(0, index)} "
}

fun String.stripHtml(): String {

    var res = this
    val rxsStore = mutableListOf("<[^>]*>", "/[a-z]")

    for (rxs in rxsStore){
        val p: Pattern = Pattern.compile(rxs)
        val m: Matcher = p.matcher(res)

        while (m.find()) {
            val oldValue = m.group(0)
            res = res.replace(oldValue!!, "")
        }
    }

    return res.trim()
}