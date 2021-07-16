package com.example.habitpalette.data.model

import androidx.lifecycle.MutableLiveData

class TSLiveData<T> : MutableLiveData<T> {
    constructor() {}
    constructor(value: T) {
        setValue(value)
    }
}