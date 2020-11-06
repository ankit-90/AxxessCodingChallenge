package com.axxesscodingchallenge.ui.searchDetail;

import android.os.Bundle;
import android.widget.ImageView;

import com.axxesscodingchallenge.R;
import com.axxesscodingchallenge.base.BaseActivity;
import com.axxesscodingchallenge.data.model.Image;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import static com.axxesscodingchallenge.ui.search.SearchActivity.EXTRA_IMAGE;

/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>SearchDetailActivity</h1>
 * <p>Show image view with field to add comment</p>
 * */
public class SearchDetailActivity extends BaseActivity {

    private Image image = null;


    @Override
    protected int getLayout(){
        return R.layout.activity_search_detail;
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getIntent().getExtras().getParcelable(EXTRA_IMAGE);
        setUpUI();

    }

    private void setUpUI(){
        ImageView imgBanner = findViewById(R.id.img_banner);
        Picasso.get()
                .load(image.getImageSrc_())
                .placeholder(R.drawable.ic_baseline_search_24)
                .centerCrop()
                .fit()
                .into(imgBanner);
        setToolBarTitle(image.getTitle());
    }


}
