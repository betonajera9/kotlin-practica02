package com.example.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.practica2.adapter.CarAdapter
import com.example.practica2.databinding.ActivityMainBinding
import com.example.practica2.models.CarModel
import com.example.practica2.lib.CarLib


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listCars: ArrayList<CarModel>
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carLib = CarLib(this)
        listCars = carLib.getAllCars()

        if(listCars.size == 0 ) binding.tvSinRegistros.visibility = View.VISIBLE
        else binding.tvSinRegistros.visibility = View.INVISIBLE

        val carAdapter = CarAdapter(this, listCars)

        binding.lvListCars.adapter = carAdapter

        binding.lvListCars.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this , DetailsActivity::class.java)
            intent.putExtra("id" , l.toInt())
            startActivity(intent)
            finish()
        }

    }

    fun click(view: View) {
        startActivity(Intent(this, CreateActivity::class.java))
        finish()
    }
}