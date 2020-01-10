package com.mumayank.surviveconfigurationchanges

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.temporal.TemporalAmount

class TipViewModel: ViewModel() {
    var billAmount: MutableLiveData<Int> = MutableLiveData(0)
    var tipPercentage: MutableLiveData<Int> = MutableLiveData(0)
    var tipAmount: MutableLiveData<Int> = MutableLiveData(0)
    var finalAmount: MutableLiveData<Int> = MutableLiveData(0)
}