
package com.example.incodecoursetask2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var result : TextView? = null
    private var digit : CheckBox? = null
    private var upperCause : CheckBox? = null
    private var lowerCause : CheckBox? = null
    private var symb : CheckBox? = null
    private var numOfSymblos : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        digit = findViewById(R.id.digit)
        upperCause = findViewById(R.id.uppercaseLetters)
        lowerCause= findViewById(R.id.lowerСase)
        symb = findViewById(R.id.symbol)
        numOfSymblos = findViewById(R.id.numOfSymbols)
    }
    fun makePass(view : View){
        if (! digit!!.isChecked && ! upperCause!!.isChecked && !lowerCause!!.isChecked && ! symb!!.isChecked  ){
            Toast.makeText(this, getString(R.string.chooseOneOrMoreChBox), Toast.LENGTH_LONG).show()
            return
        }
        if(numOfSymblos?.text.toString().toInt() > 100){
            Toast.makeText(this, getString(R.string.max100symb), Toast.LENGTH_LONG).show()
            return
        }

        result!!.text = ""
        for (item in GetPass().generate(digit!!.isChecked, upperCause!!.isChecked,
            lowerCause!!.isChecked, symb!!.isChecked, numOfSymblos?.text.toString().toInt())){
            result?.append(item + "\n")
        }
    }
    fun copyPast(view : View){
        Toast.makeText(this, getString(R.string.copyPast), Toast.LENGTH_LONG).show()
    }

    class GetPass {
        private val symbArray = arrayOf( "0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz",  "+-*/=.,;:!?#&%@|^")

        fun generate(digit : Boolean, upperCause : Boolean, lowerCause : Boolean, symb : Boolean , numOfSymblos : Int) : Array<String> {
            var result = arrayOf( "", "", "",  "", "", "")
            var t = ""

            if (digit) t += symbArray[0]
            if (upperCause) t += symbArray[1]
            if (lowerCause) t += symbArray[2]
            if (symb) t += symbArray[3]

            for (i in 0..5){
                var pass = ""
                for (j in 0 until numOfSymblos) pass += getSymbol(t)

                result[i] = pass
            }
            return result
        }
        private fun getSymbol(s: String) : String {
            return s[Random().nextInt(s.length)] + ""
        }
    }
}