package com.example.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practica2.R
import com.example.practica2.databinding.ActivityCreateBinding
import com.example.practica2.lib.CarLib
import java.util.*

class CreateActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = resources.getStringArray(R.array.stage_list)
        val adapter = ArrayAdapter(this@CreateActivity, R.layout.item_form, items)

        with(binding.brandSelect){
            setAdapter(adapter)
            onItemClickListener = this@CreateActivity
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    fun click(view: View) {
        val carLib = CarLib(this)

        with(binding){
            if (!nameInput.text.toString().isEmpty() && !brandSelect.text.toString().isEmpty() && !yearInput.text.toString().isEmpty() && !mileageInput.text.toString().isEmpty() && !priceInput.text.toString().isEmpty()){
                val id = carLib.setCar(nameInput.text.toString(),brandSelect.text.toString(),yearInput.text.toString().toInt(),mileageInput.text.toString().toInt(), priceInput.text.toString().toInt())

                val year = Calendar.getInstance().get(Calendar.YEAR)

                if(yearInput.text.toString().toInt() > year + 1){
                    yearInput.error = getString(R.string.year_error)
                }else{
                    if(id > 0){
                        Toast.makeText(this@CreateActivity, getString(R.string.success_registry), Toast.LENGTH_LONG).show()

                        nameInput.setText("")
                        brandSelect.setText("")
                        yearInput.setText("")
                        mileageInput.setText("")
                        priceInput.setText("")
                        nameInput.requestFocus()
                    }else{
                        Toast.makeText(this@CreateActivity, getString(R.string.save_error), Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                if (nameInput.text.toString() == ""){
                    nameInput.error = getString(R.string.required_error)
                }
                if (brandSelect.text.toString() == ""){
                    brandSelect.error = getString(R.string.required_error)
                }
                if (yearInput.text.toString() == ""){
                    yearInput.error = getString(R.string.required_error)
                }
                if (mileageInput.text.toString() == ""){
                    mileageInput.error = getString(R.string.required_error)
                }
                if (priceInput.text.toString() == ""){
                    priceInput.error = getString(R.string.required_error)
                }
                Toast.makeText(this@CreateActivity, getString(R.string.inputs_full), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}