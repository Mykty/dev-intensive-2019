package kz.school.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName?.trim()?.length == 0) {
            return null to null
        }
        var parts: List<String>? = fullName?.trim()?.split(" ")
        val firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
//        return Pair(firstName, lastName)

        return firstName to lastName
    }

    fun transliteration(payload: String): String {
        var nickName = ""
        for (c in payload) {
            if (c.isUpperCase()) {
                nickName += toLatinChar(c)
            } else {
                val lowerC = toLatinChar(c.toUpperCase()).toLowerCase(Locale.ROOT)
                nickName += lowerC
            }
        }

        return nickName
    }

    fun toLatinChar(char: Char): String {
        return when (char) {
            'А' -> "A"
            'Б' -> "B"
            'В' -> "V"
            'Г' -> "G"
            'Д' -> "D"
            'Е' -> "E"
            'Ё' -> "E"
            'Ж' -> "ZH"
            'З' -> "Z"
            'И' -> "I"
            'Й' -> "I"
            'К' -> "K"
            'Л' -> "L"
            'М' -> "M"
            'Н' -> "N"
            'О' -> "O"
            'П' -> "P"
            'Р' -> "R"
            'С' -> "S"
            'Т' -> "T"
            'У' -> "U"
            'Ф' -> "F"
            'Х' -> "H"
            'Ц' -> "C"
            'Ч' -> "CH"
            'Ш' -> "SH"
            'Щ' -> "SH"
            'Ъ' -> ""
            'Ы' -> "I"
            'Ь' -> ""
            'Э' -> "E"
            'Ю' -> "YU"
            'Я' -> "YA"
            ' ' -> "_"
            else -> char.toString()
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val res = "${firstName?.get(0)?.toUpperCase() ?: null}" + "${lastName?.get(0)?.toUpperCase() ?: ""} "
        return res
    }

}