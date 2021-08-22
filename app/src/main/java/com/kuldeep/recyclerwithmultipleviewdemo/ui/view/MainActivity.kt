package com.kuldeep.recyclerwithmultipleviewdemo.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuldeep.recyclerwithmultipleviewdemo.databinding.ActivityMainBinding
import com.kuldeep.recyclerwithmultipleviewdemo.ui.adapter.HomeRecyclerViewAdapter
import com.kuldeep.recyclerwithmultipleviewdemo.ui.viewmodel.HomeViewModel
import com.kuldeep.recyclerwithmultipleviewdemo.data.api.Resource
import com.kuldeep.recyclerwithmultipleviewdemo.data.model.HomeRecyclerViewItem
import com.kuldeep.recyclerwithmultipleviewdemo.utils.hide
import com.kuldeep.recyclerwithmultipleviewdemo.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val homeRecyclerViewAdapter = HomeRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = homeRecyclerViewAdapter
        }

        viewModel.homeListItemsLiveData.observe(this){ result->
            when(result){
                is Resource.Failure->{
                  viewBinding.progressBar.hide()
                }
                is Resource.loading-> viewBinding.progressBar.show()
                is Resource.Success ->{
                    viewBinding.progressBar.hide()
                    homeRecyclerViewAdapter.items= result.value
                }
            }
        }
    }
}