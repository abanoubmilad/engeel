package abanoubm.engeel.opr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import abanoubm.engeel.R;
import abanoubm.engeel.adp.VersesAdapter;
import abanoubm.engeel.data.Verse;
import abanoubm.engeel.main.BibileInfo;

public class DisplaySearchResults extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        ListView lv = (ListView) findViewById(R.id.list);

        ((TextView) findViewById(R.id.subhead)).setText(R.string.verses);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1,
                                    int position, long arg3) {
                Verse verse = (Verse) parent.getItemAtPosition(position);
                startActivity(new Intent(getApplicationContext(),
                        DisplayVerse.class).putExtra("cid", verse.getCid())
                        .putExtra("bid", verse.getBid())
                        .putExtra("vid", verse.getVid()));
            }

        });
        if (BibileInfo.searchResults == null
                || BibileInfo.searchResults.size() == 0) {
            finish();
            Toast.makeText(getApplicationContext(), "ﻻ توجد آيات متوافقة",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    BibileInfo.searchResults.size() + " آية/آيات ",
                    Toast.LENGTH_SHORT).show();
            lv.setAdapter(new VersesAdapter(getApplicationContext(),
                    BibileInfo.searchResults));
        }

    }
}
