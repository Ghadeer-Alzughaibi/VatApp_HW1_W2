package com.example.vatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // binding object
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // reference layout file
        setContentView(view) // link layout


        binding.btnCalculateVat.setOnClickListener {
            calculateVat()
        }// End setOnClickListener


    }// End onCreate

    private fun calculateVat() {
        val stringInTextField = binding.etTotalCost.text.toString()
        val cost = stringInTextField.toDouble()

        val selected = binding.rgVatOption.checkedRadioButtonId
        val vat = when (selected){
            R.id.rb_vat_10 -> 0.10
            R.id.rb_vat_15 -> 0.15
            else -> 0.20
        }

        var vatCalc =  vat * cost
        var total =  cost + vatCalc

        val roundVat = binding.switchRoundUp.isChecked
        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        val formattedVat = NumberFormat.getCurrencyInstance().format(vatCalc)
        binding.txtVatTotal.text = getString(R.string.vat, formattedVat)

        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.txtTotal.text = getString(R.string.total_amount,formattedTotal)
    }

}// End MainActivity