package com.axxesscodingchallenge.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doOnTextChanged
import com.axxesscodingchallenge.R
import com.axxesscodingchallenge.base.BaseActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers

import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchActivity : BaseActivity() {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSearch()
    }

    override fun getLayout() = R.layout.activity_search

    @SuppressLint("CheckResult")
    private fun onSearch(){

        RxTextView.textChanges(edt_search)
            .debounce(250, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                val key = edt_search.text.toString()
                searchViewModel.getSearchResults(key)

            }
    }


}