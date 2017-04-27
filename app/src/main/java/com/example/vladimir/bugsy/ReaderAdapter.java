package com.example.vladimir.bugsy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Vladimir on 27.4.2017..
 */

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ReaderHolder>{

    private Context context;
    private List<Reader> mReader;

    public static class ReaderHolder extends RecyclerView.ViewHolder {
        private View newsView;

        public ReaderHolder(View v) {
            super(v);
            newsView = v;
        }
    }

    @Override
    public ReaderHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reader, parent, false);
        ReaderHolder holder = new ReaderHolder(v);
        return holder;
    }


}
