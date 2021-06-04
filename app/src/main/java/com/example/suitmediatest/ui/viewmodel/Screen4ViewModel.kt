package com.example.suitmediatest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.suitmediatest.data.model.GuestModel
import com.example.suitmediatest.data.repository.GuestRepository

class Screen4ViewModel : ViewModel() {

    fun getGuest(page: Int) : LiveData<GuestModel>? {
        var value = GuestRepository.getGuest(page)
        return value
    }


}