package com.kevin.mymvvmsample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.databinding.DataBindingUtil
import com.kevin.mymvvmsample.databinding.ActivityMainBinding
import com.kevin.mymvvmsample.viewModel.DataViewModel
import com.kevin.mymvvmsample.viewModel.DataViewModel2
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 練習MVVM架構、dataBinding、Lifecycle-aware ViewModel
 * Model單純傳遞資料、於ViewModel處理邏輯，並透過DataBinding處理View更新
 * */
class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var binding : ActivityMainBinding
    private lateinit var dataViewModel: DataViewModel
    private lateinit var dataViewModel2 : DataViewModel2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"onCreate")
        //data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding

        //未處理Life-cycle問題
//        dataViewModel = DataViewModel()
//        binding.dataViewModel = dataViewModel
//        dataViewModel.mData.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
//            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                // 此功能為展示Data Binding缺少的Lifecycle-aware特性
//                // 改model回傳的時間來模擬此情況，在背景還是會看到Toast
//                // 改用LiveData，如果App在背景，observer會將狀態hold住，等到回到前景時在呼叫出來
//                Toast.makeText(this@MainActivity,"Property Changed From Data Binding",Toast.LENGTH_SHORT).show()
//
//                //於DataViewModel2 實作LiveData
//            }
//        })


        //處理Life-cycle問題(記得要改layout綁定的class)
        dataViewModel2 = ViewModelProviders.of(this).get(DataViewModel2::class.java)
        binding.dataViewModel = dataViewModel2
        dataViewModel2.mData.observe(this,
            Observer<String> { string ->
                textView.text = string
                //此處如果旋轉螢幕的話會呼叫第二次
//                Toast.makeText(this@MainActivity,string,Toast.LENGTH_SHORT).show()
            }
        )

        dataViewModel2.toastText.observe(this,
            Observer { toastText ->
                //使用SingleLiveEvent 旋轉螢幕的話不會呼叫第二次
                Toast.makeText(this@MainActivity,toastText,Toast.LENGTH_SHORT).show()
            }
        )

//        val user = User(1,"keivn",20)
//        binding.user = user

    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG,"onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"onResume")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.e(TAG,"onContentChanged")
    }

}
