package com.example.tresenraya_06_03_23

class Juego {
    var ganar = arrayOf(
        intArrayOf(0, 1, 2), // Horizontal
        intArrayOf(3, 4, 5), // Horizontal
        intArrayOf(6, 7, 8), // Horizontal
        intArrayOf(0, 3, 6), // Vertical
        intArrayOf(1, 4, 7), // Vertical
        intArrayOf(2, 5, 8), // Vertical
        intArrayOf(0, 4, 8), // Diagonal
        intArrayOf(2, 4, 6) // Diagonal
    )
    var tableroLogico = IntArray(9) { i -> 0 }
    var jugador = 1

     fun cambiaTurno() {
        jugador++
        if (jugador > 2) jugador = 1
    }
    /*
     * Devuelvo 1 si gana el jugador 1
     * Devuelvo 2 si gana el jugador 2
     * Devuelvo 0 si es empate
     * Devuelvo 3 si es cualquier otro caso
     */
    fun comprobar(): Int {
        var ganado = true
        for (i in 0 until ganar.size) {
            ganado = true
            for (j in 0 until ganar[i].size) {
                if (tableroLogico[ganar[i][j]] != jugador) {
                    ganado = false
                    break
                }
            }
            if (ganado) return jugador
        }
        if (isEmpate()) return 0
        return 3
    }

    private fun isEmpate(): Boolean {
        for (i in 0 until tableroLogico.size) {
            if (tableroLogico[i] == 0) return false
        }
        return true
    }
}