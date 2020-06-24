package com.example.roomkotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application):AndroidViewModel(application) {

    private val repository: WordRepository
    val allWord: LiveData<List<Word>>

    init{
        //Avvio il database
        val database = WordRoomDatabase.getDatabase(application.applicationContext,viewModelScope)?.wordDao()
        repository = WordRepository(database!!)
        allWord = repository.allWords
    }

    fun insert(word:Word) =  viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}