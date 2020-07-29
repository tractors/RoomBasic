package com.will.roombasic;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private WordRepository repository;
    private boolean isUpdate = false;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return repository.getAllWordsLive();
    }


    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public void insertWords(Word... words){
        repository.insertWords(words);
    }

    public void updateWords(Word... words){
        repository.updateWords(words);
    }

    public void deleteWords(Word... words){
        repository.deleteWords(words);
    }

    public void deleteAllWords(){
        repository.deleteAllWords();
    }


}
