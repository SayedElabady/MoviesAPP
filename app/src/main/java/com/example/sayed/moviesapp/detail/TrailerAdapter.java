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
import com.example.sayed.moviesapp.model.Trailer;

/**
 * Created by Sayed on 9/23/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    List<Trailer> list;
    TrailerListener listener;

    public TrailerAdapter(List<Trailer> list, TrailerListener presenter) {
        this.list = list;
        this.listener = presenter;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {

        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailer_item)
        TextView trailerItem;
        @BindView(R.id.trailer_view)
        View trailerContainer;

        TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Trailer trailer) {
            trailerContainer.setOnClickListener(view -> listener.onTrailerClicked(trailer.getKey()));
            trailerItem.setText(trailer.getName());
        }
    }
}
