package com.example.leroymerlin.data.categories.list

import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity

fun defaultListCategory(): ArrayList<CategoryListEntity> {
    val categoryList: ArrayList<CategoryListEntity> = ArrayList()

    val basic = CategoryListEntity(
        "Шпаклевки\nбазовые",
        "im_categories_blue.png"
    )
    categoryList.add(basic)

    val finishing = CategoryListEntity(
        "Шпаклевки\nфинишные",
        "im_categories_green.png"
    )
    categoryList.add(finishing)

    val finishingSuper = CategoryListEntity(
        "Шпаклевки\nсуперфинишные",
        "im_categories_super.png"
    )
    categoryList.add(finishingSuper)

    return categoryList
}