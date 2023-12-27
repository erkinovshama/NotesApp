package com.itacademy.notesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itacademy.notesapp.R
import com.itacademy.notesapp.databinding.NotesItemBinding
import com.itacademy.notesapp.models.Notes

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    lateinit var noteClickDeleteInterface: NoteClickDeleteInterface
    lateinit var noteClickInterface: NoteClickInterface
    private val allNotes = ArrayList<Notes>()
    private lateinit var binding: NotesItemBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.tvNoteTitle.setText(allNotes.get(position).notesTitle)
        binding.tvTimeStamp.setText("Last Updated: " + allNotes.get(position).timeStamp)

        binding.ivDelete.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: Notes) {
        allNotes.clear()
        allNotes.addAll(listOf(newList))
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(notes: Notes)
}

interface NoteClickInterface {
    fun onNoteClick(notes: Notes)
}

