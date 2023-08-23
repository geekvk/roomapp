package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteAdapter.NoteOnClick, NoteAdapter.OnClickDelete {
    lateinit var notesList : RecyclerView
    lateinit var addFB: FloatingActionButton
    lateinit var viewModel: NoteViewModel
    lateinit var noteAdapter:NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesList = findViewById(R.id.itemList)
        addFB = findViewById(R.id.addBtn)

        notesList.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter(this,this,this)
        notesList.adapter = noteAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteAdapter.updateList(it)
            }
        }
        addFB.setOnClickListener{
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.title} is deleted" ,Toast.LENGTH_LONG).show()
    }

    override fun onIconClicked(note: Note) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDes", note.description)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()

    }
}