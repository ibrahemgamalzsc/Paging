package com.example.paging.pojo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Movie {
    @SerializedName("popularity")
    public Double popularity;
    @SerializedName("vote_count")
    public Integer voteCount;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("id")
    public Integer id;
    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("genre_ids")
    public List<Integer> genreIds = null;
    @SerializedName("title")
    public String title;
    @SerializedName("vote_average")
    public Double voteAverage;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getPopularity(), movie.getPopularity()) &&
                Objects.equals(getVoteCount(), movie.getVoteCount()) &&
                Objects.equals(getVideo(), movie.getVideo()) &&
                Objects.equals(getPosterPath(), movie.getPosterPath()) &&
                Objects.equals(getId(), movie.getId()) &&
                Objects.equals(getAdult(), movie.getAdult()) &&
                Objects.equals(getBackdropPath(), movie.getBackdropPath()) &&
                Objects.equals(getOriginalLanguage(), movie.getOriginalLanguage()) &&
                Objects.equals(getOriginalTitle(), movie.getOriginalTitle()) &&
                Objects.equals(getGenreIds(), movie.getGenreIds()) &&
                Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getVoteAverage(), movie.getVoteAverage()) &&
                Objects.equals(getOverview(), movie.getOverview()) &&
                Objects.equals(getReleaseDate(), movie.getReleaseDate());
    }

   public static DiffUtil.ItemCallback<Movie> movieItemCallback=new DiffUtil.ItemCallback<Movie>() {
       @Override
       public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
           return oldItem.getId()==newItem
                   .getId();
       }

       @Override
       public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
           return oldItem.equals(newItem);
       }
   };
}
