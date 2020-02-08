package com.sparx1126.a2020_scouting.ui.rankings;

import android.widget.LinearLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sparx1126.a2020_scouting.R;

public class RankCustomLayout extends LinearLayout {

    private TextView number, details, record, rank, team;

    public RankCustomLayout(Context _context, AttributeSet _attrs) {
        super(_context, _attrs);

        LayoutInflater layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.rank_custom_layout, this);

        if (view != null) {
            number = view.findViewById(R.id.number);
            details = view.findViewById(R.id.details);
            record = view.findViewById(R.id.record);
            rank = view.findViewById(R.id.rank);
            team = view.findViewById(R.id.team);
        }
    }
}
