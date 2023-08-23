package com.example.noteapp

import androidx.lifecycle.LiveData

class NoteRepository(
    private val noteDao: NoteDao
) {
    val allNotes:LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun  insertNote(note:Note){
        noteDao.upsertNote(note)
    }
    suspend fun deleteNote(note:Note){
        noteDao.deleteNote(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
}