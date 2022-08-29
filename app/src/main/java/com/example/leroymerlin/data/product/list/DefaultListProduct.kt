package com.example.leroymerlin.data.product.list

import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity

fun defaultListProduct(): ArrayList<ProductListEntity> {
    val productList: ArrayList<ProductListEntity> = ArrayList()

    val danogips: ProductListEntity = ProductListEntity(
        "Шпаклёвка готовая финишная Danogips SuperFinish 18.1 кг",
        "im_product_danogips.png")
    productList.add(danogips)

    val knauf: ProductListEntity = ProductListEntity(
        "Шпаклёвка виниловая суперфинишная Knauf Ротбанд Паста 18 кг",
        "im_product_knauf.png")
    productList.add(knauf)

    val axton: ProductListEntity = ProductListEntity(
        "Шпаклёвка полимерная суперфинишная Axton 25 кг",
        "im_product_axton.png")
    productList.add(axton)

    val danogips5kg: ProductListEntity = ProductListEntity(
        "Шпаклёвка финишная Knauf Ротбанд Паста Профи, 5 кг",
        "im_product_knauf.png")
    productList.add(danogips5kg)

    return productList
}