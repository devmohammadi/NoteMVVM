package com.fmohammadi.notemvvm.ui.NoteList

import androidx.lifecycle.ViewModel
import com.fmohammadi.notemvvm.Data.db.entities.NoteItem
import com.fmohammadi.notemvvm.Data.repositories.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun delete(item: NoteItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

   /* fun update(title: String, notes: String, modify: Long, id: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(title, notes, modify, id)
    }*/

    fun insert(item: NoteItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(item)
    }

    fun getAllNoteItems() = repository.getAllNoteItems()
}