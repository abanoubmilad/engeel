package abanoubm.engeel.adp;

import java.util.ArrayList;

import abanoubm.engeel.R;
import abanoubm.engeel.data.Verse;
import abanoubm.engeel.main.BibileInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class VersesAdapter extends ArrayAdapter<Verse> {

	public VersesAdapter(Context context, ArrayList<Verse> verses) {
		super(context, 0, verses);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.verse_item, parent, false);
		}
		Verse verse = getItem(position);

		((TextView) convertView.findViewById(R.id.headertv))
				.setText(BibileInfo.bibleNames[verse.getBid()] + " ( "
						+ BibileInfo.getArabicNum(verse.getCid()) + " : "
						+ BibileInfo.getArabicNum(verse.getVid()) + " )");
		((TextView) convertView.findViewById(R.id.versetv)).setText(verse
				.getText());

		return convertView;
	}
}