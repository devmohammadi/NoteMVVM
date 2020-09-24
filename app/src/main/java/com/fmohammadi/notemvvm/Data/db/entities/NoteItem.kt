package com.fmohammadi.notemvvm.Data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "note_item")
data class NoteItem(
    @ColumnInfo(name = "item_title")
    var title: String,

    @ColumnInfo(name = "item_notes")
    var notes: String,

    @ColumnInfo(name = "item_modify")
    var modify: Long

)
{
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "item_id")
    var id: Int? = null

}
