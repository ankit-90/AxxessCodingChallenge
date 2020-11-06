package com.axxesscodingchallenge.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.axxesscodingchallenge.R
import com.axxesscodingchallenge.base.BaseActivity
import com.axxesscodingchallenge.data.model.Image
import com.axxesscodingchallenge.ui.searchDetail.SearchDetailActivity
import com.axxesscodingchallenge.utils.MiddleDividerItemDecoration
import com.axxesscodingchallenge.utils.createIntent
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>SearchActivity</h1>
 * <p>This class is used to show search results and it will show results in a gridview <p>
 * */
class SearchActivity : BaseActivity(),SearchAdapter.ItemClickListener {

    private val searchViewModel: SearchViewModel by viewModel()
    private var imageList = ArrayList<Image>()
    private val searchAdapter = SearchAdapter(imageList)
    private val LIST_STATE_KEY = "recycler_list_state"
    private var listState: Parcelable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSearch()
        setUpUI()

    }

    override fun getLayout() = R.layout.activity_search

    @SuppressLint("CheckResult")
    private fun onSearch() {

        RxTextView.textChanges(edt_search)
            .debounce(250, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                val key = edt_search.text.toString()
                if (!key.isNullOrEmpty()) {
                    searchViewModel.getSearchResults(key)
                }
            }
    }

    private fun setUpUI() {
        searchViewModel.images.observe(this, Observer { it ->
            imageList = it as ArrayList<Image>
            setUpRecyclerView()
        })
    }

    private fun setUpRecyclerView() {
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
        searchAdapter.setItemClickListener(this)
        rv_search.layoutManager = GridLayoutManager(this@SearchActivity, 4)
        searchAdapter.updateAdapter(imageList)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        listState = rv_search.layoutManager?.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(state: Bundle?) {
        super.onRestoreInstanceState(state)
        if (state != null) listState = state.getParcelable(LIST_STATE_KEY)
    }

    override fun onResume() {
        super.onResume()
        if (listState != null) {
            rv_search.layoutManager?.onRestoreInstanceState(listState)
        }
    }

    /**
     * @param image on click of item get image object
     * */
    override fun onItemCick(image: Image) {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_IMAGE,image)
        createIntent(SearchDetailActivity::class.java,bundle)
    }

    companion object Constants{

        const val EXTRA_IMAGE = "extra_image"

    }
}

