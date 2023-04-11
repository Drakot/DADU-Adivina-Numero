package com.example.tema4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tema4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var contador = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val random = generateRandom()
        Log.i("MainActivity", random.toString())

        binding.btAdivinar.setOnClickListener {
            val numero = binding.etNumber.text.toString().toIntOrNull()
            contador++
            numero?.let {
                if (numero > random) {
                    mostrarMensaje("El numero debe ser menor")
                } else if (numero < random) {
                    mostrarMensaje("El numero debe ser mayor")
                } else {
                    mostrarMensaje("Has ganado, y lo has intentado $contador")
                }
            }
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    fun generateRandom(): Int {
        val max = 100
        val min = 1
        val random_int = Math.floor(Math.random() * (max - min + 1) + min).toInt()

        return random_int
    }

}