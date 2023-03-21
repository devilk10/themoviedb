package com.ketansa.themoviedb.util

object Constants {
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    object ErrorCodes {
        const val INVALID_RESPONSE = 100
        const val NO_INTERNET = 101
        const val INVALID_FORMAT = 102
        const val UNKNOWN_ERROR = 103
    }
}

object TestTag {
    const val CARD = "movie_card"
}
