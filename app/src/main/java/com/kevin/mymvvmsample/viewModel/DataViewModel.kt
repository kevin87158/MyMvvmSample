package com.kevin.mymvvmsample.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.kevin.mymvvmsample.model.DataModel

/**
 * ViewModel class
 * */
class DataViewModel {

    //Model
    private var dataModel = DataModel()


    //data
    val mData = ObservableField<String>()


    val isLoading = ObservableField<Boolean>(false)



    /**
     * 從Model Class取得資料
     * 實作邏輯與資料處理
     * */
    fun getData(){
        mData.set("Loading...")
        isLoading.set(true)
        dataModel.getData(object :DataModel.DataListsener{
            override fun onDataReady(data: String) {
                mData.set("Hello From Kotlin")
                isLoading.set(false)
            }
        })
    }

}