package com.axxesscodingchallenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.axxesscodingchallenge.R
import com.axxesscodingchallenge.utils.setVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = LayoutInflater.from(this).inflate(getLayout(), null, false)
        container.addView(view, 0)

        if (hasToolbar()){
            toolbar.visibility = View.VISIBLE
        }else{
            toolbar.visibility = View.GONE
        }
    }

    /**
     * Need to implement this method if we are not using data binding in UI
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

    protected open fun hasToolbar(): Boolean = false


}