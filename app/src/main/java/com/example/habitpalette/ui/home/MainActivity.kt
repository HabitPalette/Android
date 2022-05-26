package com.example.habitpalette.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitpalette.databinding.ActivityMainBinding
import com.example.habitpalette.ui.habit.CreateHabitActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // 1. View Model 설정
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. View Binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. btnCreateHabit 눌러서 CreateHabitActivity로 intent
        binding.btnCreateHabit.setOnClickListener {
            Intent(this, CreateHabitActivity::class.java).also{
                startActivity(it)
            }
        }

        // 4. pastAdapter 설정
        val pastAdapter = PastHabitListAdapter(this)
        pastAdapter.setHasStableIds(true)
        binding.rvPastHabit.adapter = pastAdapter
        binding.rvPastHabit.layoutManager = LinearLayoutManager(this)
        viewModel.pastHabitList.observe(this) { list -> pastAdapter.submitList(list) }

        // 5. presentAdapter 설정
        val presentAdapter = CurrentHabitListAdapter(this)
        presentAdapter.setHasStableIds(true)
        binding.rvCurrentHabit.adapter = presentAdapter
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false).apply {
            binding.rvCurrentHabit.layoutManager = this
        }
        viewModel.presentHabitList.observe(this) { list -> presentAdapter.submitList(list) }
    }
}