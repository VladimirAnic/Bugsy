package com.example.vladimir.bugsy;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeLayout;
    private List<Reader> mFeedModelList;
    private ImageButton ibSearch;
    private EditText etSearch;
    private String searchCategory="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
    }

    private void setUpUI() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);

        ibSearch.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new FetchFeedTask().execute((Void) null);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                etSearch.setText("");
                searchCategory="";
                new FetchFeedTask().execute((Void) null);
            }
        });
    }

    public List<Reader> parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {
        String title = null;
        String link = null;
        String description = null;
        String image = null;
        String category = null;
        String pubDate = null;
        boolean isItem = false;
        List<Reader> items = new ArrayList<>();
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);
            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if (name == null)
                    continue;

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                } else if (name.equalsIgnoreCase("category")) {
                    if (searchCategory.equals("")) category = result;
                    else {
                        if (result.equals(searchCategory)) category = result;
                        else category = null;
                    }
                } else if (name.equalsIgnoreCase("enclosure")) {
                    image = xmlPullParser.getAttributeValue(1);
                } else if (name.equalsIgnoreCase("pubDate")) {
                    pubDate = result;
                }

                if (title != null && link != null && description != null && category != null && image != null && pubDate != null) {
                    if (isItem) {
                        Reader item = new Reader(title, link, category, description, image, pubDate);
                        items.add(item);
                    }

                    title = null;
                    link = null;
                    description = null;
                    category = null;
                    image = null;
                    pubDate = null;
                    isItem = false;
                }
            }

            return items;
        } finally {
            inputStream.close();
        }
    }

    @Override
    public void onClick(View v) {
        searchCategory = etSearch.getText().toString();
        new FetchFeedTask().execute((Void) null);
    }

    private class FetchFeedTask extends AsyncTask<Void, Void, Boolean> {

        private String urlLink;

        @Override
        protected void onPreExecute() {
            mSwipeLayout.setRefreshing(true);
            urlLink = "http://www.bug.hr/rss/vijesti/";
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            if (TextUtils.isEmpty(urlLink))
                return false;

            try {
                URL url = new URL(urlLink);
                InputStream inputStream = url.openConnection().getInputStream();
                mFeedModelList = parseFeed(inputStream);
                return true;
            } catch (IOException e) {
                Log.e(TAG, "Error", e);
            } catch (XmlPullParserException e) {
                Log.e(TAG, "Error in parsing", e);
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            mSwipeLayout.setRefreshing(false);
            if (success) {
                mRecyclerView.setAdapter(new ReaderAdapter(mFeedModelList, getApplicationContext()));
            } else {
                Toast.makeText(MainActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
            }
        }
    }
}
