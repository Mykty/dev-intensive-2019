package kz.school.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.SECONDS -> value * SECOND
        TimeUnits.MINUTES -> value * MINUTE
        TimeUnits.HOURS -> value * HOUR
        TimeUnits.DAYS -> value * DAY
    }

    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    SECONDS,
    MINUTES,
    HOURS,
    DAYS
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val today = Date().format()
    val date = date.format()

    val todayTime = today.split(" ")[0].split(":")
    val todayDate = today.split(" ")[1].split(".")

    val userTime = date.split(" ")[0].split(":")
    val userDate = date.split(" ")[1].split(".")

    println(today)
    println(date)

    var reportTxt = "empty"

    /*
    0с - 1с "только что"
    1с - 45с "несколько секунд назад"
    45с - 75с "минуту назад"
    75с - 45мин "N минут назад"
    45мин - 75мин "час назад"
    75мин 22ч "N часов назад"
    22ч - 26ч "день назад"
    26ч - 360д "N дней назад"
     */

    when (val yearCalc = todayDate[2].toInt() - userDate[2].toInt()) {
        1 -> reportTxt = "больше года назад"
        in 2..4 -> reportTxt = "$yearCalc года назад"
        in 5..20 -> reportTxt = "$yearCalc лет назад"
        21 -> reportTxt = "$yearCalc год назад"
        in 22..24 -> reportTxt = "$yearCalc года назад"
        0 -> {

            when (val dayCalc = todayDate[0].toInt() - userDate[0].toInt()) {
                1 -> reportTxt = "день назад"
                0 -> {

                    when (val hourCalc = todayTime[0].toInt() - userTime[0].toInt()) {
                        1 -> reportTxt = "час назад"
                        in 5..22 -> reportTxt = "$hourCalc часов назад"
                        0 -> {
                            when (val minuteCalc =
                                todayTime[1].toInt() - userTime[1].toInt()) {
                                1 -> reportTxt = "минут назад"
                                in 2..4 -> reportTxt = "$minuteCalc минуты назад"
                                in 5..11 -> reportTxt = "$minuteCalc минут назад"
                                12 -> reportTxt = "$minuteCalc минуты назад"
                                in 13..40 -> reportTxt = "$minuteCalc минут назад"
                                41 -> reportTxt = "$minuteCalc минута назад"
                                in 42..44 -> reportTxt = "$minuteCalc минуты назад"
                                in 45..50 -> reportTxt = "$minuteCalc минут назад"
                                in 51..54 -> reportTxt = "$minuteCalc минуты назад"
                                in 55..59 -> reportTxt = "$minuteCalc минут назад"


                                0 -> {
                                    val secondCalc =
                                        todayTime[2].toInt() - userTime[2].toInt()
                                    when (secondCalc % 10) {
                                        0 -> {
                                            if (secondCalc >= 10) reportTxt =
                                                "$secondCalc секунд назад"
                                            else reportTxt = "только что"
                                        }

                                        1 -> reportTxt = "$secondCalc секунда назад"
                                        in 2..4 -> reportTxt = "$secondCalc секунды назад"
                                        in 5..9 -> reportTxt = "$secondCalc секунд назад"
                                    }
                                }
                            }
                        }

                        !in 5..20 -> reportTxt = "$hourCalc часа назад"
                    }
                }

                else -> reportTxt = "$dayCalc дней назад"
            }
        }
    }

    return reportTxt
}
