package com.fmohammadi.notemvvm.ui.NoteList.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.fmohammadi.notemvvm.Data.db.NoteDatabase
import com.fmohammadi.notemvvm.Data.repositories.NoteRepository
import com.fmohammadi.notemvvm.R
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModel
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModelFactory
import com.fmohammadi.notemvvm.ui.NoteList.fragment.NoteListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}