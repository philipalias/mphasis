package com.mphasis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mphasis.adapter.AlbumAdapter
import com.mphasis.databinding.ActivityMainBinding
import com.mphasis.repository.AlbumRepository
import com.mphasis.retrofit.RetrofitService
import com.mphasis.viewmodel.AlbumViewModel
import com.mphasis.viewmodel.AlbumViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AlbumViewModel
    private val adapter = AlbumAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val albumRepository = AlbumRepository(retrofitService)

        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this, AlbumViewModelFactory(albumRepository)).get(AlbumViewModel::class.java)


        viewModel.albumList.observe(this, {
            adapter.setData(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllAlbums()
    }
}