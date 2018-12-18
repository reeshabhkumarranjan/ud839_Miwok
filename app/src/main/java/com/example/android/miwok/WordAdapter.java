package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public final class WordAdapter extends ArrayAdapter<Word> {

    private ArrayList objects;
    private int tileColorResourceID;
    private Activity activity;

    public WordAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.objects = (ArrayList) objects;
    }

    public WordAdapter(@NonNull Context context, int resource, @NonNull List objects, int tileColorResourceID, Activity activity) {
        super(context, resource, objects);
        this.objects = (ArrayList) objects;
        this.tileColorResourceID = tileColorResourceID;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View wordView = convertView;

        if (wordView == null) {
            wordView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word word = (Word) (objects.get(position));
        TextView miwokTextView = wordView.findViewById(R.id.miwokText);
        TextView englishTextView = wordView.findViewById(R.id.englishText);
        ImageView imageView = wordView.findViewById(R.id.image);
        ListView wordList = activity.findViewById(R.id.wordList);

        miwokTextView.setText(word.getMiwokWord());
        englishTextView.setText(word.getEnglishWord());

        imageView.setImageResource(word.getImageResourceId());

        if (objects.equals(WordList.getPhrasesList())) {
            imageView.setVisibility(View.GONE);
        }

        LinearLayout linearLayout = wordView.findViewById(R.id.tile);
        linearLayout.setBackgroundResource(tileColorResourceID);
        wordList.setBackgroundResource(tileColorResourceID);
        return wordView;
    }
}
