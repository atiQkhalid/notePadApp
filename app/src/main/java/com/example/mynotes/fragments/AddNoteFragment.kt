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
import com.example.mynotes.databinding.FragmentAddNoteBinding
import com.example.mynotes.extensions.dateTime
import com.example.mynotes.extensions.replaceFragment

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btAdd = binding.btAdd
        btAdd.setOnClickListener {

            val text = binding.etNote.text.toString()
            val title = binding.etTitle.text.toString()

            if (text.isNotEmpty() && title.isNotEmpty()) {
                viewModel.insertNote(Note(title = title, text = text, time = dateTime()))
                Toast.makeText(requireContext(), "$title is been Created", Toast.LENGTH_LONG).show()
                reset()
                replaceFragment(NoteFragment())

            } else {
                Toast.makeText(requireContext(), "Field can't be empty", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun reset() {
        binding.etNote.setText("")
        binding.etTitle.setText("")
    }
}