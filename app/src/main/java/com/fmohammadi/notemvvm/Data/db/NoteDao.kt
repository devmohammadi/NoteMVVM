package com.fmohammadi.notemvvm.Data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fmohammadi.notemvvm.Data.db.entities.NoteItem


@Dao
interface NoteDao {
    @Delete
    suspend fun delete(item: NoteItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: NoteItem)

    @Query("SELECT * FROM note_item")
    fun getAllNoteItems(): LiveData<List<NoteItem>>

}