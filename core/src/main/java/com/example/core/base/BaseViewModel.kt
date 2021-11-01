package com.example.core.base


import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseViewModel @Inject constructor() :ViewModel(){

    open val compositeDisposable: CompositeDisposable = CompositeDisposable()


    open val command  by lazy { SingleLiveEvent<BaseCommand>() }




    open fun handleError(t: Throwable) {
        val error: String  = when (t) {

            is BaseError ->{
                 t.statusMessage ?: "Something Went Wrong"
            }
            is HttpException -> {
                "Network Error"
            }
            is UnknownHostException -> {
               "No Internet"
            }
            else -> {
                "Something Went Wrong"
            }
        }
        command.postValue(BaseCommand.Error(error))
    }



    open fun onClear(){
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}