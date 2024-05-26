package com.mmcs.memeapiclient.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mmcs.memeapiclient.R
import com.mmcs.memeapiclient.database.GlobalDbApplication
import com.mmcs.memeapiclient.database.ViewModelFactory
import com.mmcs.memeapiclient.viewmodel.MemeApiClientViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var dbApp: GlobalDbApplication
    private val sharedData: MemeApiClientViewModel by viewModels {
        ViewModelFactory(dbApp.getDB().dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        dbApp = applicationContext as GlobalDbApplication

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}