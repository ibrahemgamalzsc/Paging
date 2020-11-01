package com.example.paging.adapter;

import android.content.res.Resources;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paging.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BindingAdapters {
    @BindingAdapter("android:setItemDecoration")
    public static void setItemDecoration(RecyclerView recyclerView, ArrayList<DividerItemDecoration> decorations) {
        for (DividerItemDecoration decoration : decorations) {
            recyclerView.addItemDecoration(decoration);
        }
    }

    @BindingAdapter("android:setImage")
    public static void setImage(ImageView image, String url) {
        Picasso.get().load(url).placeholder(R.drawable.poster_placeholder).fit().into(image);
    }

    @BindingAdapter("android:setAdapter")
    public static void setAdapter(RecyclerView recyclerView, PageListAdapter pageListAdapter) {
        recyclerView.setAdapter(pageListAdapter);
    }

}
