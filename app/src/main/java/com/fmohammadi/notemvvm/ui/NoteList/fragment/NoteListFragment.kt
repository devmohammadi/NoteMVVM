package com.fmohammadi.notemvvm.ui.NoteList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmohammadi.notemvvm.Data.db.NoteDatabase
import com.fmohammadi.notemvvm.Data.repositories.NoteRepository
import com.fmohammadi.notemvvm.R
import com.fmohammadi.notemvvm.other.NoteItemAdapter
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModel
import com.fmohammadi.notemvvm.ui.NoteList.NoteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_note_list.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class NoteListFragment : Fragment(),KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val factory: NoteViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)

        val viewModel: NoteViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(NoteViewModel::class.java)

        val adapter = NoteItemAdapter(listOf(), viewModel)

        val mLayoutManager = LinearLayoutManager(view.context)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = true
        view.rvNoteItem.layoutManager = mLayoutManager
        view.rvNoteItem.adapter = adapter

        viewModel.getAllNoteItems().observe(requireActivity(), Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        val fabActionButton = view.findViewById<FloatingActionButton>(R.id.fabButton)
        fabActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.noteList_to_create)
        }

        return view
    }

}