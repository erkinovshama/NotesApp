package com.itacademy.notesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notesTable")
data class Notes(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") val notesTitle: String,
    @ColumnInfo(name = "description") val notesDescription: String,
    @ColumnInfo(name = "timestamp") val timeStamp: String
)