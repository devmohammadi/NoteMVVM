package com.fmohammadi.notemvvm.ui.NoteList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.fmohammadi.notemvvm.Data.db.entities.NoteItem
import com.fmohammadi.notemvvm.R
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_ID
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_LONG_MODIFY
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_MODIFY
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_NOTES
import com.fmohammadi.notemvvm.other.NoteItemAdapter.Companion.KEY_TITLE
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModel
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_edit.*
import kotlinx.android.synthetic.main.fragment_create_edit.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CreateEditFragment() : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val factory: NoteViewModelFactory by instance()

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
                if (saveNote(view, viewModel)) return@setOnClickListener
                findNavController().popBackStack()
            }

            cancel.setOnClickListener {
                saveItem =
                    NoteItem(title!!, notes!!, bundle!!.getString(KEY_LONG_MODIFY)!!.toLong())
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


    private fun saveNote(view: View, viewModel: NoteViewModel): Boolean {
        title = etTitle.text.trim().toString()
        notes = etNotes.text.trim().toString()

        if (title!!.isEmpty() || notes!!.isEmpty()) {
            Toast.makeText(view.context, "Please Enter A Note", Toast.LENGTH_SHORT).show()
            return true
        }

        saveItem = NoteItem(title!!, notes!!, System.currentTimeMillis())
        viewModel.insert(saveItem!!)
        return false
    }

    override fun onPause() {
        title = etTitle.text.trim().toString()
        notes = etNotes.text.trim().toString()

        val viewModel: NoteViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(NoteViewModel::class.java)


        if (!(title!!.isEmpty() && notes!!.isEmpty())) {
            saveItem = NoteItem(title!!, notes!!, System.currentTimeMillis())
            viewModel.insert(saveItem!!)
        }
        super.onPause()
    }
}