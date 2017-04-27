package com.example.vladimir.bugsy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
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

    public ReaderAdapter(List<Reader> ModelList, Context context) {
        mReader = ModelList;
        this.context = context;
    }


    public static class ReaderHolder extends RecyclerView.ViewHolder {
        private View raderView;

        public ReaderHolder(View v) {
            super(v);
            raderView = v;
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
        TextView title = (TextView) holder.raderView.findViewById(R.id.tvTitle);
        title.setText(reader.title);
        TextView description =  (TextView) holder.raderView.findViewById(R.id.tvDescription);
        description.setText(reader.description);
        TextView category = (TextView) holder.raderView.findViewById(R.id.tvCategory);
        category.setText(reader.category);
        TextView pubDate = (TextView) holder.raderView.findViewById(R.id.tvPubDate);
        pubDate.setText(reader.pubDate);
        ImageView ivImage = ((ImageView) holder.raderView.findViewById(R.id.ivImage));
        holder.raderView.setOnClickListener(new MyOnClickListener(position));
        Picasso.with(context)
                .load(reader.image)
                .resize(300, 300)
                .into(ivImage);

        if(position%2==0){

            holder.raderView.setBackgroundColor(ContextCompat.getColor(context,R.color.color1));

        }else{
            holder.raderView.setBackgroundColor(ContextCompat.getColor(context,R.color.color2));

        }
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
    @Override
    public int getItemCount() {
        return mReader.size();
    }
}
