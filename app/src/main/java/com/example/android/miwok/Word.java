package com.example.android.miwok;

import java.io.Serializable;
import java.util.Objects;

public final class Word implements Serializable {
    private String miwokWord;
    private String englishWord;
    private int imageResourceId;
    private int audioResourceID;

    public Word(String miwokWord, String englishWord) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
    }

    public Word(String miwokWord, String englishWord, int imageResourceId) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.imageResourceId = imageResourceId;
    }

    public Word(String miwokWord, String englishWord, int imageResourceId, int audioResourceID) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.imageResourceId = imageResourceId;
        this.audioResourceID = audioResourceID;
    }

    public int getAudioResourceID() {
        return audioResourceID;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setMiwokWord(String miwokWord) {
        this.miwokWord = miwokWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return getImageResourceId() == word.getImageResourceId() &&
                Objects.equals(getMiwokWord(), word.getMiwokWord()) &&
                Objects.equals(getEnglishWord(), word.getEnglishWord());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getMiwokWord(), getEnglishWord(), getImageResourceId());
    }
}
