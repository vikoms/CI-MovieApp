package com.example.movie_app_second_submission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.movie_app_second_submission.databinding.ActivityMainBinding
import com.example.movie_app_second_submission.ui.movie.MovieFragment
import com.example.movie_app_second_submission.ui.series.SeriesFragment
import dagger.hilt.android.AndroidEntryPoint
import github.com.st235.lib_expandablebottombar.MenuItem

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mFragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFragmentManager = supportFragmentManager

        addFragment(MovieFragment())

        binding.expandableBottomBar.onItemSelectedListener =
            { _: View, menuItem: MenuItem, b: Boolean ->
                when (menuItem.id) {
                    R.id.movie -> {
                        addFragment(MovieFragment())
                        supportActionBar?.title = "Movie"
                    }
                    R.id.series -> {
                        addFragment(SeriesFragment())
                        supportActionBar?.title = "Series"
                    }
                }
            }

    }

    private fun addFragment(fragment: Fragment) {
        mFragmentManager
            .beginTransaction()
            .replace(
                R.id.frame_container,
                fragment,
                MovieFragment::class.java.simpleName
            )
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                val uri = Uri.parse("movieapp://favorite")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                true
            }
            else -> true

        }
    }
}