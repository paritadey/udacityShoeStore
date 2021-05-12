package com.parita.shoestore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.parita.shoestore.model.Shoes

@Database(entities = arrayOf(Shoes::class), version = 1, exportSchema = false)
abstract class ShoeDatabase : RoomDatabase() {
    abstract fun shoeDao(): ShoeDao

    companion object {
        private var INSTANCE: ShoeDatabase? = null
        fun getDatabase(context: Context): ShoeDatabase? {
            if (INSTANCE == null) {
                synchronized(ShoeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        ShoeDatabase::class.java, "shoes.db"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

}
