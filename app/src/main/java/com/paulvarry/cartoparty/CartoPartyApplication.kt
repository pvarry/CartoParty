package com.paulvarry.cartoparty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.paulvarry.cartoparty.data_source.db.AppDatabase

@HiltAndroidApp
class CartoPartyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        AppDatabase.get(this)
    }
}
