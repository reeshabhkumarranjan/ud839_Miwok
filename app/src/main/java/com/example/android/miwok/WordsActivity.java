package com.example.android.miwok;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordsActivity extends AppCompatActivity {

    private ArrayList<Word> wordArrayList;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioFocusRequest audioFocusRequest;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Intent upIntent=NavUtils.getParentActivityIntent(this);

            if(NavUtils.shouldUpRecreateTask(this,upIntent)){
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(upIntent)
                        .startActivities();
            }

            else{
                NavUtils.navigateUpTo(this,upIntent);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setContentView(R.layout.activity_words);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        setTitle(title);
        int tileColorResourceID = (Integer) intent.getExtras().get("tileColorResourceID");
        mediaPlayer = MediaPlayer.create(this, R.raw.color_white);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        AudioAttributes audioAttributes=new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        audioFocusRequest=new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                .setAudioAttributes(audioAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {

                        switch (focusChange){
                            case AudioManager.AUDIOFOCUS_LOSS:
                                mediaPlayer.stop();
                                releaseResources();
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                mediaPlayer.pause();
                                mediaPlayer.seekTo(0);
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                mediaPlayer.pause();
                                mediaPlayer.seekTo(0);
                                break;
                            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                                mediaPlayer.start();
                                break;
                        }
                    }
                })
                .build();

        wordArrayList = (ArrayList<Word>) intent.getExtras().get("list");

        WordAdapter wordAdapter = new WordAdapter(this, R.layout.list_item, wordArrayList, tileColorResourceID, this);
        ListView listView = findViewById(R.id.wordList);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int focusRequest=audioManager.requestAudioFocus(audioFocusRequest);

                if(focusRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    TextView textView = view.findViewById(R.id.englishText);
                    Snackbar snackbar = Snackbar.make(view, "Playing translation of " + textView.getText(), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    Word word = wordArrayList.get(i);

                    mediaPlayer = MediaPlayer.create(WordsActivity.this, word.getAudioResourceID());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseResources();
                        }
                    });
                }

                else if (focusRequest==AudioManager.AUDIOFOCUS_REQUEST_FAILED){
                    Toast.makeText(WordsActivity.this, "Music playback failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseResources();
    }

    private void releaseResources(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if(audioManager!=null){
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
        }
    }
}
