package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    lateinit var bt9: Button
    lateinit var bt8: Button
    lateinit var bt7: Button
    lateinit var bt6: Button
    lateinit var bt5: Button
    lateinit var bt4: Button
    lateinit var bt3: Button
    lateinit var bt2: Button
    lateinit var bt1: Button
    lateinit var bt0: Button
    lateinit var btDiv: Button
    lateinit var btMultiply: Button
    lateinit var btSub: Button
    lateinit var btNegativeNum: Button
    lateinit var btDot: Button
    lateinit var btAdd: Button
    lateinit var btDEL: Button
    lateinit var btClear: Button
    lateinit var btEqual: Button
    lateinit var textView: TextView
    fun getButtonValue(bt: Button) {
        textView.text = textView.text.toString() + bt.text.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TextView
        textView = findViewById(R.id.textView)
        //Numbers Buttons
        bt9 = findViewById(R.id.bt9)
        bt8 = findViewById(R.id.bt8)
        bt7 = findViewById(R.id.bt7)
        bt6 = findViewById(R.id.bt6)
        bt5 = findViewById(R.id.bt5)
        bt4 = findViewById(R.id.bt4)
        bt2 = findViewById(R.id.bt2)
        bt3 = findViewById(R.id.bt3)
        bt1 = findViewById(R.id.bt1)
        bt0 = findViewById(R.id.bt0)

        bt9.setOnClickListener { getButtonValue(bt9) }
        bt8.setOnClickListener { getButtonValue(bt8) }
        bt7.setOnClickListener { getButtonValue(bt7) }
        bt6.setOnClickListener { getButtonValue(bt6) }
        bt5.setOnClickListener { getButtonValue(bt5) }
        bt4.setOnClickListener { getButtonValue(bt4) }
        bt3.setOnClickListener { getButtonValue(bt3) }
        bt2.setOnClickListener { getButtonValue(bt2) }
        bt1.setOnClickListener { getButtonValue(bt1) }
        bt0.setOnClickListener { getButtonValue(bt0) }
        //Operations Buttons
        btDiv = findViewById(R.id.btDiv)
        btMultiply = findViewById(R.id.btMultiply)
        btSub = findViewById(R.id.btSub)
        btNegativeNum = findViewById(R.id.btNegativeNum)
        btDot = findViewById(R.id.btDot)
        btAdd = findViewById(R.id.btAdd)
        btDEL = findViewById(R.id.btDEL)
        btClear = findViewById(R.id.btClear)
        btEqual = findViewById(R.id.btEqual)

        btDiv.setOnClickListener { getButtonValue(btDiv) }
        btMultiply.setOnClickListener { getButtonValue(btMultiply) }
        btSub.setOnClickListener { getButtonValue(btSub) }
        btNegativeNum.setOnClickListener { getButtonValue(btSub) }
        btDot.setOnClickListener { getButtonValue(btDot) }
        btAdd.setOnClickListener { getButtonValue(btAdd) }
        btDEL.setOnClickListener {
            if (textView.text.toString().length > 0)
                if ((textView.text.toString())[0].isDigit())
                    textView.text = textView.text.substring(0, textView.text.toString().length - 1)
        }
        btClear.setOnClickListener { textView.setText("") }
        btEqual.setOnClickListener {
            var num1 = ""
            var operation = ""
            var isDotNum1 = false
            var isDotNum2 = false
            var num2 = ""
            var result = 0f
            val expression = textView.text.toString()



/////////////////////////////////////////////////////////////////////////////////
            for (i in expression) { //15+3.5*2
                when {
                    //i is a number
                    i.isDigit() -> {
                        if (operation.isEmpty()) {
                            num1 += i
                        } else {//5+
                            num2 += i
                        }
                    }
                    // the number has a dot
                    i == '.' -> {
                        if (operation.isEmpty() && isDotNum1 == false) {
                            num1 += i
                            isDotNum1 = true
                        } else if (isDotNum2 == false) {//5.5+
                            num2 += i
                            isDotNum2 = true
                        }
                    }
                    //addition/multiplication operation
                    i == '+' || i == '*' -> {
                        operation = i.toString()

                    }
                    //subtraction operation
                    i == '-' -> {
                        if (num1.isEmpty() && operation.isEmpty()) {//-3+1
                            num1 += i
                        } else if (operation.isEmpty()) {//3-3
                            operation += i
                        } else {//5+-3
                            num2 += i
                        }
                    }
                    //division
                    i == '/' -> {
                        if (num1.isEmpty()) {
                            num1 = "0"
                        }
                        operation += '/'

                    }

                }

            }

            when {
                operation == "+" -> {
                    result = num1.toFloat() + num2.toFloat()
                }
                operation == "*" -> {
                    result = num1.toFloat() * num2.toFloat()
                }
                operation == "-" -> {
                    result = num1.toFloat() - num2.toFloat()
                }
                operation == "/" -> {
                    if (num2 != "0") {
                        result = num1.toFloat() / num2.toFloat()
                    } else {
                        result = 0f
                    }

                }
                else -> {//9=9
                    if (textView.text.toString()[0].isDigit())
                        result = textView.text.toString().toFloat()
                }
            }
            textView.text = result.toString()

//////////////////////////////////////////////////
        }

    }
}