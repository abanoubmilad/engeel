package abanoubm.engeel.opr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import abanoubm.engeel.R;
import abanoubm.engeel.main.BibileInfo;

public class BibleChooser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        final boolean isNewBible = getIntent().getBooleanExtra("new_bible",
                false);
        if (isNewBible)
            ((TextView) findViewById(R.id.subhead))
                    .setText(R.string.new_bibles);
        else
            ((TextView) findViewById(R.id.subhead))
                    .setText(R.string.old_bibles);

        ListView lv = (ListView) findViewById(R.id.list);

        if (isNewBible)
            lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item,
                    R.id.item, new ArrayList<String>(Arrays
                    .asList(BibileInfo.bibleNewTestNames))));
        else
            lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item,
                    R.id.item, new ArrayList<String>(Arrays
                    .asList(BibileInfo.bibleOldTestNames))));

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long arg3) {
                if (isNewBible)

                    startActivity(new Intent(getApplicationContext(),
                            ChapterChooser.class)
                            .putExtra("bid", position + 46));
                else
                    startActivity(new Intent(getApplicationContext(),
                            ChapterChooser.class).putExtra("bid", position));

            }
        });
    }
}
