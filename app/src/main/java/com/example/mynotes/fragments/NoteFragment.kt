package com.example.mynotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.adapter.NoteAdapter
import com.example.mynotes.database.entity.Note
import androidx.lifecycle.Observer
import com.example.mynotes.MainActivity
import com.example.mynotes.adapter.InterfaceNoteAdapter
import com.example.mynotes.databinding.FragmentNoteBinding
import com.example.mynotes.extensions.replaceFragment

class NoteFragment : Fragment(), InterfaceNoteAdapter {

    private val viewModel: NoteViewModel by viewModels()
    lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btOpenAddNote.setOnClickListener{
            replaceFragment(AddNoteFragment())
        }
        val recyclerView = binding.rvNote
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = NoteAdapter(requireContext(), this)
        recyclerView.adapter = adapter


        viewModel.allNotes.observe(requireActivity(), Observer { list ->
            list?.let {
                adapter.updateNotes(it)
                if (it.isEmpty()){
                    binding.tvEmptyList.visibility = View.VISIBLE
                }
            }
        })

    }

    override fun onClickListener(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun editListener(note: Note) {
        Toast.makeText(requireContext(), "${note.title} is been pressed", Toast.LENGTH_SHORT).show()
        replaceFragment(EditNoteFragment(note))
    }

}