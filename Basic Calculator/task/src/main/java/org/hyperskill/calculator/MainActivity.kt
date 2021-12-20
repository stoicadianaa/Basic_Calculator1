package org.hyperskill.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var negativeNumber = false
        var number1 = 0.0
        var number2 = 0.0
        var operation = ' '

        findViewById<EditText>(R.id.editText).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var length = editText.text.length
                if (length == 2) {
                    if(editText.text[0] == '0' && editText.text[1] != '.')
                        editText.text.delete(0,1)
                    else if(editText.text[0] == '-' && editText.text[1] == '.') {
                        editText.text.delete(1,2)
                        editText.append("0.")
                    }
                }
                else if(length >= 2 && editText.text[length - 2] == '.' && editText.text[length - 1] == '0')
                    editText.text.delete(length - 2,length)
                else if (length == 3) {
                    if(editText.text[0] == '-' && editText.text[1] == '0' && editText.text[2] != '.')
                    editText.text.delete(1,2)
                }

            }
            override fun afterTextChanged(s: Editable?) {
                //
            }
        })

        fun numbers(op: Char) {
            number1 = editText.text.toString().toDouble()
            if(editText.text.toString().toIntOrNull() != null)
                editText.hint = number1.toInt().toString()
            editText.text.clear()
            operation = op
        }
        //fun intOrDouble() {
        //
        //}

        findViewById<Button>(R.id.button9).setOnClickListener {
            editText.append("9")
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            editText.append("8")
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            editText.append("7")
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            editText.append("6")
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            editText.append("5")
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            editText.append("4")
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            editText.append("3")
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            editText.append("2")
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            editText.append("1")
        }
        findViewById<Button>(R.id.button0).setOnClickListener {
            editText.append("0")
        }
        findViewById<Button>(R.id.dotButton).setOnClickListener {
            editText.append(".")
        }
        findViewById<Button>(R.id.clearButton).setOnClickListener {
            editText.text.clear()
            editText.hint = "0"
            editText.append("0")
        }


        findViewById<Button>(R.id.addButton).setOnClickListener {
            numbers('+')
        }
        findViewById<Button>(R.id.subtractButton).setOnClickListener {
            if(editText.text.toString() == "0") {
                negativeNumber = true
                editText.append("-")
                negativeNumber = false
            }
            else
                numbers('-')
        }
        findViewById<Button>(R.id.multiplyButton).setOnClickListener {
            numbers('x')
        }
        findViewById<Button>(R.id.divideButton).setOnClickListener {
            numbers('%')
        }

        findViewById<Button>(R.id.equalButton).setOnClickListener {
            number2 = editText.text.toString().toDouble()
            editText.text.clear()
            var value:String = when (operation) {
                '+' -> number1 + number2
                '-' -> number1 - number2
                'x' -> number1 * number2
                '%' -> number1 / number2
                else -> error("error")
            }.toDouble().toString()

            editText.append(value)
        }
    }

}
