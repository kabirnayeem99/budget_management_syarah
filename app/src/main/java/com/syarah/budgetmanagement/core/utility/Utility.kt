package com.syarah.budgetmanagement.core.utility

import java.util.Locale

fun Int.toArabic(): String {
    val string = this.toString()
    return string.toArabic()
}


fun String.toArabic(): String {
    val arabicNumbers = mapOf(
        "0" to "٠",
        "1" to "١",
        "2" to "٢",
        "3" to "٣",
        "4" to "٤",
        "5" to "٥",
        "6" to "٦",
        "7" to "٧",
        "8" to "٨",
        "9" to "٩"
    )

    val words = this.split(" ")
    val convertedWords = ArrayList<String>()

    for (word in words) {
        val convertedWord = StringBuilder()

        for (char in word) {
            val charString = char.toString()
            val convertedChar = arabicNumbers[charString]
            if (convertedChar != null) {
                convertedWord.append(convertedChar)
            } else {
                convertedWord.append(char)
            }
        }

        convertedWords.add(convertedWord.toString())
    }

    return convertedWords.joinToString(" ")
}