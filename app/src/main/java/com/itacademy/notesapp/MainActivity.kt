package com.itacademy.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.itacademy.notesapp.adapters.NotesAdapter
import com.itacademy.notesapp.databinding.ActivityMainBinding
import com.itacademy.notesapp.models.Notes
import com.itacademy.notesapp.views.EditNoteActivity
import com.itacademy.notesapp.views.NotesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { NotesViewModel() }
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { NotesAdapter() }
    lateinit var notesRV : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesAdapter = adapter
        notesRV.adapter = notesAdapter
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.allNotes.observe(this, Observer{list ->
            list?.let {
                notesAdapter.updateList(it)
            }
        })
        binding.fabAddNote.setOnClickListener{
            val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    fun onNoteClick(notes: Notes) {
        val intent = Intent(this@MainActivity,EditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", notes.notesTitle)
        intent.putExtra("noteDescription", notes.notesDescription)
        intent.putExtra("noteID", notes.id)
        startActivity(intent)
    }

    fun onDeleteIconClick(notes: Notes) {
        viewModel.getAll(notes).let {
            viewModel.allNotes.observe(this){

            }

        }
        Toast.makeText(this, "${notes.notesTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}
