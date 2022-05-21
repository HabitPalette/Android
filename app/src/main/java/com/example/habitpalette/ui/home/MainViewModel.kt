package com.example.habitpalette.ui.home
import android.os.Build
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

class MainViewModelFactory(private val repository: HabitRepository):ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}