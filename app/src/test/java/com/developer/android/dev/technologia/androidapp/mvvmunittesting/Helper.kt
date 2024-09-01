package com.developer.android.dev.technologia.androidapp.mvvmunittesting

import java.io.InputStreamReader

object Helper {

    fun readFileResources(fileName:String):String{
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream,"UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}