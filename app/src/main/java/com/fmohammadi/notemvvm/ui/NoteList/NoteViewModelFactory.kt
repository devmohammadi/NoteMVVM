package com.fmohammadi.notemvvm.ui.NoteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmohammadi.notemvvm.Data.repositories.NoteRepository

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(private val repository: NoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(repository) as T
    }
}