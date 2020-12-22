package com.mindorks.framework.mvvm.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.databinding.ActivityMainBinding
import com.mindorks.framework.mvvm.ui.base.ViewModelFactory
import com.mindorks.framework.mvvm.ui.main.adapter.MainAdapter
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Resource
import com.mindorks.framework.mvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel(binding)
        setupUI(binding)
        setupObserver(binding)
        binding.lifecycleOwner = this
    }

    private fun setupViewModel(binding: ActivityMainBinding) {
        val mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        binding.viewmodel = mainViewModel
    }

    private fun setupUI(binding: ActivityMainBinding) {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(recyclerView.context)
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = MainAdapter(arrayListOf())
        }
    }

    private fun setupObserver(binding: ActivityMainBinding) {
        val mainViewModel: MainViewModel = binding.viewmodel as MainViewModel
        val adapter = binding.recyclerView.adapter as MainAdapter

        mainViewModel.users.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { users -> renderUserList(users, adapter) }
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {
                }
            }
        }
    }

    private fun renderUserList(users: List<User>, adapter: MainAdapter) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}
