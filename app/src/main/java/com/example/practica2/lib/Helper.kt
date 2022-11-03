package com.example.practica2.lib

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class Helper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_CAR (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, brand TEXT NOT NULL, year INTEGER NOT NULL, mileage INTEGER NOT NULL, price INTEGER NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL("DROP TABLE $TABLE_CAR")
        onCreate(p0)
    }

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "cars_list.db"
        public const val TABLE_CAR = "car"
    }
}