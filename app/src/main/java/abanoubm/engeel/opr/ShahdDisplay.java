package abanoubm.engeel.opr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import abanoubm.engeel.R;
import abanoubm.engeel.adp.GridListAdapter;
import abanoubm.engeel.main.BibileInfo;

public class ShahdDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shahd);

        ((TextView) findViewById(R.id.subhead)).setText(R.string.shahd);

        final EditText chapternum = (EditText) findViewById(R.id.chaptertv);
        final EditText number = (EditText) findViewById(R.id.numbtv);

        GridView grid = (GridView) findViewById(R.id.grid_view);
        grid.setAdapter(new GridListAdapter(this, Arrays
                .asList(BibileInfo.shahhd)));
        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                String chn = chapternum.getText().toString().trim();
                String num = number.getText().toString().trim();
                if (chn.length() + num.length() == 0) {
                    startActivity(new Intent(getApplicationContext(),
                            ChapterChooser.class).putExtra("bid", position));
                } else if (chn.length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            " ادخل الاصحاح", Toast.LENGTH_SHORT)
                            .show();
                } else if (!BibileInfo.isValidChapterNum(chn)) {
                    Toast.makeText(getApplicationContext(),
                            " الاصحاح غير صالح", Toast.LENGTH_SHORT)
                            .show();
                } else if (num.length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            " ادخل العدد", Toast.LENGTH_SHORT).show();
                } else if (!BibileInfo.isValidVerseNum(num)) {
                    Toast.makeText(getApplicationContext(), " العدد غير صالح ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(),
                            DisplayVerse.class).putExtra("cid", chn)
                            .putExtra("bid", position).putExtra("vid", num));
                }

            }
        });

    }
}
