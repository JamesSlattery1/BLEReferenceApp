package com.example.blereferenceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment(@LayoutRes val layoutId: Int) : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var lastSneckbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    open fun showError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showSckeekBar(view: View) {
        lastSneckbar = Snackbar.make(view, "Sending data ", Snackbar.LENGTH_INDEFINITE)
        lastSneckbar!!.show()
    }

    fun hideSneckBar() {
        lastSneckbar?.dismiss()
    }
}