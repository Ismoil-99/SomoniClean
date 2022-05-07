package com.example.somoni.data.model

data class Regular(val icon: String,val bank_name:String,val Currency:ArrayList<Currency>,val colors:Colors)
data class Currency(val name:String,val sell_value: String,val buy_value:String,val flag:String)
class Colors(val color_1:String,val color_2:String)
