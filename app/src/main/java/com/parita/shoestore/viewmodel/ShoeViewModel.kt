package com.parita.shoestore.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parita.shoestore.model.Shoes

class ShoeViewModel : ViewModel() {

    private var insertData: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var shoeDataList: LiveData<List<Shoes>>


    fun addShoeDetails(shoeModel: Shoes, context: Context): MutableLiveData<Boolean> {
        ShoesRepository.insertData(context, shoeModel)
        insertData.value = true
        return insertData
    }

    fun getShoeDetails(context: Context) {
        shoeDataList = ShoesRepository.getAllShoes(context)
    }
    fun getAllShoes() :LiveData<List<Shoes>>{ return shoeDataList }

}