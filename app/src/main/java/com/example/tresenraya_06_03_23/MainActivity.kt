package com.example.tresenraya_06_03_23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tresenraya_06_03_23.databinding.ActivityMainBinding

class MainActivity : Base() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}