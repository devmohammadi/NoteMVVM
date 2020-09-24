package com.fmohammadi.notemvvm.Data.repositories

import com.fmohammadi.notemvvm.Data.db.NoteDatabase
import com.fmohammadi.notemvvm.Data.db.entities.NoteItem

class NoteRepository(private val db: NoteDatabase) {
    suspend fun delete(item: NoteItem) = db.getNote().delete(item)

    suspend fun insert(item: NoteItem) = db.getNote().insert(item)

    fun getAllNoteItems() = db.getNote().getAllNoteItems()
}