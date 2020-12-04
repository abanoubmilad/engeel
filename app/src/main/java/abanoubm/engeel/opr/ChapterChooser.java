package abanoubm.engeel.opr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import abanoubm.engeel.R;
import abanoubm.engeel.adp.GridBaseAdapter;
import abanoubm.engeel.main.BibileInfo;

public class ChapterChooser extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_chooser);

        final int bid = getIntent().getIntExtra("bid", 0);

        ((TextView) findViewById(R.id.subhead)).setText("أصحاحات " + "\n"
                + BibileInfo.bibleNames[bid]);

        GridView grid = (GridView) findViewById(R.id.grid_view);
        grid.setAdapter(new GridBaseAdapter(this, BibileInfo.bibleLengths[bid]));
        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Intent intent = new Intent(getApplicationContext(),
                        VerseChooser.class).putExtra("bid", bid).putExtra(
                        "cid", position + 1);
                startActivity(intent);
            }
        });

    }
}
