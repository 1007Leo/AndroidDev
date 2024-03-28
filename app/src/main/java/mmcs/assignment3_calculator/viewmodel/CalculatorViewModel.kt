package mmcs.assignment3_calculator.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class CalculatorViewModel: BaseObservable(), Calculator {
    override var display = ObservableField("")
    override var secondaryDisplay = ObservableField("")
    override var operation = ObservableField("")

    override fun addDigit(dig: Int) {
        if (dig == 0 && display.get() == "0")
            return
        display.set(display.get() + dig.toString())
    }

    override fun addPoint() {
        val curStr = display.get()
        if (curStr != null && (curStr.isEmpty() || curStr.contains('.')))
            return
        display.set("$curStr.")
    }

    override fun addOperation(op: Operation) {
        compute()
        when(op) {
            Operation.ADD -> operation.set("+")
            Operation.SUB -> operation.set("-")
            Operation.MUL -> operation.set("×")
            Operation.DIV -> operation.set("÷")
        }
        if (secondaryDisplay.get().isNullOrEmpty() && operation.get() != "-")
            operation.set("")
        if (!display.get().isNullOrEmpty())
            compute()
    }

    override fun compute() {
        val curDisplay = display.get()
        val curSecDisplay = secondaryDisplay.get()
        val curOperation = operation.get()

        if (!curSecDisplay.isNullOrEmpty() && !curSecDisplay.toFloat().isFinite()) {
            secondaryDisplay.set("")
        }
        if (curDisplay.isNullOrEmpty())
            return
        if (curSecDisplay.isNullOrEmpty()) {
            secondaryDisplay.set(curOperation + curDisplay)
            display.set("")
            operation.set("")
            return
        }

        var curRes = when (curOperation) {
            "+" -> (curSecDisplay.toFloat() + curDisplay.toFloat()).toString()
            "-" -> (curSecDisplay.toFloat() - curDisplay.toFloat()).toString()
            "×" -> (curSecDisplay.toFloat() * curDisplay.toFloat()).toString()
            "÷" -> (curSecDisplay.toFloat() / curDisplay.toFloat()).toString()
            else -> return
        }

        if (curRes.length > 2 &&
            curRes[curRes.length - 2] == '.' &&
            curRes[curRes.length - 1] == '0' )
            curRes = curRes.slice(0..curRes.length-3)

        secondaryDisplay.set(curRes)
        display.set("")
        operation.set("")
    }

    override fun clear() {
        val curDisplay = display.get()
        if (curDisplay != null)
            display.set(curDisplay.slice(0..curDisplay.length-2))
    }

    override fun reset() : Boolean {
        display.set("")
        secondaryDisplay.set("")
        operation.set("")
        return true
    }
}