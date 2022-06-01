package com.diconium.bermob.marvelous

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/4 architecture
class EntryFragment : Fragment(R.layout.frag_entry), SearchView.OnQueryTextListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search: SearchView = view.findViewById(R.id.search)
        search.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        val q = query.trim { it <= ' ' }
        if (q.isNotEmpty()) {
            onSearch(q)
        }
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        // no-op
        return true
    }

    private fun onSearch(query: String) {
        findNavController().navigate(EntryFragmentDirections.toSearchFragment(query))
    }
}