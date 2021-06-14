package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.exp

class MainActivity : AppCompatActivity() {
    lateinit var one: TextView
    lateinit var two: TextView
    lateinit var three: TextView
    lateinit var four: TextView
    lateinit var five: TextView
    lateinit var six: TextView
    lateinit var seven: TextView
    lateinit var eight: TextView
    lateinit var nine: TextView
    lateinit var zero: TextView

    lateinit var plus: TextView
    lateinit var minus: TextView
    lateinit var multiply: TextView
    lateinit var divide: TextView
    lateinit var modulo: TextView
    lateinit var equals: TextView

    lateinit var changeSign: TextView
    lateinit var decimal: TextView
    lateinit var ac: TextView
    lateinit var backspace: ImageView

    lateinit var expression: TextView
    lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)

        plus = findViewById(R.id.addition)
        minus = findViewById(R.id.subtract)
        multiply = findViewById(R.id.multiply)
        divide = findViewById(R.id.divide)
        modulo = findViewById(R.id.modulo)

        equals = findViewById(R.id.equals)
        changeSign = findViewById(R.id.change_sign)
        decimal = findViewById(R.id.decimal)

        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)

        ac = findViewById(R.id.ac)
        backspace = findViewById(R.id.back)
        one.setOnClickListener {
            appendText("1", true)
        }

        two.setOnClickListener {
            appendText("2", true)
        }

        three.setOnClickListener {
            appendText("3", true)
        }

        four.setOnClickListener {
            appendText("4", true)
        }

        five.setOnClickListener {
            appendText("5", true)
        }

        six.setOnClickListener {
            appendText("6", true)
        }

        seven.setOnClickListener {
            appendText("7", true)
        }

        eight.setOnClickListener {
            appendText("8", true)
        }

        nine.setOnClickListener {
            appendText("9", true)
        }

        zero.setOnClickListener {
            appendText("0", true)
        }

        plus.setOnClickListener {
            appendText("+", false)
        }

        minus.setOnClickListener {
            appendText("-", false)
        }

        multiply.setOnClickListener {
            appendText("*", false)
        }

        divide.setOnClickListener {
            appendText("/", false)
        }

        modulo.setOnClickListener {
            appendText("%", false)
        }

        equals.setOnClickListener {
            try {
                val expr = ExpressionBuilder(expression.text.toString()).build()
                val answer = expr.evaluate()
                result.text = answer.toString()
            } catch (exception: Exception) {
                result.text = exception.message
            }
        }

        decimal.setOnClickListener {
            appendText(".", true)
        }

        changeSign.setOnClickListener {
            result.text = ""
            result.hint = ""

            if (expression.text.isNotEmpty() && expression.text[0] == '-') {
                expression.text = expression.text.substring(1) // -2345 -> substring(1) -> 2345
            } else {
                expression.text = "-${expression.text}"
            }
        }

        backspace.setOnClickListener {
            result.text = ""
            result.hint = ""

            if (expression.text.isNotEmpty()) {
                expression.text = expression.text.substring(0, expression.text.length - 1) // 234578 -> 23457
            }
        }

        ac.setOnClickListener {
            expression.text = ""
            result.text = ""
            result.hint = ""
        }
    }
    private fun appendText(value: String, toBeCleared: Boolean) {
        if (result.text != "") {
            expression.text = ""
        }

        // 85+25
        // = 110
        // Press 2
        // Result will be cleared
        // Expression will contain 2
        if (toBeCleared) {
            result.text = ""
            expression.append(value)
        } else {
            expression.append(result.text)
            expression.append(value)
            result.text = ""
        }

        result.hint = expression.text
    }
}