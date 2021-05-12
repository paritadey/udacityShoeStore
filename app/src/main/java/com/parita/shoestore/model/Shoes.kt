package com.parita.shoestore.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "shoes", primaryKeys = ["shoeId"])
data class Shoes (
     var shoeId: String,
     var shoeName: String,
     var shoeCompany: String,
     var shoeSize: String,
     var shoeDescription: String
)