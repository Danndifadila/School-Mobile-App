package com.example.raiso

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity





class SecondActivity : AppCompatActivity() {
    private lateinit var tvInput: TextView
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        tvInput = findViewById(R.id.tvInput)

        val buttonGoToNext: Button = findViewById(R.id.back)
        buttonGoToNext.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun onDigit(view: View) {
        if (stateError) {
            tvInput.text = (view as Button).text
            stateError = false
        } else {
            tvInput.append((view as Button).text)
        }
        lastNumeric = true
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    fun onDot(view: View) {
        if (lastNumeric && !stateError && !lastDot) {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric && !stateError) {
            val tvValue = tvInput.text.toString()
            var prefix = ""

            try {
                var result = tvValue

                // Handle subtraction for negative numbers
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    result = tvValue.substring(1)
                }

                when {
                    result.contains("-") -> {
                        val splitValue = result.split("-")
                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput.text =
                            removeTrailingZero((one.toDouble() - two.toDouble()).toString())
                    }

                    result.contains("+") -> {
                        val splitValue = result.split("+")
                        tvInput.text =
                            removeTrailingZero((splitValue[0].toDouble() + splitValue[1].toDouble()).toString())
                    }

                    result.contains("*") -> {
                        val splitValue = result.split("*")
                        tvInput.text =
                            removeTrailingZero((splitValue[0].toDouble() * splitValue[1].toDouble()).toString())
                    }

                    result.contains("/") -> {
                        val splitValue = result.split("/")
                        tvInput.text =
                            removeTrailingZero((splitValue[0].toDouble() / splitValue[1].toDouble()).toString())
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
                stateError = true
                tvInput.text = "Error"
            }
        }
    }

    private fun removeTrailingZero(result: String): String {
        return if (result.contains(".0"))
            result.substring(0, result.length - 2)
        else
            result
    }

}