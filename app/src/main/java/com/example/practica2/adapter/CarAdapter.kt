package com.example.practica2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.practica2.R
import com.example.practica2.databinding.ListItemBinding
import com.example.practica2.models.CarModel

class CarAdapter (context: Context, aListCarModels: ArrayList<CarModel>) : BaseAdapter() {

    private val listCars = aListCarModels
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listCars.size
    }

    override fun getItem(position: Int): Any {
        return listCars[position]
    }

    override fun getItemId(position: Int): Long {
        return listCars[position].id.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ListItemBinding.inflate(layoutInflater)
        with(binding){
            NameLabel.text = listCars[position].name
            BrandLabel.text = listCars[position].brand
            MileageLabel.text = listCars[position].mileage.toString() + " Km"
            YearLabel.text = "Año " + listCars[position].year.toString()
            PriceLabel.text = "$ " + listCars[position].price.toString() + ".00"
            when(listCars[position].brand){
                "BMW" ->{
                    ivStage.setImageResource(R.drawable.bmw)
                }
                "Mercedez Benz" ->{
                    ivStage.setImageResource(R.drawable.mercedez)
                }
                "Nissan" ->{
                    ivStage.setImageResource(R.drawable.nissan)
                }
                "Toyota" ->{
                    ivStage.setImageResource(R.drawable.toyota)
                }
                "Ferrari" ->{
                    ivStage.setImageResource(R.drawable.ferrari)
                }
                "Lamborghini" ->{
                    ivStage.setImageResource(R.drawable.lamborghini)
                }
                "Audi" ->{
                    ivStage.setImageResource(R.drawable.audi)
                }
                "Aston Martin" ->{
                    ivStage.setImageResource(R.drawable.aston_martin)
                }
                "Mclaren" ->{
                    ivStage.setImageResource(R.drawable.mclaren)
                }
                "Alfa Romeo" ->{
                    ivStage.setImageResource(R.drawable.alfa_romeo)
                }
                "Volkwagen" ->{
                    ivStage.setImageResource(R.drawable.volkswagen)
                }
                "Mazda" ->{
                    ivStage.setImageResource(R.drawable.mazda)
                }
                "Pagani" ->{
                    ivStage.setImageResource(R.drawable.pagani)
                }
                "Porsche" ->{
                    ivStage.setImageResource(R.drawable.porsche)
                }
                "Ford" ->{
                    ivStage.setImageResource(R.drawable.ford)
                }
                "Chevrolet" ->{
                    ivStage.setImageResource(R.drawable.chevrolet)
                }
                "Dodge" ->{
                    ivStage.setImageResource(R.drawable.dodge)
                }
                "Tesla" ->{
                    ivStage.setImageResource(R.drawable.tesla)
                }
                "Honda" ->{
                    ivStage.setImageResource(R.drawable.honda)
                }
                "Maserati" ->{
                    ivStage.setImageResource(R.drawable.maserati)
                }
                "Jaguar" ->{
                    ivStage.setImageResource(R.drawable.jaguar)
                }
                "Bentley" ->{
                    ivStage.setImageResource(R.drawable.bentley)
                }
                "Lincoln" ->{
                    ivStage.setImageResource(R.drawable.lincoln)
                }
                "Rolls Royce" ->{
                    ivStage.setImageResource(R.drawable.rolls_royce)
                }
                "Cadillac" ->{
                    ivStage.setImageResource(R.drawable.cadillac)
                }
                "Subaru" ->{
                    ivStage.setImageResource(R.drawable.subaru)
                }
            }
        }
        return binding.root
    }
}