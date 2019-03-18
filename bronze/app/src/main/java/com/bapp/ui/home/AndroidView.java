package com.bapp.ui.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * 描述：
 */

public interface AndroidView {

    RecyclerView getRecyclerView();

    SwipeRefreshLayout getSwipeRefresh();

}
