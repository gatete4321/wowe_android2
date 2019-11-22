package com.example.wowebackand.views.adapter;

import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.Client;

/**
 * created by gatete rugamba
 * this will listen all click listener on all recycle view
 */
public interface MyOnRecyclerListener<E,T> {

    public void onRecyclerViewItemCliked(E e,T t);
}
