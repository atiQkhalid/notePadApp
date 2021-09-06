package com.example.mynotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.database.entity.Note
import com.example.mynotes.databinding.ListItemBinding

class NoteAdapter(private val context: Context, private val click: InterfaceNoteAdapter) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvTitle
        val noteText = binding.tvNote
        val dateTime = binding.tvDateTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val vh = NoteViewHolder(binding)

        binding.btDelete.setOnClickListener {
            click.onClickListener(allNotes[vh.adapterPosition])
        }
        binding.btEdit.setOnClickListener {
            click.editListener(allNotes[vh.adapterPosition])
        }
        return vh
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.title?.text = currentNote.title
        holder.noteText?.text = currentNote.text
        holder.dateTime?.text = currentNote.time
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateNotes(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface InterfaceNoteAdapter {
    fun onClickListener(note: Note)
    fun editListener(note: Note)
}