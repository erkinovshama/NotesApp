package com.itacademy.notesapp.room

import androidx.room.*
import com.itacademy.notesapp.models.Notes

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Update
    suspend fun update(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Query("SELECT * FROM NotesTable")
    fun getAllNotes(): List<Notes>
}