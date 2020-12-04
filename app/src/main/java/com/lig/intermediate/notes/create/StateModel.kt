package com.lig.intermediate.notes.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // apply only to the same scope
class StateModel @Inject constructor() {
    val isEnableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val _isEnableLiveData: LiveData<Boolean> = isEnableLiveData
    private var isEnable = false
    private var state: String? = ""

    fun updateState(input: String?){
        if(state.isNullOrEmpty() != input.isNullOrEmpty()){
            isEnable = !input.isNullOrEmpty()
            isEnableLiveData.postValue(isEnable)
        }
        state = input
    }

}