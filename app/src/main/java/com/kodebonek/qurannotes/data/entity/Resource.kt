package com.kodebonek.qurannotes.data.entity

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

class Resource<T>(val status: Status,
                  val data: T? = null,
                  val message: String? = null) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(msg: String?): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING)
        }
    }
}