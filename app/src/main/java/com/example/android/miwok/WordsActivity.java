package com.example.android.miwok;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordsActivity extends AppCompatActivity {

    private ArrayList<Word> wordArrayList;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        setTitle(title);
        int tileColorResourceID=(Integer)intent.getExtras().get("tileColorResourceID");
        mediaPlayer=MediaPlayer.create(this,R.raw.color_white);

//        ArrayList<Word> wordArrayList=new ArrayList<>();
//
//        for (int i=0;i<100;i++){
////            stringArrayList.add("iteration: "+i);
//            wordArrayList.add(new Word("Miwok: "+i,"English: "+i));
//        }

        wordArrayList=(ArrayList<Word>) intent.getExtras().get("list");

//        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,wordArrayList);
        WordAdapter wordAdapter=new WordAdapter(this,R.layout.list_item,wordArrayList,tileColorResourceID, this);
        ListView listView=(ListView)findViewById(R.id.wordList);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView textView=(TextView)view;
                TextView textView=(TextView)view.findViewById(R.id.englishText);
                Snackbar snackbar=Snackbar.make(view,"Playing translation of "+textView.getText(),Snackbar.LENGTH_SHORT);
                snackbar.show();
//                System.out.println("Tapped!");
                Word word=wordArrayList.get(i);
                mediaPlayer=MediaPlayer.create(WordsActivity.this,word.getAudioResourceID());
                mediaPlayer.start();
            }
        });
    }
}
