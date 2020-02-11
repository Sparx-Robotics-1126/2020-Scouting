package com.sparx1126.a2020_scouting.ui.rankings;

import android.widget.LinearLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sparx1126.a2020_scouting.R;

public class RankListItem {

    private String teamNumber, details, record, rank, teamName;

    public RankListItem() {
        teamNumber = "1234";
        details = "ploopy";
        record = "6-4-0";
        rank = "3";
        teamName = "Sprax";
    }
    //temporary for testing

    public String getTeamNumber(){
        return teamNumber;
    }
    public String getDetails(){
        return details;
    }
    public String getRecord(){
        return record;
    }
    public String getRank(){
        return rank;
    }
    public String getTeamName(){
        return teamName;
    }

    /*
    public void setTeamNumber(Integer teamNumber){
        this.teamNumber.setText(teamNumber.toString());
    }
    public void setDetails(String details){
        this.details.setText(details);
    }
    public void setRecord(int wins, int losses, int ties){
        record.setText(wins + "-" + losses + "-" + ties);
    }
    public void setRank(int rank){
        this.rank.setText("Rank " + rank);
    }
    public void setTeamName(String teamName){
        this.teamName.setText(teamName);
    }
     */
}
