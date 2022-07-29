package com.example.blereferenceapp

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.blereferenceapp.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    public val context: Context,

) : BaseViewModel(context) {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        Handler(Looper.getMainLooper()).post {
            if (exception is HttpException && exception.response()?.raw()?.request?.url != null) {
                Toast.makeText(this.context, "Request Url: ${exception.response()?.raw()?.request?.url}\n${exception.message}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this.context, exception.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}