package com.example.webapplication.presentation.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webapplication.R
import com.example.webapplication.data.RepositoryImpl
import com.example.webapplication.domain.*
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : ViewModel() {

    private val repository = RepositoryImpl(application)

    private val GetItemsListUseCase = GetItemsListUseCase(repository)
    private val AddNewWebItemUseCase = AddNewWebItemUseCase(repository)
    private val DeleteWebItemUseCase = DeleteWebItemUseCase(repository)
    private val EditWebItemUseCase = EditWebItemUseCase(repository)
    private val AddItemsListUseCase = AddItemsListUseCase(repository)

    private val _itemsList = MutableLiveData<List<WebItemEntity>>()
    val itemsList: LiveData<List<WebItemEntity>>
        get() = _itemsList

    private val list = ArrayList<WebItemEntity>()

    init {
        if (_itemsList.value != null) {
            viewModelScope.launch {
                _itemsList.value = GetItemsListUseCase.getItemsList().value
            }
        } else {
            viewModelScope.launch {
                AddItemsListUseCase.addItemsList(list)
            }
        }
    }

    private fun initList() {
        list.add(
            WebItemEntity("Tweeter", "https://twitter.com", R.drawable.twitter)
        )
        list.add(
            WebItemEntity("Chess.com", "https://www.chess.com", R.drawable.chess)
        )
        list.add(
            WebItemEntity("Translate", "https://translate.google.com", R.drawable.translate)
        )
        list.add(
            WebItemEntity("Netflix", "https://www.netflix.com", R.drawable.netflix)
        )
        list.add(
            WebItemEntity("Spotify", "https://open.spotify.com", R.drawable.spotify)
        )
        list.add(
            WebItemEntity("YouTube", "https://www.youtube.com", R.drawable.youtube)
        )
        list.add(
            WebItemEntity("Pinterest", "https://www.pinterest.com", R.drawable.pinterest)
        )
        list.add(
            WebItemEntity("Instagram", "https://www.instagram.com", R.drawable.insta)
        )
        list.add(
            WebItemEntity("Facebook", "https://www.facebook.com", R.drawable.facebook)
        )
        list.add(
            WebItemEntity(
                "StackOverflow",
                "https://stackoverflow.com",
                R.drawable.stackoverflow
            )
        )
        list.add(
            WebItemEntity("Binance", "https://www.binance.com", R.drawable.binance)
        )
        list.add(
            WebItemEntity("RoboForex", "https://roboforex.com", R.drawable.roboforex)
        )
        list.add(
            WebItemEntity("PocketOption", "https://pocketoption.com", R.drawable.pocketoption)
        )
    }

    fun addNewItem(item: WebItemEntity) {
        viewModelScope.launch {
            AddNewWebItemUseCase.addNewItem(item)
        }
    }

    fun deleteItem(item: WebItemEntity) {
        viewModelScope.launch {
            DeleteWebItemUseCase.deleteItem(item)
        }
    }

    fun editItem(item: WebItemEntity) {
        viewModelScope.launch {
            EditWebItemUseCase.changeItem(item)
        }
    }
}