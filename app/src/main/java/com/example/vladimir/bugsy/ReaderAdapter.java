package com.example.vladimir.bugsy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vladimir on 27.4.2017..
 */

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ReaderHolder> {

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

    @Override
    public void onBindViewHolder(final ReaderHolder holder, int position) {
        final Reader reader = mReader.get(position);
        ((TextView) holder.newsView.findViewById(R.id.tvTitle)).setText(Reader.title);
        ((TextView) holder.newsView.findViewById(R.id.tvDescription)).setText(Reader.description);
        ((TextView) holder.newsView.findViewById(R.id.tvCategory)).setText(Reader.category);
        ((TextView) holder.newsView.findViewById(R.id.tvPubDate)).setText(Reader.pubDate);
        ImageView ivImage = ((ImageView) holder.newsView.findViewById(R.id.ivImage));
        holder.newsView.setOnClickListener(new MyOnClickListener(position));
        Picasso.with(context)
                .load(reader.image)
                .resize(300, 300)
                .into(ivImage);
    }

    @Override
    public int getItemCount() {
        return mReader.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        int position;

        private MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            final Reader newsObj = mReader.get(position);
            Intent implicitIntent = new Intent();
            String url = newsObj.link;
            Uri uri = Uri.parse(url);
            implicitIntent.setData(uri);
            implicitIntent.setAction(Intent.ACTION_VIEW);
            implicitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(implicitIntent);
        }
    }
}
