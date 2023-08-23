package com.example.noteapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface NoteDao {
    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note:Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes():LiveData<List<Note>>
}