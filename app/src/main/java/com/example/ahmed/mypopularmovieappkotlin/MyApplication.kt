package com.example.ahmed.mypopularmovieappkotlin

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    companion object {
        lateinit var api: API
    //   lateinit var appComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_Movie)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(API::class.java)

//        appComponent = DaggerAppComponent.builder()
//          .appModule(AppModule(this))
//               .build()

    }


}