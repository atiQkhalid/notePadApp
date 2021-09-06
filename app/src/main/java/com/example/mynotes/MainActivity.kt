package com.example.mynotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.extensions.replaceFragmentSafely
import com.example.mynotes.fragments.NoteFragment
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        replaceFragmentSafely(NoteFragment())
    }
}

