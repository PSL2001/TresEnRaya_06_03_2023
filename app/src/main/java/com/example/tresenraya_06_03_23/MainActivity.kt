package com.example.tresenraya_06_03_23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.tresenraya_06_03_23.databinding.ActivityMainBinding

class MainActivity : Base(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    val casillas = arrayOf(R.drawable.blanco, R.drawable.circulo, R.drawable.cruz)
    val tablero: Array<ImageView?> = Array(9, {null})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pantallaCompleta()
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}