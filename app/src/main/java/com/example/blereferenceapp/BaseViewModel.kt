package com.example.blereferenceapp

import android.content.Context
import android.os.Handler
import android.os.Looper.getMainLooper
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException

open class BaseViewModel(private val context: Context) : ViewModel() {
    protected val TAG = javaClass.simpleName
    val errors = MutableLiveData<Throwable>()
    val handler = CoroutineExceptionHandler { _, exception ->
        errors.postValue(exception)
        exception.printStackTrace()

        Handler(getMainLooper()).post {
            val text = "Config Error"
            val centeredText: Spannable = SpannableString(text)
            centeredText.setSpan(
                AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, text.length - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            if (exception is HttpException && exception.response()?.raw()?.request?.url != null) {
                val toast: Toast = Toast.makeText(context, centeredText, LENGTH_LONG)
                toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
                //Toast.makeText(context, "Request Url: ${exception.response()?.raw()?.request?.url}\n${exception.message}", LENGTH_LONG).show()
            } else {
                val toast: Toast = Toast.makeText(context, centeredText, LENGTH_LONG)
                toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
                //Toast.makeText(context, exception.message, LENGTH_LONG).show()
            }
        }
    }

}