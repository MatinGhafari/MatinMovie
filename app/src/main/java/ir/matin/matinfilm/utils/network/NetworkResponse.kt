package ir.matin.matinfilm.utils.network

import com.google.gson.Gson
import ir.matin.matinfilm.data.model.ErrorResponse
import retrofit2.Response


open class NetworkResponse <T>(private val response: Response<T>) {

    open fun generateResponse(): NetworkRequest<T> {
        return when {
            response.code() == 401 -> NetworkRequest.Error("You are not authorized in app")
            response.code() == 422 -> {
                var errorMessage = ""
                if (response.errorBody() != null) {
                    val errorResponse = Gson().fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
                    errorMessage=errorResponse.statusMessage?:"null"

                }
                NetworkRequest.Error(errorMessage)
            }

            response.code() == 500 -> NetworkRequest.Error("Try again")
            response.isSuccessful -> NetworkRequest.Success(response.body()!!)
            else -> NetworkRequest.Error(response.message())
        }
    }
}