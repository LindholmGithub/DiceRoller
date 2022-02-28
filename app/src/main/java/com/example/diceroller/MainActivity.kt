package com.example.diceroller

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){
    private val historyList: ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val historyButton: Button = findViewById(R.id.button2)
        historyButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RollHistory::class.java)
            val b = Bundle()
            b.putStringArrayList("history", historyList)
            intent.putExtras(b)
            startActivity(intent)
        }
        val spinner: Spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.dice_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            // Weird conversion, jeg ved det godt. Programmet crashede når jeg skrev "as Int".
            val amountOfRolls = spinner.selectedItem.toString().toInt()
            rollDice(amountOfRolls)

        }
    }

    private fun rollDice(amountOfRolls: Int) {
        val dice = Dice(6)
        val resultList: MutableList<Int> = mutableListOf()
        val resultTextView: TextView = findViewById(R.id.textView)
        for (i in 1..amountOfRolls){
            val diceRoll = dice.roll()
            resultList.add(diceRoll)
        }
        resultTextView.text = resultList.joinToString(" - ")
        historyList.add(resultList.joinToString(" - "))
    }

}

class Dice(private val numSides: Int){
    fun roll(): Int {
        return (1..numSides).random()
    }
}