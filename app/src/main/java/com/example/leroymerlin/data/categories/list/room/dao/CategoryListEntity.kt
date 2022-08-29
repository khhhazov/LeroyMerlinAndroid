package com.example.leroymerlin.data.categories.list.room.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CategoryListEntity.TABLE_NAME)
class CategoryListEntity() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryId")
    var id: Int? = null

    @ColumnInfo(name = "categoryName")
    var name: String? = null

    @ColumnInfo(name = "categoryImageUrl")
    var imageUrl: String? = null

    constructor(name: String, imageUrl: String) : this() {
        this.name = name
        this.imageUrl = imageUrl
    }

    companion object {
        const val TABLE_NAME = "category_list_table"
    }
}