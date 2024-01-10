package com.skipissue.mobilebanking.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.skipissue.mobilebanking.data.repository.DatabaseRepository
import com.skipissue.mobilebanking.domain.entity.CardHistoryEntity
import com.skipissue.mobilebanking.domain.entity.getResponse.Data
import javax.inject.Inject

@HiltViewModel
class DataBaseViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val _livedata = MutableLiveData<List<CardHistoryEntity>>()
    val livedata : LiveData<List<CardHistoryEntity>> = _livedata

    private val _livedataCards = MutableLiveData<List<Data>>()
    val livedataCards : LiveData<List<Data>> = _livedataCards

    fun insertAll(entities: List<CardHistoryEntity>){
        viewModelScope.launch {
            databaseRepository.insert(entities)
        }
    }
    fun getAll(){
        viewModelScope.launch {
            _livedata.value = databaseRepository.getAll()
        }
    }
    fun get(isSuccess: Boolean){
        viewModelScope.launch {
            _livedata.value = databaseRepository.get(isSuccess)
        }
    }
    fun getAllCards(){
        viewModelScope.launch {
            _livedataCards.value = databaseRepository.getAllCard()
        }
    }
    fun getCard(id: Long){
        viewModelScope.launch {
            _livedataCards.value = databaseRepository.getFromId(id)
        }
    }
    fun insertCard(card: Data){
        viewModelScope.launch {
            databaseRepository.insertCard(card)
        }
    }
    fun insertCards(cards: List<Data>){
        viewModelScope.launch {
            databaseRepository.insertAllCard(cards)
        }
    }
    fun deleteCards(){
        viewModelScope.launch {
            databaseRepository.deleteCards()
        }
    }
}