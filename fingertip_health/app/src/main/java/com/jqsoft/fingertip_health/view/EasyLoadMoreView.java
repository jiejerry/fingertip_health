package com.jqsoft.fingertip_health.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.jqsoft.fingertip_health.R;

/**
 * Created by quantan.liu on 2017/3/28.
 */

public class EasyLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.base_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
