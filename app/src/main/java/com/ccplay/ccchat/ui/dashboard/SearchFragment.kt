package com.ccplay.ccchat.ui.dashboard

import com.ccplay.ccchat.R

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ccplay.ccchat.databinding.FragmentSearchBinding
import com.ccplay.ccchat.databinding.RowSearchBinding
import com.tom.atm.Lightyear

class SearchFragment : Fragment() {
    val viewModel by viewModels<SearchViewModel>()
    private lateinit var adapter: SearchAdapter
    val chatRooms = mutableListOf<Lightyear>()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRecycler.setHasFixedSize(true)
        binding.searchRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = SearchAdapter()//要在manger後面?
        binding.searchRecycler.adapter = adapter
        viewModel.chatRooms.observe(viewLifecycleOwner) { rooms ->
            adapter.submitRooms(rooms)
        }
        viewModel.getAllRooms()
    }

    inner class SearchAdapter : RecyclerView.Adapter<BindingViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
            val binding = RowSearchBinding.inflate(layoutInflater, parent, false)
            //    binding.headShot.setOnClickListener( startActivity(intent))
            return BindingViewHolder(binding)
        }

        override fun getItemCount(): Int {//取得房間數量
            return chatRooms.size
        }

        override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
            val lightYear = chatRooms[position]
            holder.host.setText(lightYear.stream_title)
            holder.title.setText(lightYear.nickname)
            holder.itemView.setOnClickListener { chatRoomsClicked(lightYear) }
            val option = RequestOptions()
                .transform(CenterCrop(), RoundedCorners(30))//圓角30
            Glide.with(this@SearchFragment)
                .applyDefaultRequestOptions(option)//應用圓角
                .load(lightYear.head_photo)
                .into(holder.binding.searchPhoto)
        }


        private fun chatRoomsClicked(lightYear: Lightyear) {
            Log.d(ContentValues.TAG, "東踏取蜜")
        }

        fun submitRooms(rooms: List<Lightyear>) {
            chatRooms.clear()
            chatRooms.addAll(rooms)
            notifyDataSetChanged()
        }
    }

    inner class BindingViewHolder(val binding: RowSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val host = binding.tvSearchHostTitle
        val title = binding.tvSearchTitle
        val headshot = binding.searchPhoto
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

