package ir.matin.matinfilm.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.matin.matinfilm.BuildConfig
import ir.matin.matinfilm.data.network.ApiService
import ir.matin.matinfilm.utils.API_KEY
import ir.matin.matinfilm.utils.AUTHORIZATION
import ir.matin.matinfilm.utils.BASE_URL
import ir.matin.matinfilm.utils.CONNECTION_TIME
import ir.matin.matinfilm.utils.PING_INTERVAL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiService =
               Retrofit.Builder().
               baseUrl(baseUrl)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .client(client)
              .build()
                   .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideClient(@Timeout timeout: Long, @PingInterval ping : Long, interceptor: HttpLoggingInterceptor)=
        OkHttpClient.Builder()
            .addInterceptor { chain->
                chain.proceed(
                    chain.request().newBuilder().also {
                        it.addHeader(AUTHORIZATION , API_KEY)
                    }.build(),
                )
            }.also {
                it.addInterceptor(interceptor)
            }    .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .pingInterval(ping, TimeUnit.SECONDS)
            .build()

            @Provides
            @Singleton
            fun provideInterceptor()= HttpLoggingInterceptor().apply {
                level= if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
    @Provides
    @Singleton
    @Timeout
    fun provideTimeout() = CONNECTION_TIME

    @Provides
    @Singleton
    @PingInterval
    fun providePingInterval() = PING_INTERVAL

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }


}