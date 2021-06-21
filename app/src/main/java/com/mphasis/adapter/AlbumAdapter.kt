package com.mphasis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mphasis.databinding.ListItemBinding
import com.mphasis.model.AlbumModel


class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var data : List<AlbumModel>? =null;
    fun setData(albumData:List<AlbumModel>?){
        data = albumData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = data?.get(position)
        holder.binding.title.text = (item?.title)
    }

    class AlbumViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}