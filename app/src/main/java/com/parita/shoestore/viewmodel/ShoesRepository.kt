package com.parita.shoestore.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import com.parita.shoestore.db.ShoeDatabase
import com.parita.shoestore.model.Shoes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ShoesRepository() {
    companion object {

        var shoesDatabase: ShoeDatabase? = null
        lateinit var shoeData: LiveData<List<Shoes>>

        fun initializeDB(context: Context): ShoeDatabase {
            return ShoeDatabase.getDatabase(context)!!
        }

        fun insertData(context: Context, shoeModel: Shoes) {
            shoesDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                shoesDatabase!!.shoeDao().insertShoe(shoeModel)
            }
        }

        fun getAllShoes(context: Context):LiveData<List<Shoes>> {
            shoesDatabase = initializeDB(context)
            shoeData = shoesDatabase!!.shoeDao().getShoeList()
            return shoeData
        }
    }
}
