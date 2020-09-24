package com.fmohammadi.notemvvm.other

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fmohammadi.notemvvm.Data.db.entities.NoteItem
import com.fmohammadi.notemvvm.R
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModel
import kotlinx.android.synthetic.main.note_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class NoteItemAdapter(
    var items: List<NoteItem>,
    private val viewModel: NoteViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val KEY_ID = "id"
        const val KEY_TITLE = "title"
        const val KEY_NOTES = "notes"
        const val KEY_MODIFY = "modify"
        const val KEY_LONG_MODIFY = "longModify"
    }


    inner class NoteViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curNoteItem = items[position]

        holder.itemView.tvTitle.text = curNoteItem.title
        holder.itemView.tvNotes.text = curNoteItem.notes
        holder.itemView.tvModify.text = showTime(curNoteItem.modify)

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(curNoteItem)
        }

        holder.itemView.ivEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(KEY_ID, curNoteItem.id.toString())
            bundle.putString(KEY_TITLE, curNoteItem.title)
            bundle.putString(KEY_NOTES, curNoteItem.notes)
            bundle.putString(KEY_MODIFY, showTime(curNoteItem.modify))
            bundle.putString(KEY_LONG_MODIFY, curNoteItem.modify.toString())
            viewModel.delete(curNoteItem)
            holder.itemView.findNavController()
                .navigate(R.id.noteList_to_create, bundle)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun showTime(time: Long): String {
        val simple = SimpleDateFormat("  HH:mm         yyyy/MM/dd")
        val result = Date(time)
        return simple.format(result)
    }


    override fun getItemCount(): Int {
        return items.size
    }
}