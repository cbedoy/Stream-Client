package cbedoy.streamclient.retrofit

import cbedoy.streamclient.providers.UtilsProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
    private val retrofit : Retrofit by lazy {
        init()
    }

    const val apiKey = "pxq7kudd57xc&id_lte=65bca63a-7d61-48e8-be9d-c6665a008196"

    private fun client(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", UtilsProvider.getToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("stream-auth-type", "jwt")
                .build()
            chain.proceed(newRequest)
        }.build()
    }

    private fun init() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.stream-io-api.com/api/v1.0/")
            .client(client())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}