package com.sparx1126.a2020_scouting.ui.rankings;

import android.widget.LinearLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sparx1126.a2020_scouting.R;

public class RankCustomLayout extends LinearLayout {

    private TextView teamNumber, details, record, rank, teamName;

    public RankCustomLayout(Context _context, AttributeSet _attrs) {
        super(_context, _attrs);

        LayoutInflater layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.rank_custom_layout, this);

        if (view != null) {
            teamNumber = view.findViewById(R.id.teamNumber);
            details = view.findViewById(R.id.details);
            record = view.findViewById(R.id.record);
            rank = view.findViewById(R.id.rank);
            teamName = view.findViewById(R.id.teamName);
        }
    }

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
}
