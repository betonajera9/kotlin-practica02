package com.example.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practica2.R
import com.example.practica2.databinding.ActivityEditBinding
import com.example.practica2.lib.CarLib
import java.util.*

class EditActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityEditBinding
    private lateinit var carLib: CarLib
    var car: com.example.practica2.models.CarModel? = null
    var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
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
            }
        }

        val items = resources.getStringArray(R.array.stage_list)
        val adapter = ArrayAdapter(this@EditActivity, R.layout.item_form, items)

        with(binding.brandSelect){
            setAdapter(adapter)
            onItemClickListener = this@EditActivity
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    fun click(view: View) {
        with(binding){
            if(!nameInput.text.toString().isEmpty() && !brandSelect.text.toString().isEmpty() && !yearInput.text.toString().isEmpty() && !mileageInput.text.toString().isEmpty() && !priceInput.text.toString().isEmpty()){
                val year = Calendar.getInstance().get(Calendar.YEAR)

                if(yearInput.text.toString().toInt() > year + 1){
                    yearInput.error = getString(R.string.year_error)
                }else {
                    if(carLib.updateCar(id, nameInput.text.toString(), brandSelect.text.toString(), yearInput.text.toString().toInt(),mileageInput.text.toString().toInt(), priceInput.text.toString().toInt())){
                        Toast.makeText(this@EditActivity, getString(R.string.success_update), Toast.LENGTH_LONG).show()
                        val intent = Intent(this@EditActivity, MainActivity::class.java)
                        intent.putExtra("id",id)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@EditActivity, getString(R.string.update_error), Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this@EditActivity, getString(R.string.inputs_full), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@EditActivity, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        finish()
    }
}