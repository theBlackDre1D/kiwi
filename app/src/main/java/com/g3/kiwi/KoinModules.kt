package com.g3.kiwi

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.g3.kiwi.api.FlightRequests
import com.g3.kiwi.database.FlightDatabase
import com.g3.kiwi.repositories.FlightRepositoryImp
import com.g3.kiwi.repositories.interfaces.FlightRepository
import com.g3.kiwi.screens.home.fragments.HomeFragmentViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val koinModules = module {

    // ViewModels
    viewModel { (handle: SavedStateHandle) -> HomeFragmentViewModel(handle, get()) }

    // Repositories
    single<FlightRepository> { FlightRepositoryImp( get(), get() ) }

    // Retrofit
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY;
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl("https://api.skypicker.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(FlightRequests::class.java)
    }

    // Room
    single { Room.databaseBuilder(get(), FlightDatabase::class.java, "flightsDatabase" ).build() }
    single { get<FlightDatabase>().flightDao() }
}