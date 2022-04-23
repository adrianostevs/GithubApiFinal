package com.example.submissionakhir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionakhir.database.User
import com.example.submissionakhir.databinding.ListFavoriteBinding


class ListFavoriteAdapter :
    RecyclerView.Adapter<ListFavoriteAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listUser = ArrayList<User>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(users:List<User>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(var binding: ListFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(user) }
            binding.apply{
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .circleCrop()
                    .into(imgAvatar)
                tvUsername.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ListFavoriteBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: User)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

}

