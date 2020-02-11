package com.sparx1126.a2020_scouting.ui.rankings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.sparx1126.a2020_scouting.R;

public class RankArrayAdapter extends ArrayAdapter<RankListItem> {

    private Context context;
    private List<RankListItem> rankList = new ArrayList<>();

    public RankArrayAdapter(Context context, ArrayList<RankListItem> list) {
        super(context, 0 , list);
        this.context = context;
        rankList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.rank_item_layout, parent,false);

        RankListItem rankItem = rankList.get(position);

        //multiple of...
        TextView name = (TextView) listItem.findViewById(R.id.teamNumber);
        name.setText(rankItem.getTeamNumber());
        //these

        return listItem;
    }
}
