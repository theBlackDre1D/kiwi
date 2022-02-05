package com.g3.kiwi

import androidx.room.Room
import com.g3.kiwi.api.SuggestionRequests
import com.g3.kiwi.database.FlightDatabase
import com.g3.kiwi.repositories.FlightRepositoryImp
import com.g3.kiwi.repositories.SuggestionRepositoryImpl
import com.g3.kiwi.repositories.interfaces.FlightRepository
import com.g3.kiwi.repositories.interfaces.SuggestionRepository
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
//    viewModel { (handle: SavedStateHandle) -> HomeFragmentViewModel(handle, get()) }
    viewModel { HomeFragmentViewModel(get() ) }

    // Repositories
    single<FlightRepository> { FlightRepositoryImp( get(), get() ) }
    single<SuggestionRepository> { SuggestionRepositoryImpl( get() ) }

    // Retrofit
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY;
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(SuggestionRequests::class.java)
    }

    // Room
    single { Room.databaseBuilder(get(), FlightDatabase::class.java, "flightsDatabase" ).build() }
    single { get<FlightDatabase>().flightDao() }
}