package com.diconium.bermob.marvelous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val vm by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO: https://github.com/diconium/bermob-android-marvelous/issues/10 ViewBinding
        val root = inflater.inflate(R.layout.frag_search, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.recycler)
        val adapter = Adapter()
        vm.data.observe(viewLifecycleOwner) {
            adapter.data = it
            adapter.notifyDataSetChanged()
        }
        recycler.adapter = adapter
        return root
    }

//    private val viewModelFactory = object : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//            // TODO: https://github.com/diconium/bermob-android-marvelous/issues/11 arguments
//            val query = requireActivity().intent.data?.query
//                ?: throw IllegalArgumentException("No query found")
//
//
//
//
//            return SearchViewModel(query, repo) as T
//        }
//    }
}

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/13 ListAdapter
private class Adapter : RecyclerView.Adapter<Holder>() {

    var data: List<TempData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.frag_search_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val d = data[position]
        holder.image.setImageResource(R.drawable.ic_placeholder)
        holder.title.text = d.t
        holder.subtitle.text = d.s
    }

    override fun getItemCount() = data.size

}

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/10 ViewBinding
private class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)
    val title: TextView = itemView.findViewById(R.id.title)
    val subtitle: TextView = itemView.findViewById(R.id.subtitle)

}
