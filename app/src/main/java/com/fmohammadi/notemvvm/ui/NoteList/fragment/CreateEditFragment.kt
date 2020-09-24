package com.fmohammadi.notemvvm.ui.NoteList.fragment

import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.fmohammadi.notemvvm.Data.db.NoteDatabase
import com.fmohammadi.notemvvm.Data.db.entities.NoteItem
import com.fmohammadi.notemvvm.Data.repositories.NoteRepository
import com.fmohammadi.notemvvm.R
import com.fmohammadi.notemvvm.other.NoteItemAdapter
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_ID
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_LONG_MODIFY
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_MODIFY
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_NOTES
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_TITLE
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModel
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_edit.*
import kotlinx.android.synthetic.main.fragment_create_edit.view.*

class CreateEditFragment() : Fragment() {
    private var bundle: Bundle? = null
    private var title: String? = null
    private var notes: String? = null
    private var modify: String? = null
    private var saveItem: NoteItem? = null
    private var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_edit, container, false)

        val database = NoteDatabase(view.context)
        val repository = NoteRepository(database)
        val factory = NoteViewModelFactory(repository)

        val viewModel: NoteViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(NoteViewModel::class.java)

        bundle = arguments
        val save = view.findViewById<TextView>(R.id.tvSave)
        val cancel = view.findViewById<TextView>(R.id.tvCancel)

        if (bundle != null) {
            view.tvLastModify.visibility = View.VISIBLE
            view.tvModify.visibility = View.VISIBLE

            title = bundle!!.getString(KEY_TITLE)
            notes = bundle!!.getString(KEY_NOTES)
            modify = bundle!!.getString(KEY_MODIFY)
            id = bundle!!.getString(KEY_ID)!!.toInt()

            view.etTitle.setText(title)
            view.etNotes.setText(notes)
            view.tvModify.text = "$modify"

            save.setOnClickListener {
                title = etTitle.text.trim().toString()
                notes = etNotes.text.trim().toString()

                if (title!!.isEmpty() || notes!!.isEmpty()) {
                    Toast.makeText(view.context, "Please Enter A Note", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                saveItem = NoteItem(title!!, notes!!, System.currentTimeMillis())
                viewModel.insert(saveItem!!)
                findNavController().popBackStack()
            }

            cancel.setOnClickListener {
                saveItem = NoteItem(title!!, notes!!, System.currentTimeMillis())
                viewModel.insert(saveItem!!)
                findNavController().popBackStack()
            }

        } else {
            save.setOnClickListener {
                title = etTitle.text.trim().toString()
                notes = etNotes.text.trim().toString()

                if (title!!.isEmpty() || notes!!.isEmpty()) {
                    Toast.makeText(view.context, "Please Enter A Note", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                saveItem = NoteItem(title!!, notes!!, System.currentTimeMillis())
                viewModel.insert(saveItem!!)
                findNavController().popBackStack()
            }

            cancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        return view
    }
}