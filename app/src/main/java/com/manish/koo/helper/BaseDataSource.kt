package com.manish.koo.helper

import androidx.annotation.Keep
import com.manish.koo.remoteUtils.Resource
import com.manish.koo.remoteUtils.Status
import retrofit2.Call
import retrofit2.Response

@Keep
class BaseDataSource<T>(
    private var call: Call<T>? = null,
    private var isDuplicateSuccess: Boolean = false
) {

    class Builder<T> {
        private var call: Call<T>? = null
        private var isDuplicateSuccess: Boolean = false

        fun addCall(call: Call<T>) = apply { this.call = call }

        fun markDuplicateAsSuccess() = apply { isDuplicateSuccess = true }

        fun build() = BaseDataSource(call, isDuplicateSuccess)
    }

    fun execute(): Resource<T> {
        try {
            val result = call?.execute()
//            Logger.e(Thread.currentThread(), "execute_ka_result: ${Gson().toJson(result)}")
            return when {
                result?.isSuccessful!! -> buildSuccessResource(result)

                isServerError(result) -> buildFailResource(result, Status.SERVER_ERROR)
                isDuplicateEntry(result) -> {
                    if (isDuplicateSuccess) {
                        buildSuccessResource(result)
                    } else {
                        buildFailResource(result, Status.DUPLICATE_ENTRY)
                    }
                }
                isMissingParameter(result) -> buildFailResource(result, Status.MISSING_PARAMETER)

                else -> buildFailResource(result, Status.ERROR)
            }
        } catch (e: Exception) {
            return buildFailResource(null, Status.ERROR)
        }

    }

    private fun buildSuccessResource(result: Response<T>): Resource<T> {
        return Resource(
            status = Status.SUCCESS,
            data = result.body(),
            message = result.message(),
            code = result.code()
        )
    }

    private fun buildFailResource(result: Response<T>?, status: Status): Resource<T> {
        return Resource(
            status = status,
            data = null,
            message = result?.message() ?: "not connected",
            code = result?.code() ?: 500
        )
    }

    private fun isDuplicateEntry(response: Response<T>): Boolean {
        return response.code() in 402..410
    }

    private fun isMissingParameter(response: Response<T>): Boolean {
        return response.code() == 422
    }

    private fun isServerError(response: Response<T>): Boolean {
        return response.code() in 500..600
    }

}