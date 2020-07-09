package com.thanmanhvinh.movieandnews.utils.exception

import com.thanmanhvinh.movieandnews.R

enum class AuthenticateException(val message: Int) {
    EMPTY_USERNAME(R.string.empty_username),
    EMPTY_PASSWORD(R.string.empty_password),
    USERNAME_TOO_SHORT(R.string.username_short),
    PASSWORD_TOO_SHORT(R.string.password_short),
    PLEASE_TRY_AGAIN(R.string.please_try_again)

}