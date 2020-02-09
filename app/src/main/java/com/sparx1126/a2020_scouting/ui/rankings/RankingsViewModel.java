package com.sparx1126.a2020_scouting.ui.rankings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RankingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RankingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}