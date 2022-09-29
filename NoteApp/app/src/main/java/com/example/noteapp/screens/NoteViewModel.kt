package com.example.noteapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Note

class NoteViewModel : ViewModel() {
    private var notes = mutableStateListOf<Note>()

    init {
        notes.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun removeNote(note: Note) {
        notes.remove(note)
    }

    fun getNotes() : List<Note> {
        return notes
    }
}