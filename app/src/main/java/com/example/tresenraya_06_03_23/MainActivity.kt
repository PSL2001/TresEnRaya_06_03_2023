package com.example.tresenraya_06_03_23

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tresenraya_06_03_23.databinding.ActivityMainBinding

class MainActivity : Base(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    val casillas = arrayOf(R.drawable.blanco, R.drawable.circulo, R.drawable.cruz)
    val tablero: Array<ImageView?> = Array(9, {null})
    var punt1 = 0
    var punt2 = 0
    var dificultad = 0

    var dosJugadores = true
    lateinit var juego: Juego

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pantallaCompleta()
        iniciarTablero()
        setListeners()
        //modoDosJugadores()
    }

    private fun setListeners() {
        binding.btnSalir.setOnClickListener {
            finish()
        }
        binding.btnJugar.setOnClickListener {
            comprobarModo()
        }
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            dosJugadores = !(isChecked)
            //Si esta activado el modo 2 jugadores, tanto el label de dificultad como el spinner se desactivan
            binding.tvDificultad.isEnabled = !dosJugadores
            binding.spDificultad.isEnabled = !dosJugadores

        }
    }

    private fun comprobarModo() {
        if (dosJugadores) {
            Toast.makeText(this, "Modo 2 jugadores", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Derrota a la maquina!", Toast.LENGTH_SHORT).show()
        }
        iniciarJuego()
    }

    private fun iniciarJuego() {
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
        if (!dosJugadores) {
            //Comprobamos la dificultad
            dificultad = binding.spDificultad.selectedItemPosition
            //Si la dificultad es 0, entonces es facil, si es 1, entonces es medio, si es 2, entonces es dificil
            when (dificultad) {
                0 -> juego.dificultad = 1
                1 -> juego.dificultad = 2
                2 -> juego.dificultad = 3
            }
            //Llamamos a la funcion piensaJugada, pasandole la dificultad
            var jugada = juego.piensaJugada(juego.dificultad)
            //Ponemos la imagen en el tablero
            tablero[jugada]?.setImageResource(casillas[juego.jugador])
            //Ponemos el valor en el tablero logico
            juego.tableroLogico[jugada] = juego.jugador
            //Comprobamos si hay ganador
            var op = juego.comprobar()
            //Si hay ganador, mostramos el mensaje
            evaluarResultado(op)
            //Si hay ganador, salimos de la funcion
            if (op != 3) return
            //Si no hay ganador, cambiamos el turno
            juego.cambiaTurno()
        }
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