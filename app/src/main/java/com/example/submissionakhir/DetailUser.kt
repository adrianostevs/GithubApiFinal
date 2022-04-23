package com.example.submissionakhir

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submissionakhir.databinding.ActivityDetailUserBinding
import com.example.submissionakhir.response.DetailUserResponse
import com.example.submissionakhir.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUser : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail User"
        supportActionBar?.elevation = 0f

        val detailUserViewModel = ViewModelProvider(
            this
        )[DetailUserViewModel::class.java]
        detailUserViewModel.detailUser.observe(this) { detailUser -> setDetailUser(detailUser) }
        detailUserViewModel.isLoading.observe(this) { isLoading -> showLoading(isLoading) }

        val username = intent.getStringExtra(USERNAME)
        val id = intent.getIntExtra(ID, 0)
        val avatarUrl = intent.getStringExtra(AVATAR)
        if (username != null) {
            detailUserViewModel.getDetailUser(username)
        }

        val login = Bundle()
        login.putString(DATA_FRAGMENT, username)
        Log.d("Detail", username.toString())

        val sectionPagerAdapter = SectionPagerAdapter(this, login)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        FollowingFragment.newInstance(username.toString())
        FollowersFragment.newInstance(username.toString())

        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailUserViewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if(count != null){
                    if(count > 0){
                        binding.tbFavorite.isChecked = true
                        isChecked = true
                    } else {
                        binding.tbFavorite.isChecked = false
                        isChecked = false
                    }
                }
            }
        }

        binding.tbFavorite.setOnClickListener {
            isChecked = !isChecked
            if(isChecked){
                if (username != null) {
                    if (avatarUrl != null) {
                        detailUserViewModel.getAllUser(username, id, avatarUrl)
                    }
                }
            } else{
                detailUserViewModel.removeUser(id)
            }
            binding.tbFavorite.isChecked = isChecked
        }
    }

    private fun setDetailUser(detailUser: DetailUserResponse) {
        binding.tvUsernameDetail.text = detailUser.login
        binding.tvNamaDetail.text = detailUser.name
        binding.tvPerusahaanDetail.text = detailUser.company
        binding.tvLokasiDetail.text = detailUser.location
        binding.tvRepositoriDetail.text = StringBuilder(detailUser.publicRepos.toString()).append(" repositori")
        binding.tvFollowingDetail.text = StringBuilder(detailUser.following.toString()).append(" following")
        binding.tvFollowerDetail.text = StringBuilder(detailUser.followers.toString()).append(" followers")
        Glide.with(this@DetailUser)
            .load(detailUser.avatarUrl)
            .circleCrop()
            .into(binding.avaDetail)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val USERNAME = "username"
        const val ID = "id"
        const val AVATAR = "avatar"
        const val DATA_FRAGMENT = "datafragment"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.following,
            R.string.followers,
        )
    }
}