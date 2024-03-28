package mmcs.assignment3_calculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import mmcs.assignment3_calculator.R
import mmcs.assignment3_calculator.databinding.ActivityMainBinding
import mmcs.assignment3_calculator.viewmodel.Calculator
import mmcs.assignment3_calculator.viewmodel.CalculatorViewModel
import mmcs.assignment3_calculator.viewmodel.Operation

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var viewModel: Calculator

    private fun initButtonListeners() {
        for (i in 0..9)
            findViewById<Button>(R.id.button0 + i)
                .setOnClickListener {
                    viewModel.addDigit(i)
                }

        findViewById<Button>(R.id.buttonDot)
            .setOnClickListener {
                viewModel.addPoint()
            }

        findViewById<Button>(R.id.buttonPlus)
            .setOnClickListener {
                viewModel.addOperation(Operation.ADD)
            }

        findViewById<Button>(R.id.buttonMinus)
            .setOnClickListener {
                viewModel.addOperation(Operation.SUB)
            }

        findViewById<Button>(R.id.buttonMultiply)
            .setOnClickListener {
                viewModel.addOperation(Operation.MUL)
            }

        findViewById<Button>(R.id.buttonDivide)
            .setOnClickListener {
                viewModel.addOperation(Operation.DIV)
            }

        findViewById<Button>(R.id.buttonEquals)
            .setOnClickListener {
                viewModel.compute()
            }

        findViewById<Button>(R.id.buttonDelete)
            .setOnClickListener {
                viewModel.clear()
            }
        findViewById<Button>(R.id.buttonDelete)
            .setOnLongClickListener {
                viewModel.reset()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = CalculatorViewModel()

        mainBinding.viewModel = viewModel

        initButtonListeners()
    }
}