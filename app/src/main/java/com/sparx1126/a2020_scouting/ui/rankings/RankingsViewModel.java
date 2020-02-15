package com.sparx1126.a2020_scouting.ui.rankings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RankingsViewModel extends ViewModel {

    private MutableLiveData<String> team;
    private MutableLiveData<String> rank;
    private MutableLiveData<String> team_name;
    private MutableLiveData<String> record;
    private MutableLiveData<String> details;

    public RankingsViewModel() {
        team = new MutableLiveData<>();
        rank = new MutableLiveData<>();
        team_name = new MutableLiveData<>();
        record = new MutableLiveData<>();
        details = new MutableLiveData<>();
    }

    public LiveData<String> getTeam() {
        return team;
    }

    public LiveData<String> getRank() {
        return rank;
    }

    public LiveData<String> getTeam_name() {
        return team_name;
    }

    public LiveData<String> getRecord() {
        return record;
    }

    public LiveData<String> getDetails() {
        return details;
    }

    public void setTeam(String _value) {
        team.setValue(_value);
    }

    public void setRank(String _value) {
        rank.setValue(_value);
    }

    public void setTeam_name(String _value) {
        team_name.setValue(_value);
    }

    public void setRecord(String _value) {
        record.setValue(_value);
    }

    public void setDetails(String _value) {
        details.setValue(_value);
    }
}
