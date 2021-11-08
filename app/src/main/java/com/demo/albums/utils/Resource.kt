package com.demo.albums.utils

/*Setting up the Utils - A generic class with 3 parameters in the constructor
* This class can be used to wrap it around any kind of object in <out T> and then easily it can check the status as
* Success, Error or Loading, and post observing we can get the data of that object using data : T
* */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}
/*Setting up the Utils - Enum class*/
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

