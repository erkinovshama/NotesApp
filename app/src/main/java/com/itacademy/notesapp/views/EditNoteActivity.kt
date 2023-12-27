
package com.itacademy.notesapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.itacademy.notesapp.MainActivity
import com.itacademy.notesapp.R
import com.itacademy.notesapp.databinding.ActivityEditNoteBinding
import com.itacademy.notesapp.models.Notes
import java.text.SimpleDateFormat
import java.util.*

class EditNoteActivity : AppCompatActivity() {

    private val viewModel by lazy { NotesViewModel() }
    private lateinit var binding: ActivityEditNoteBinding
    var noteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        binding.viewModel = viewModel

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1)
            binding.btnAddUpdate.text = ("Update note")
            binding.editNoteTitle.setText(noteTitle)
            binding.editNoteDescription.setText(noteDesc)
        } else {
            binding.btnAddUpdate.text = ("Save Note")
        }

        binding.btnAddUpdate.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString()
            val noteDescription = binding.editNoteDescription.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val timestamp: String = sdf.format(Date())
                    val updateNote = Notes(0,
                        noteTitle,
                        noteDescription,
                        timestamp,
                    )
                    updateNote.id = noteID
                    viewModel.allNotes.observe(this){
                        print("currentdate: "+timestamp)
                        Toast.makeText(applicationContext, "Note Updated...", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(
                        Notes(0,
                            noteTitle,
                            noteDescription,
                            currentDate
                        )
                    )
                    Toast.makeText(applicationContext, "Note Added...",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

        // Listen KeyEvent when edittext on focus, prevent edittext to loose focus when Enter/Done key is pressed
        binding.editNoteDescription.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                Log.d("enter key ", "presssed")
                return@OnKeyListener true
            }
            false
        })
    }
}