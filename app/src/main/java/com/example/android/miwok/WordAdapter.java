package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public final class WordAdapter extends ArrayAdapter<Word> {

    private ArrayList objects;
    private int tileColorResourceID;
//    private static int numbersColorResourceID,familyColorResourceID, colorsColorResourceID,phrasesColorsResourceID;
//
//    static {
//        numbersColorResourceID=R.color.category_numbers;
//        familyColorResourceID=R.color.category_family;
//        colorsColorResourceID=R.color.category_colors;
//        phrasesColorsResourceID=R.color.category_phrases;
//    }

    public WordAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.objects=(ArrayList)objects;
    }

    public WordAdapter(@NonNull Context context, int resource, @NonNull List objects, int tileColorResourceID) {
        super(context, resource, objects);
        this.objects=(ArrayList)objects;
        this.tileColorResourceID=tileColorResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View wordView=convertView;

        //The following inflation logic: https://github.com/udacity/ud839_CustomAdapter_Example/blob/master/app/src/main/java/com/example/android/flavor/AndroidFlavorAdapter.java

        if(wordView == null) {
            wordView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word word=(Word)(objects.get(position));
        TextView miwokTextView=wordView.findViewById(R.id.miwokText);
        TextView englishTextView=wordView.findViewById(R.id.englishText);
        ImageView imageView=wordView.findViewById(R.id.image);
        ImageButton audioPlayButton=(ImageButton)wordView.findViewById(R.id.audioPlayButton);
//        ListView wordList=(ListView)findViewById(R.id.wordList);

        miwokTextView.setText(word.getMiwokWord());
        englishTextView.setText(word.getEnglishWord());

//        if(objects.getClass()!=WordList.getPhrasesList().getClass()){
//            imageView.setImageResource(word.getImageResourceId());
//        }

        imageView.setImageResource(word.getImageResourceId());

//        System.out.println(objects.getClass());
//        if(objects.getClass().equals(WordList.getPhrasesList().getClass())){
//            System.out.println("Phrases list tapped.");
//            imageView.setVisibility(View.GONE);
//        }
//        else{
//            imageView.setVisibility(View.VISIBLE);
//        }

        if(objects.equals(WordList.getPhrasesList())){
//            System.err.println("Tapped phrases list.");
            imageView.setVisibility(View.GONE);
        }

        LinearLayout linearLayout=(LinearLayout)wordView.findViewById(R.id.tile);
        linearLayout.setBackgroundResource(tileColorResourceID);
//        audioPlayButton.setBackgroundResource(tileColorResourceID);
//        System.out.println("tileColorResourceID: "+ tileColorResourceID);
        return wordView;
    }
}
