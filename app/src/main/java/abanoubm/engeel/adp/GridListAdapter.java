package abanoubm.engeel.adp;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import abanoubm.engeel.R;

public class GridListAdapter extends ArrayAdapter<String> {
    private static int white = Color.WHITE,
            grey;

    public GridListAdapter(Activity context, List<String> strs) {
        super(context, 0, strs);
        grey = ContextCompat.getColor(context, R.color.light_grey);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }
        TextView t = (TextView) convertView.findViewById(R.id.item_text);
        t.setText(getItem(position));

        if (position % 2 == 0)
            t.setBackgroundColor(white);
        else
            t.setBackgroundColor(grey);

        return convertView;
    }
}