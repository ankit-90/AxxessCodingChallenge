package com.axxesscodingchallenge.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.axxesscodingchallenge.R
import com.axxesscodingchallenge.base.BaseActivity
import com.axxesscodingchallenge.data.model.Image
import com.axxesscodingchallenge.utils.MiddleDividerItemDecoration
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class SearchActivity : BaseActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private var imageList = ArrayList<Image>()
    private val searchAdapter = SearchAdapter(imageList)
    val LIST_STATE_KEY = "recycler_list_state"
    var listState: Parcelable? = null


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
                if(!key.isNullOrEmpty()){
                    searchViewModel.getSearchResults(key)
                }


            }

        searchViewModel.images.observe(this, Observer { it ->
            imageList = it as ArrayList<Image>
            Log.d("check karo", "$imageList")

            val itemDecoration = MiddleDividerItemDecoration(
                this@SearchActivity,
                MiddleDividerItemDecoration.ALL
            )
            itemDecoration.setDividerColor(
                ContextCompat.getColor(
                    this@SearchActivity,
                    android.R.color.holo_red_dark
                )
            )
            rv_search.addItemDecoration(itemDecoration)
            rv_search.adapter = searchAdapter
            rv_search.layoutManager = GridLayoutManager(this@SearchActivity, 4)
            searchAdapter.updateAdapter(imageList)

        })


    }


    override fun onSaveInstanceState(outState: Bundle) {
        listState = rv_search.layoutManager?.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(state: Bundle?) {
        super.onRestoreInstanceState(state)
        // Retrieve list state and list/item positions
        if (state != null) listState = state.getParcelable(LIST_STATE_KEY)
    }

    override fun onResume() {
        super.onResume()
        if (listState != null) {
            rv_search.layoutManager?.onRestoreInstanceState(listState)
        }
    }

}