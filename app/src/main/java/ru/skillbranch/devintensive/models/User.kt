package kz.school.devintensive.models

import kz.school.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?,
    val rating: Int = 0,
    val respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    class Builder{
        private var lastId: Int = -1
        private var id: String = "-1"
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun id(value: String) = apply { id = value }
        fun firstName(value: String) = apply { firstName = value }
        fun lastName(value: String) = apply { lastName = value }
        fun avatar(value: String) = apply { avatar = value }
        fun rating(value: Int) = apply { rating = value }
        fun respect(value: Int) = apply { respect = value }
        fun lastVisit(value: Date) = apply { lastVisit = value }
        fun isOnline(value: Boolean) = apply { isOnline = value }

        fun build() = User(if(id == "-1") "${++lastId}" else "0", firstName, lastName, avatar, rating, respect, lastVisit, isOnline)

    }

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println("It's Alive!!! ${if (lastName === "Doe") "His name is $firstName $lastName" else "And his is $firstName $lastName"}")
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName);

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    fun printMe() = println(
        """
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
            """.trimIndent()
    )

}


