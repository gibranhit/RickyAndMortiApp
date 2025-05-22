package com.gibran.core.network

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.success(apiCall())
    } catch (e: IOException) {
        Result.failure(NetworkError.NoInternet)
    } catch (e: HttpException) {
        Result.failure(NetworkError.Http(e.code(), e.message()))
    } catch (e: Exception) {
        Result.failure(NetworkError.Unknown)
    }
}