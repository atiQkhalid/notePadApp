package com.example.mynotes.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.mynotes.database.entity.Note
import com.example.mynotes.databinding.FragmentEditNoteBinding
import com.example.mynotes.extensions.dateTime
import com.example.mynotes.extensions.replaceFragment

class EditNoteFragment(private val note: Note) : Fragment() {

    lateinit var binding: FragmentEditNoteBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noteTitle.setText(note.title)
        binding.noteText.setText(note.text)

        binding.btUpdate.setOnClickListener {
            val updatedTitle = binding.noteTitle.text.toString()
            val updatedText = binding.noteText.text.toString()

            if (updatedText.isNotEmpty() && updatedTitle.isNotEmpty()) {
                viewModel.updateNote(Note(id = note.id, title = updatedTitle, text = updatedText, time = dateTime()))
                Toast.makeText(requireContext(), "$note is been updated", Toast.LENGTH_LONG).show()
                reset()
                replaceFragment(NoteFragment())

            } else {
                Toast.makeText(requireContext(), "Field can't be empty", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun reset() {
        binding.noteText.setText("")
        binding.noteTitle.setText("")
    }
}