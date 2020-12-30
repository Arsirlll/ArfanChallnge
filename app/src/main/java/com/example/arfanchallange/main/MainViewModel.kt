package com.example.arfanchallange.main

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.arfanchallange.data.MainData
import com.example.arfanchallange.data.source.MainDataRepository
import com.example.arfanchallange.data.source.MainDataSource
import com.example.arfanchallange.data.source.remote.MainDataRemoteSource.getMainData
import com.example.arfanchallange.util.SingleLiveEvent

class MainViewModel(application: Application, private val mainDataRepository: MainDataRepository) :
    AndroidViewModel(application) {

    val mainDataField: ObservableField<MainData> = ObservableField()
    internal val openRepo = SingleLiveEvent<MainData>()

    fun start() {
        getMainData()
    }

    fun openRepo() {
        openRepo.value = mainDataField.get()
    }

    private fun getMainData() {
        mainDataRepository.getMainData(object : MainDataSource.GetMainDataCallback {

            override fun onDataLoaded(mainData: MainData?) {
                mainDataField.set(mainData)
            }

            override fun onNotAvailable() {
                Toast.makeText(getApplication(), "DataNot Available", Toast.LENGTH_SHORT).show()
            }

            override fun onError(msg: String?) {
                Toast.makeText(getApplication(), "Error : $msg", Toast.LENGTH_SHORT).show()
            }
        })
    }
}