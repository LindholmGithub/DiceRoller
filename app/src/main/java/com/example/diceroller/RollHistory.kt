package com.example.diceroller

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class RollHistory : AppCompatActivity() {
    private val historyList: ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val historyBundle = intent.extras
        if (historyBundle != null) {
            historyList.addAll(historyBundle.getStringArrayList("history")!!)
        }

        setContentView(R.layout.activity_roll_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val backButton: Button = findViewById(R.id.button3)
        backButton.setOnClickListener {
            finish()
        }
        var listView: ListView = findViewById(R.id.historyListView)
        val arrayAdapter: ListAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            historyList.reversed()
        )
        listView.adapter = arrayAdapter
    }
}