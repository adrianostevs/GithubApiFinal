package com.example.submissionakhir

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionakhir.database.User
import com.example.submissionakhir.databinding.ActivityFavoriteBinding
import com.example.submissionakhir.viewmodel.FavoriteViewModel


class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var listFavoriteAdapter: ListFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listFavoriteAdapter = ListFavoriteAdapter()

        val favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        listFavoriteAdapter.setOnItemClickCallback(object : ListFavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                val moveWithObjectIntent = Intent(this@FavoriteActivity, DetailUser::class.java)
                moveWithObjectIntent.putExtra(DetailUser.USERNAME, user.login)
                moveWithObjectIntent.putExtra(DetailUser.ID, user.id)
                moveWithObjectIntent.putExtra(DetailUser.AVATAR, user.avatar_url)
                startActivity(moveWithObjectIntent)
            }
        })

        binding.rvFavorite.adapter = listFavoriteAdapter

        supportActionBar?.title = "Favorite User"

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavorite.addItemDecoration(itemDecoration)

        favoriteViewModel.getFavUser()?.observe(this) {
            if(it != null){
                val list = mapList(it)
                listFavoriteAdapter.setData(list)
            }
        }
    }

    private fun mapList(it: List<User>): ArrayList<User> {
        val listUser = ArrayList<User>()
        for (user in it){
            val userMapped = User(
                user.id,
                user.login,
                user.avatar_url
            )
            listUser.add(userMapped)
        }
        return listUser
    }
}