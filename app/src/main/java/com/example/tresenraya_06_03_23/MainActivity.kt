package com.example.tresenraya_06_03_23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.tresenraya_06_03_23.databinding.ActivityMainBinding

class MainActivity : Base(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    val casillas = arrayOf(R.drawable.blanco, R.drawable.circulo, R.drawable.cruz)
    val tablero: Array<ImageView?> = Array(9, {null})
    var punt1 = 0
    var punt2 = 0

    var dosJugadores = true
    lateinit var juego: Juego

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pantallaCompleta()
        iniciarTablero()
        modoDosJugadores()
    }

    private fun modoDosJugadores() {
        dosJugadores = true
        juego = Juego()
        ponerListeners()
    }

    private fun ponerListeners() {
        for (i in tablero.indices) {
            tablero[i]?.setOnClickListener(this)
            tablero[i]?.setImageResource(casillas[0])
        }
    }

    private fun iniciarTablero() {
        tablero[0] = binding.iv0
        tablero[1] = binding.iv1
        tablero[2] = binding.iv2
        tablero[3] = binding.iv3
        tablero[4] = binding.iv4
        tablero[5] = binding.iv5
        tablero[6] = binding.iv6
        tablero[7] = binding.iv7
        tablero[8] = binding.iv8

    }

    override fun onClick(v: View?) {
        for (i in 0 until tablero.size) {
            if (v == tablero[i]) {
                jugarCasilla(i)
            }
        }
    }

    private fun jugarCasilla(i: Int) {
        if (juego.tableroLogico[i] != 0) return
        tablero[i]?.setImageResource(casillas[juego.jugador])
        juego.tableroLogico[i] = juego.jugador
        var op = juego.comprobar()
        evaluarResultado(op)
        if (op != 3) return
        juego.cambiaTurno()
    }

    private fun evaluarResultado(op: Int) {
        val mensaje = AlertDialog.Builder(this)
        mensaje.setTitle("TRES EN RAYA")
        when (op) {
            0 -> mostrar("Empate", mensaje)
            1 -> mostrar("Gana el jugador 1", mensaje)
            2 -> mostrar("Gana el jugador 2", mensaje)
            3 -> return
        }
    }

    private fun mostrar(s: String, mensaje: AlertDialog.Builder) {
        mensaje.setMessage(s)
        mensaje.show()
    }


}