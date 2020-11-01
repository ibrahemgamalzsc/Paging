package com.example.paging.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paging.databinding.MovieListItemBinding;
import com.example.paging.pojo.Movie;

public class PageListAdapter extends PagedListAdapter<Movie, PageListAdapter.ListViewHolder> {
    public PageListAdapter() {
        super(Movie.movieItemCallback);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieListItemBinding movieListItemBinding = MovieListItemBinding.inflate(inflater, parent, false);
        return new ListViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.movieListItemBinding.setMovie(movie);
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemBinding movieListItemBinding;

        public ListViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;
        }
    }
}
