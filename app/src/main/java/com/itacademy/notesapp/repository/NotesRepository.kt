package com.itacademy.notesapp.repository

import com.itacademy.notesapp.App
import com.itacademy.notesapp.models.Notes

class NotesRepository {
    private val db = App.instance?.getDatabase()
    private val dao = db?.notesDao()

    suspend fun insert(notes: Notes) {
        dao?.insert(notes)
    }

    suspend fun delete(notes: Notes) {
        dao?.delete(notes)
    }

    suspend fun update(notes: Notes) {
        dao?.update(notes)
    }

    suspend fun getAll(notes: Notes): List<Notes>? {
        return dao?.getAllNotes()
    }

}