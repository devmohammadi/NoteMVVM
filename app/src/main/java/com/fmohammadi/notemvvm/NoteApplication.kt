package com.fmohammadi.notemvvm

import android.app.Application
import com.fmohammadi.notemvvm.Data.db.NoteDatabase
import com.fmohammadi.notemvvm.Data.repositories.NoteRepository
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class NoteApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@NoteApplication))
        bind() from singleton { NoteDatabase(instance()) }
        bind() from singleton { NoteRepository(instance()) }
        bind() from provider { NoteViewModelFactory(instance()) }
    }

}