package kz.school.devintensive.extensions

import kz.school.devintensive.models.User
import kz.school.devintensive.models.UserView
import kz.school.devintensive.utils.Utils

fun User.toUserView(): UserView {

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
//    val status = if(lastVisit == null) "Еще ни разу не был" else if(isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff(lastVisit)}"
    val status = null

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initials,
        avatar = avatar,
        status = status)
}