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
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        binding.viewmodel = viewModel

        setupUI(binding)
        setupObserver(binding)
    }

    private fun setupUI(binding: ActivityMainBinding) {
        val adapter = MainAdapter(arrayListOf())
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver(binding: ActivityMainBinding) {
        val mainViewModel = binding.viewmodel as MainViewModel
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
