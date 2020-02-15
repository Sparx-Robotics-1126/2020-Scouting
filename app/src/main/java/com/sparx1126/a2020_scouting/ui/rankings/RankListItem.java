package com.sparx1126.a2020_scouting.ui.rankings;

import androidx.annotation.NonNull;

public class RankListItem {
    //this class is the sole checker of proper type and value

    private String details, teamName;
    private Integer teamNumber, wins, losses, ties, rank;

    public RankListItem() {

    }

    public String getTeamNumber(){
        if(teamNumber == null)
            return "No Number!";
        else
            return teamNumber.toString();
    }
    public String getDetails(){
        if(details == null)
            return "No Details!";
        else
            return details;
    }
    public String getRecord(){
        String winsStr, lossesStr, tiesStr;
        if(wins == null)
            winsStr = "?";
        else
            winsStr = wins.toString();

        if(losses == null)
            lossesStr = "?";
        else
            lossesStr = losses.toString();

        if(ties == null)
            tiesStr = "?";
        else
            tiesStr = ties.toString();

        return winsStr + "-" + lossesStr + "-" + tiesStr;
    }
    public String getRank(){
        String rankStr;
        if(rank == null)
            rankStr = "?";
        else
            rankStr = rank.toString();

        return "Rank " + rankStr;
    }
    public String getTeamName(){
        if(teamName == null)
            return "No Team Name";
        else
            return teamName;
    }


    public void setTeamNumber(int teamNumber){
        this.teamNumber = teamNumber;
    }
    public void setDetails(String details){
        this.details = details;
    }
    public void setRecord(int wins, int losses, int ties){
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
}
