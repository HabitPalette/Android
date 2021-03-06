package com.example.habitpalette.ui.home
import androidx.lifecycle.*
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(habitRepository: HabitRepository):  ViewModel() {

    val pastHabitList: LiveData<List<Habit>> = habitRepository.pastList.asLiveData()
    val presentHabitList: LiveData<List<Habit>> = habitRepository.presentList.asLiveData()
}