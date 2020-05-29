package kz.school.devintensive.extensions

import kz.school.devintensive.models.User
import kz.school.devintensive.models.UserView
import kz.school.devintensive.utils.Utils

fun User.toUserView(): UserView {

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if(lastVisit == null) "Еще ни разу не был" else if(isOnline) "online" else "Последний раз был ${lastVisit!!.humanizeDiff(lastVisit!!)}"
    
    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initials,
        avatar = avatar,
        status = status)

}


//    data class Builder(
//        var id: String,
//        var firstName: String? = null,
//        var lastName: String? = null,
//        var avatar: String? = null,
//        var rating: Int = 0,
//        var respect: Int = 0,
//        var lastVisit: Date? = null,
//        var isOnline: Boolean = false
//    ) {
//        fun id(value: String) = apply { id = value }
//        fun firstName(value: String) = apply { firstName = value }
//        fun lastName(value: String) = apply { lastName = value }
//        fun avatar(value: String) = apply { avatar = value }
//        fun rating(value: Int) = apply { rating = value }
//        fun respect(value: Int) = apply { respect = value }
//        fun lastVisit(value: Date) = apply { lastVisit = value }
//        fun isOnline(value: Boolean) = apply { isOnline = value }
//
//        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
//    }