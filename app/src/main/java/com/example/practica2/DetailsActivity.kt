package com.example.practica2

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2.lib.CarLib
import com.example.practica2.R
import com.example.practica2.databinding.ActivityDetailsBinding
import com.example.practica2.models.CarModel

class DetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var carLib: CarLib
    var car: CarModel? = null
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            val bundle = intent.extras
            if (bundle != null){
                id = bundle.getInt("id", 0)
            }
        }else{
            id = savedInstanceState.getSerializable("id") as Int
        }

        carLib = CarLib(this)
        car = carLib.getCar(id)
        if(car != null){
            with(binding){
                nameInput.setText(car?.name)
                brandSelect.setText(car?.brand)
                yearInput.setText(car?.year.toString())
                mileageInput.setText(car?.mileage.toString())
                priceInput.setText(car?.price.toString())

                nameInput.inputType = InputType.TYPE_NULL
                brandSelect.inputType = InputType.TYPE_NULL
                yearInput.inputType = InputType.TYPE_NULL
                mileageInput.inputType = InputType.TYPE_NULL
                priceInput.inputType = InputType.TYPE_NULL
            }
        }

    }

    fun click(view: View) {
        when(view.id){
            R.id.btnEdit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
            R.id.btnDelete ->{
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.confirmation))
                    .setMessage(getString(R.string.delete_confirmation) + " " + car?.name + "?")
                    .setPositiveButton(getString(R.string.yes), DialogInterface.OnClickListener { dialog, which ->
                        if (carLib.deleteCar(id)){
                            Toast.makeText(this, getString(R.string.success_delete), Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    })
                    .setNegativeButton(getString(R.string.no), DialogInterface.OnClickListener { dialog, which ->
                    })
                    .show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}