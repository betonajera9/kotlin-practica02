package com.example.practica2.lib

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.practica2.models.CarModel
import java.lang.Exception

class CarLib(context: Context?): Helper(context){

    val context = context

    fun getAllCars(): ArrayList<CarModel>{
        val helper = Helper(context)
        val db = helper.writableDatabase
        var listCarModels = ArrayList<CarModel>()
        var carModelTmp: CarModel? = null
        var carCursor: Cursor? = null

        carCursor = db.rawQuery("SELECT * FROM $TABLE_CAR",null)

        if (carCursor.moveToFirst()){
            do{
                carModelTmp = CarModel(carCursor.getInt(0),carCursor.getString(1),carCursor.getString(2),carCursor.getInt(3),carCursor.getInt(4), carCursor.getInt(5))
                listCarModels.add(carModelTmp)
            }while (carCursor.moveToNext())
        }
        carCursor.close()
        return listCarModels
    }

    fun getCar(id: Int): CarModel?{
        val helper = Helper(context)
        val db = helper.writableDatabase
        var carModel: CarModel? = null
        var CursorCar: Cursor? = null

        CursorCar = db.rawQuery("SELECT * FROM $TABLE_CAR WHERE id = $id LIMIT 1",null)

        if (CursorCar.moveToFirst()){
            carModel = CarModel(CursorCar.getInt(0),CursorCar.getString(1),CursorCar.getString(2),CursorCar.getInt(3),CursorCar.getInt(4), CursorCar.getInt(5))
        }

        CursorCar.close()
        return carModel
    }

    fun setCar(name: String, brand: String, year: Int, mileage: Int, price: Int): Long{
        val helper = Helper(context)
        val db = helper.writableDatabase
        var id: Long = 0

        try {
            val values = ContentValues()
            values.put("name",name)
            values.put("brand",brand)
            values.put("year",year)
            values.put("mileage",mileage)
            values.put("price",price)
            id = db.insert(TABLE_CAR,null,values)
        }catch (e: Exception){

        }finally {
            db.close()
        }
        return id
    }

    fun updateCar(id: Int, name: String, brand: String, year: Int, mileage: Int, price: Int): Boolean{
        var success = false
        val helper = Helper(context)
        val db = helper.writableDatabase
        try {
            db.execSQL("UPDATE $TABLE_CAR SET name = '$name', brand = '$brand', year = '$year', mileage = '$mileage', price = '$price' WHERE id = $id")
            success = true
        }catch (e: Exception){

        }finally {
            db.close()
        }

        return success
    }

    fun deleteCar(id: Int): Boolean{
        var success = false
        val helper = Helper(context)
        val db = helper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_CAR WHERE id = $id")
            success = true
        }catch (e: Exception){

        }finally {
            db.close()
        }

        return success
    }
}
