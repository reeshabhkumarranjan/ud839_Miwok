package com.example.android.miwok;

import java.util.ArrayList;

public final class WordList {
    private static ArrayList<Word> numbersList,familyList,colorsList,phrasesList;

    static {

        // Initialising the numbersList
        numbersList=new ArrayList<>();
        numbersList.add(new Word("lutti","one",R.drawable.number_one,R.raw.number_one));
        numbersList.add(new Word("otiiko","two",R.drawable.number_two,R.raw.number_two));
        numbersList.add(new Word("tolookasu","three",R.drawable.number_three,R.raw.number_three));
        numbersList.add(new Word("oyyisa","four",R.drawable.number_four,R.raw.number_four));
        numbersList.add(new Word("massokka","five",R.drawable.number_five,R.raw.number_five));
        numbersList.add(new Word("temmokka","six",R.drawable.number_six,R.raw.number_six));
        numbersList.add(new Word("kenekaku","seven",R.drawable.number_seven,R.raw.number_seven));
        numbersList.add(new Word("kawinta","eight",R.drawable.number_eight,R.raw.number_eight));
        numbersList.add(new Word("wo'e","nine",R.drawable.number_nine,R.raw.number_nine));
        numbersList.add(new Word("na'aacha","ten",R.drawable.number_ten,R.raw.number_ten));

        //Initialising the familyList
        familyList=new ArrayList<>();
        familyList.add(new Word("әpә","father",R.drawable.family_father,R.raw.family_father));
        familyList.add(new Word("әṭa","mother",R.drawable.family_mother,R.raw.family_mother));
        familyList.add(new Word("angsi","son",R.drawable.family_son,R.raw.family_son));
        familyList.add(new Word("tune","daughter",R.drawable.family_daughter,R.raw.family_daughter));
        familyList.add(new Word("taachi","older brother",R.drawable.family_older_brother,R.raw.family_older_brother));
        familyList.add(new Word("chalitti","younger brother",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        familyList.add(new Word("teṭe","older sister",R.drawable.family_older_sister,R.raw.family_older_sister));
        familyList.add(new Word("kolliti","younger sister",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        familyList.add(new Word("ama","grandmother",R.drawable.family_grandmother,R.raw.family_grandmother));
        familyList.add(new Word("paapa","grandfather",R.drawable.family_grandfather,R.raw.family_grandfather));

        //Initialising the colorsList
        colorsList=new ArrayList<>();
        colorsList.add(new Word("weṭeṭṭi","red",R.drawable.color_red,R.raw.color_red));
        colorsList.add(new Word("chokokki","green",R.drawable.color_green,R.raw.color_green));
        colorsList.add(new Word("ṭakaakki","brown",R.drawable.color_brown,R.raw.color_brown));
        colorsList.add(new Word("ṭopoppi","gray",R.drawable.color_gray,R.raw.color_gray));
        colorsList.add(new Word("kululli","black",R.drawable.color_black,R.raw.color_black));
        colorsList.add(new Word("kelelli","white",R.drawable.color_white,R.raw.color_white));
        colorsList.add(new Word("ṭopiisә","dusty yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colorsList.add(new Word("chiwiiṭә","mustard yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        //Initialising the phrasesList
        phrasesList=new ArrayList<>();
        phrasesList.add(new Word("minto wuksus","Where are you going?",R.drawable.icon_phrases,R.raw.phrase_where_are_you_going));
        phrasesList.add(new Word("tinnә oyaase'nә","What is your name?",R.drawable.icon_phrases,R.raw.phrase_what_is_your_name));
        phrasesList.add(new Word("oyaaset...","My name is...",R.drawable.icon_phrases,R.raw.phrase_my_name_is));
        phrasesList.add(new Word("michәksәs?","How are you feeling?",R.drawable.icon_phrases,R.raw.phrase_how_are_you_feeling));
        phrasesList.add(new Word("kuchi achit","I’m feeling good.",R.drawable.icon_phrases,R.raw.phrase_im_feeling_good));
        phrasesList.add(new Word("әәnәs'aa?","Are you coming?",R.drawable.icon_phrases,R.raw.phrase_are_you_coming));
        phrasesList.add(new Word("hәә’ әәnәm","Yes, I’m coming.",R.drawable.icon_phrases,R.raw.phrase_yes_im_coming));
        phrasesList.add(new Word("әәnәm","I’m coming.",R.drawable.icon_phrases,R.raw.phrase_im_coming));
        phrasesList.add(new Word("yoowutis","Let’s go.",R.drawable.icon_phrases,R.raw.phrase_lets_go));
        phrasesList.add(new Word("әnni'nem","Come here.",R.drawable.icon_phrases,R.raw.phrase_come_here));

    }

    public static ArrayList<Word> getNumbersList() {
        return numbersList;
    }

    public static ArrayList<Word> getFamilyList() {
        return familyList;
    }

    public static ArrayList<Word> getColorsList() {
        return colorsList;
    }

    public static ArrayList<Word> getPhrasesList() {
        return phrasesList;
    }
}
