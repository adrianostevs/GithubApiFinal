package com.example.submissionakhir

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionakhir.response.ItemsItem
import com.example.submissionakhir.databinding.ActivityMainBinding
import com.example.submissionakhir.viewmodel.MainActivityViewModel
import com.example.submissionakhir.viewmodel.ThemeViewModel
import com.example.submissionakhir.viewmodel.ViewModelFactory
import com.google.android.material.switchmaterial.SwitchMaterial

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listUserAdapter = ListUserAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUser.adapter = listUserAdapter

        supportActionBar?.title = "Cari User"

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        val pref = SettingPreferences.getInstance(dataStore)

        val mainActivityViewModel =
            ViewModelProvider(this)[MainActivityViewModel::class.java]
        val themeViewModel = ViewModelProvider(this, ViewModelFactory(pref))[ThemeViewModel::class.java]

        themeViewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = binding.svUser
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.cari_user)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String): Boolean {
                Toast.makeText(this@MainActivity, q, Toast.LENGTH_SHORT).show()
                mainActivityViewModel.getListUser(q)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        mainActivityViewModel.items.observe(this) { items -> setUserResponse(items) }
        mainActivityViewModel.isLoading.observe(this) { showLoading(it) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, ThemeActivity::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }

    private fun setUserResponse(items: List<ItemsItem>) {
        listUserAdapter.setData(items)
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: ItemsItem) {
                showSelectedUser(user)
                val moveWithObjectIntent = Intent(this@MainActivity, DetailUser::class.java)
                moveWithObjectIntent.putExtra(DetailUser.USERNAME, user.login)
                moveWithObjectIntent.putExtra(DetailUser.ID, user.id)
                moveWithObjectIntent.putExtra(DetailUser.AVATAR, user.avatarUrl)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun showSelectedUser(user: ItemsItem) {
        Toast.makeText(this, "Anda memilih " + user.login, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}


