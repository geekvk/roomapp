package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date

class AddEditActivity : AppCompatActivity() {
    lateinit var title:EditText
    lateinit var desc:EditText
    lateinit var add: Button
    lateinit var viewModel: NoteViewModel
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)
        title = findViewById(R.id.titleTxt)
        desc = findViewById(R.id.descTxt)
        add = findViewById(R.id.addNote)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        val type =  intent.getStringExtra("noteType")
        if (type.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc =  intent.getStringExtra("noteDes")
            noteId =  intent.getIntExtra("noteID", -1)

            add.text = "Update the Note"
            title.setText(noteTitle)
            desc.setText(noteDesc)
        }else{
            add.text = "Add New Note"
        }

        add.setOnClickListener {
            val noteTitle = title.text.toString()
            val noteDesc = desc.text.toString()

            if (type.equals("Edit")){
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MM, yyyyy - HH mm")
                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(
                        noteTitle,
                        noteDesc,
                        currentDate
                    )
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "${updateNote.title} is Updated", Toast.LENGTH_LONG).show()
                }
            }else{
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MM, yyyyy - HH mm")
                    val currentDate:String = sdf.format(Date())
                    val newNote = Note(
                        noteTitle,
                        noteDesc,
                        currentDate
                    )
                    newNote.id = noteId
                    viewModel.insertNote(newNote)
                    Toast.makeText(this, "${newNote.title} is Created", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }


    }
}