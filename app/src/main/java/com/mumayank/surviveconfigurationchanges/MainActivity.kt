package com.mumayank.surviveconfigurationchanges

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val tipViewModel = ViewModelProviders.of(this)[TipViewModel::class.java]

        billAmountEditText.doOnTextChanged { text, start, count, after ->
            val billAmount = text.toString().toIntOrNull() ?: 0
            tipViewModel.billAmount.value = billAmount
        }
        tipPercentEditText.doOnTextChanged { text, start, count, after ->
            val tipPercent = text.toString().toIntOrNull() ?: 0
            tipViewModel.tipPercentage.value = tipPercent
        }


        tipViewModel.billAmount.observe(this, Observer {
            onBillOrTipPercentChange(tipViewModel)
        })
        tipViewModel.tipPercentage.observe(this, Observer {
            onBillOrTipPercentChange(tipViewModel)
        })
        tipViewModel.finalAmount.observe(this, Observer {
            onFinalAmountChange(tipViewModel)
        })
    }

    private fun onBillOrTipPercentChange(tipViewModel: TipViewModel) {
        val billAmount = billAmountEditText.text.toString().toIntOrNull() ?: 0
        val tipPercentage = tipPercentEditText.text.toString().toIntOrNull() ?: 0

        val tipAmount = (billAmount.toDouble() * (tipPercentage/100.0)).roundToInt()
        val finalAmount = billAmount + tipAmount

        tipViewModel.tipAmount.value = tipAmount
        tipViewModel.finalAmount.value = finalAmount
    }

    private fun onFinalAmountChange(tipViewModel: TipViewModel) {
        val tipAmount = tipViewModel.tipAmount.value.toString().toIntOrNull() ?: 0
        val finalAmount = tipViewModel.finalAmount.value.toString().toIntOrNull() ?: 0

        tipAmountTextView.text = tipAmount.toString()
        finalAmountTextView.text = finalAmount.toString()
    }
}
