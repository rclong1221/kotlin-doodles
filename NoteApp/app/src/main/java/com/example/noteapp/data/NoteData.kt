package com.example.noteapp.data

import com.example.noteapp.model.Note

class NoteDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "A good day1", description = "Yes it was"),
            Note(title = "A good day2", description = "Yes it was"),
            Note(title = "A good day3", description = "Yes it was"),
            Note(title = "A good day4", description = "Yes it was"),
            Note(title = "A good day5", description = "Yes it was")
        )
    }
}