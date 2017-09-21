package mobilestudio.io.core.moviesapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mobilestudio.io.core.moviesapp.R;
import mobilestudio.io.core.moviesapp.main.MainContract;
import mobilestudio.io.core.moviesapp.model.Movie;

/**
 * Created by Sayed on 9/21/2017.
 */

public class MainAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Movie> list;
    private LayoutInflater layoutInflater;
    MainContract.Presenter presenter;

    public MainAdapter(Activity activity, ArrayList<Movie> list, MainContract.Presenter presenter) {
        this.activity = activity;
        this.list = list;
        this.presenter = presenter;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_view_item, null);
        }

        imageView = view.findViewById(R.id.image_item);
        Picasso.with(activity).load(list.get(i).getPosterUrl()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onGridItemClicked(list.get(i));
            }
        });
        return view;

    }
}
