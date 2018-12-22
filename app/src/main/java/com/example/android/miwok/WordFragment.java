package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordFragment extends Fragment {

    private ArrayList<Word> wordArrayList;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioFocusRequest audioFocusRequest;
    private String title;
    private int tileColorResourceID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.activity_words,container,false); //TODO what is container

        Bundle bundle = getArguments();
        title=bundle.getString("title");
        tileColorResourceID=bundle.getInt("tileColorResourceID");
        wordArrayList=bundle.getParcelableArrayList("wordList");

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.color_white);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
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

        WordAdapter wordAdapter = new WordAdapter(getActivity(), R.layout.list_item, wordArrayList, tileColorResourceID, getActivity());
        ListView listView = rootView.findViewById(R.id.wordList);
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

                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceID());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseResources();
                        }
                    });
                }

                else if (focusRequest==AudioManager.AUDIOFOCUS_REQUEST_FAILED){
                    Toast.makeText(getActivity(), "Music playback failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
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
