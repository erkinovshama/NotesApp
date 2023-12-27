package com.itacademy.notesapp

import android.app.Application
import androidx.room.Room
import com.itacademy.notesapp.room.NotesDatabase

class App: Application() {
    private var database: NotesDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, NotesDatabase::class.java, "database")
            .allowMainThreadQueries().build()
    }

    fun getDatabase(): NotesDatabase?{
        return database
    }

    companion object{
        var instance: App? = null
    }
}