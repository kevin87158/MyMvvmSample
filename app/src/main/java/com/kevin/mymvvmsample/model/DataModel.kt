package com.kevin.mymvvmsample.model

/***
 * 取得資料
 * */
class DataModel {

    /**
     * 取得Data
     * */
    fun getData(dataListsener: DataListsener){
        android.os.Handler().postDelayed({
            dataListsener.onDataReady("Hello from kotlin")
        },(5000).toLong())
    }

    interface DataListsener{
        fun onDataReady(data:String)
    }

}