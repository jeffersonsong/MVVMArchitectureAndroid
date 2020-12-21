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

        val mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        binding.viewmodel = mainViewModel
        binding.lifecycleOwner = this

        val adapter = MainAdapter(arrayListOf())

        setupRecyclerView(binding.recyclerView, adapter)
        setupObserver(mainViewModel) { userList ->
            renderUserList(userList, adapter)
        }
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>
    ) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    private fun setupObserver(
        mainViewModel: MainViewModel,
        observer: (userList: List<User>) -> Unit
    ) {
        mainViewModel.getUsers().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { users -> observer(users) }
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderUserList(users: List<User>, adapter: MainAdapter) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}
