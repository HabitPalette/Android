package com.example.habitpalette

import android.app.Application
import com.example.habitpalette.data.persistence.AppDatabase
import com.example.habitpalette.data.repository.HabitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HabitPaletteApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {AppDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {HabitRepository(database!!.HabitDao())}
}