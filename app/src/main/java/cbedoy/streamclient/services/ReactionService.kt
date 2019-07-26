package cbedoy.streamclient.services

import cbedoy.streamclient.retrofit.RetrofitService
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ReactionService {

    @Headers( "Content-Type: application/json;charset=UTF-8",
        "Stream-Auth-Type: jwt")
    @GET("reaction/activity_id/{lookup_value}/{kind}/?api_key=${RetrofitService.apiKey}")
    fun getReactionsAsync(
        @Path("lookup_value") lookupValue: String,
        @Path("kind") kind: String
    ) : Deferred<HashMap<String, Any>>
}