package com.itacademy.notesapp.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.notesapp.models.Notes
import com.itacademy.notesapp.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {

    private val repository = NotesRepository()
    val allNotes: MutableLiveData<Notes> = MutableLiveData()

    fun getAll(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll(notes).let {
                allNotes.postValue(it?.get(1))
            }
        }

        fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
            repository.delete(notes)
        }

        fun updateNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
            repository.update(notes)
        }

        fun addNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
            repository.insert(notes)
        }
    }
}