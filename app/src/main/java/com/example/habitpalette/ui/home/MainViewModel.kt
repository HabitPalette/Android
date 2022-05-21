package com.example.habitpalette.ui.home
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.repository.HabitRepository
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(private val repository: HabitRepository):  ViewModel() {

    val pastHabitList: LiveData<List<Habit>> = repository.pastList.asLiveData()
    val presentHabitList: LiveData<List<Habit>> = repository.presentList.asLiveData()

    fun getHabitItem(id:Long) = viewModelScope.launch {
        repository.getHabitItemById(id)
    }
}