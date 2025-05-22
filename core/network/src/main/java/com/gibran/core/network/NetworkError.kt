package com.gibran.core.network

sealed class NetworkError : Throwable() {
    data object NoInternet : NetworkError()
    data object Timeout : NetworkError()
    data class Http(val code: Int, override val message: String) : NetworkError()
    data object Unknown : NetworkError()
}
