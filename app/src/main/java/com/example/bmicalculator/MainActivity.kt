package com.example.bmicalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<TextView>(R.id.etWeight)
        val heightText = findViewById<TextView>(R.id.etHeight)
        val calButton = findViewById<Button>(R.id.btnCalculate)

        calButton.setOnClickListener{
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }

    }
    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrBlank() ->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrBlank() ->{
                Toast.makeText(this, "Heigh is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 18.5 - 24.9 )"

        var resultText = ""
        var color = 0

        when{
            bmi <18.50 ->{
                resultText = "Under weight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "Over weight"
                color = R.color.over_weight
            }
            bmi >29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }
resultDescription.setTextColor(ContextCompat.getColor(this, color))
resultDescription.text = resultText
    }
}