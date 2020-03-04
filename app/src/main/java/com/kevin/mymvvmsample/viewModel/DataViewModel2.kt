package com.kevin.mymvvmsample.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kevin.mymvvmsample.SingleLiveEvent
import com.kevin.mymvvmsample.model.DataModel



/**
 * ViewModel class (實作LiveData)
 * 1.修改mData型態為MutableLiveData
 * MutableLiveData提供setValue()和postValue()兩種方式更新value
 * 差異在於前者是在main thread執行，若需要在background thread則改用後者。
 *
 *
 * 不再用new而是改成透過ViewModelProviders協助我們取得ViewModel，其中of()的參數代表著ViewModel的生命範圍(scope)，
 * 在MainActivity中用of(this)表示ViewModel的生命週期會持續到MainActivity不再活動(destroy且沒有re-create)為止，
 * 只要MainActivity還在活動中，ViewModel就不會被清除，每次create都可以取得同一個ViewModel。
 * */
class DataViewModel2: ViewModel() {

    //Model
    private var dataModel = DataModel()


    //data
    val mData = MutableLiveData<String>()
    val toastText = SingleLiveEvent<String>()
    val isLoading = ObservableField<Boolean>(false)

    /**
     * 從Model Class取得資料
     * 實作邏輯與資料處理
     * */
    fun getData(){
        mData.value = "Loading"
        isLoading.set(true)

        dataModel.getData(object :DataModel.DataListsener{
            override fun onDataReady(data: String) {
                mData.value = "Hello From LiveData"
                toastText.value = "ToastText change From LiveData"
                isLoading.set(false)
            }
        })
    }

}