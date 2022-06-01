package com.diconium.bermob.marvelous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diconium.bermob.marvelous.service.MarvelService
import com.diconium.bermob.marvelous.service.models.CharacterResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class SearchFragment : Fragment() {
    private val arguments by navArgs<SearchFragmentArgs>()

    private val vm by viewModels<SearchViewModel> { viewModelFactory }

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
            adapter.submitList(it)
        }
        recycler.adapter = adapter

        Toast.makeText(requireContext(), arguments.query, Toast.LENGTH_LONG).show()

        return root
    }

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            val query = arguments.query

            // TODO: https://github.com/diconium/bermob-android-marvelous/issues/12 DI
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

private class Adapter : ListAdapter<CharacterResponse, Holder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.frag_search_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val d = getItem(position)
        holder.bind(d)
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<CharacterResponse>() {
            override fun areItemsTheSame(
                oldItem: CharacterResponse,
                newItem: CharacterResponse
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterResponse,
                newItem: CharacterResponse
            ): Boolean = oldItem == newItem

        }
    }

}

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/10 ViewBinding
private class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)
    val title: TextView = itemView.findViewById(R.id.title)
    val subtitle: TextView = itemView.findViewById(R.id.subtitle)

    fun bind(data: CharacterResponse) {
        image.setImageResource(R.drawable.ic_placeholder)
        title.text = data.name
        subtitle.text = data.description
    }

}
