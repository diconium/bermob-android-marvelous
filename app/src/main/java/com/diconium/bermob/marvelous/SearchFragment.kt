package com.diconium.bermob.marvelous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.diconium.bermob.marvelous.service.MarvelService
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class SearchFragment : Fragment() {

    private val vm by viewModels<SearchViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO: Refactor to use ViewBinding
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

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            // TODO: Refactor to use the fragment own arguments
            val query = requireActivity().intent.data?.query
                ?: throw IllegalArgumentException("No query found")

            // TODO: Refactor to use dependency injection
            val client = OkHttpClient()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://marvel.com/api/v1/")
                .build()
            val service = retrofit.create(MarvelService::class.java)
            return SearchViewModel(query, service) as T
        }
    }
}

// TODO: Refactor to ListAdapter
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

// TODO: Refactor to use ViewBinding
private class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.image)
    val title = itemView.findViewById<TextView>(R.id.title)
    val subtitle = itemView.findViewById<TextView>(R.id.subtitle)

}
