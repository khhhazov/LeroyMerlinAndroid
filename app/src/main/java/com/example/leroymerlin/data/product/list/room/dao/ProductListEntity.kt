package com.example.leroymerlin.data.product.list.room.dao

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
class ProductListEntity() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "productId")
    var id: Int? = null

    @ColumnInfo (name = "productName")
    var name: String? = null

    @ColumnInfo (name = "productImageUrl")
    var imageUrl: String? = null

    constructor(name: String, imageUrl: String) : this() {
        this.name = name
        this.imageUrl = imageUrl
    }

    companion object {
        const val TABLE_NAME = "product_list_table"
    }
}