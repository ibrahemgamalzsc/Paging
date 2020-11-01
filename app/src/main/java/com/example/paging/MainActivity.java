package com.example.paging;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.paging.adapter.PageListAdapter;
import com.example.paging.data.ResponseState;
import com.example.paging.databinding.ActivityMainBinding;
import com.example.paging.pojo.Movie;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    MainVM mainVM;
    @Inject
    PageListAdapter pageListAdapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityMainBinding.setAdapter(pageListAdapter);
        ArrayList<DividerItemDecoration> decorations = new ArrayList<>();
        decorations.add(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        decorations.add(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        activityMainBinding.setDecoration(decorations);
        mainVM.getStatus().observe(this, new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                switch (responseState.status) {
                    case SUCCESS: {
                        activityMainBinding.setLoading(false);
                        activityMainBinding.setShowRecycleView(true);
                        break;
                    }
                    case LOADING: {
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(MainActivity.this,responseState.getErrorDescription(),Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });

        mainVM.getPagedListLiveData().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                if (movies != null) {
                    pageListAdapter.submitList(movies);
                    pageListAdapter.notifyDataSetChanged();
                }

            }
        });
    }
}