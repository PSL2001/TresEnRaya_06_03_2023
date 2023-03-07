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

    //------------------------------------------------------------------------------------------
    fun piensaJugada(): Int {
        var op = atacar()
        if (op != -1) return op
        op = defiende()
        if (op != -1) return op
        if (tableroLogico[4] == 0) return 4
        op = jugarEsquinas()
        if (op != -1) return op
        return jugadaRandom()
    }

    private fun jugarEsquinas(): Int {
        if (tableroLogico[0] == 0 || tableroLogico[2] == 0 || tableroLogico[6] == 0 || tableroLogico[8] == 0) {
            var a = intArrayOf(0, 2, 6, 8)
            do {
                var casilla = a.random()
                if (tableroLogico[casilla] == 0) return casilla
            } while (true)
        }
        return -1
    }

    private fun jugadaRandom(): Int {
        do {
            var casilla = (0..8).random()
            if (tableroLogico[casilla] == 0) return casilla
        } while (true)
    }

    private fun defiende(): Int {
        for (i in ganar.indices) {
            if (cantidadDeValores(i, 1)) {
                var res = casillaQueFalta(i)
                if (res != -1) return res
            }
        }
        return -1
    }

    /*
     * Devuelve si puede ser la casilla ganadora o -1 si no hay
     */
    private fun atacar(): Int {
        for (i in ganar.indices) {
            if (cantidadDeValores(i, 2)) {
                var res = casillaQueFalta(i)
                if (res != -1) return res
            }
        }
        return -1
    }

    private fun casillaQueFalta(fila: Int): Int {
        for (i in 0 until ganar[fila].size) {
            if (tableroLogico[ganar[fila][i]] == 0) return ganar[fila][i]
        }
        return -1
    }

    private fun cantidadDeValores(fila: Int, valor: Int): Boolean {
        var cont = 0
        for (i in 0 until ganar[fila].size) {
            if (tableroLogico[ganar[fila][i]] == valor) cont++
        }
        return (cont == 2)
    }
}