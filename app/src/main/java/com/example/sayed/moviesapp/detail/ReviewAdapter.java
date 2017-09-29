package com.example.sayed.moviesapp.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.sayed.moviesapp.R;
import com.example.sayed.moviesapp.model.Review;

/**
 * Created by Sayed on 9/23/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    List<Review> list;

    public ReviewAdapter(List<Review> list) {
        this.list = list;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_item)
        TextView reviewItem;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }

        public void bind(Review review) {
            reviewItem.setText(review.getContent());

        }

    }

}
