package abanoubm.engeel.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import abanoubm.engeel.BuildConfig;
import abanoubm.engeel.R;
import abanoubm.engeel.opr.BibleChooser;
import abanoubm.engeel.opr.DisplaySearchResults;
import abanoubm.engeel.opr.Search;
import abanoubm.engeel.opr.ShahdDisplay;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView) findViewById(R.id.footer)).setText(
                getResources().getString(R.string.copyright,
                        BuildConfig.VERSION_NAME,
                        new SimpleDateFormat("yyyy", Locale.getDefault())
                                .format(new Date())));
        ((TextView) findViewById(R.id.subhead)).setText(R.string.app_name);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                R.layout.item, R.id.item, BibileInfo.menuItems));

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        new GetFavouritesTask().execute();
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(),
                                BibleChooser.class).putExtra("new_bible", true));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),
                                BibleChooser.class).putExtra("new_bible", false));
                        break;
                    case 3:

                        startActivity(new Intent(getApplicationContext(),
                                Search.class));
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),
                                ShahdDisplay.class));
                        break;
                    case 5:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/1363784786"))
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        } catch (Exception e) {
                            startActivity(
                                    new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/abanoubmiladhanna/"))
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        break;
                }
            }
        });

    }

    private class GetFavouritesTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog pBar;

        @Override
        protected void onPreExecute() {
            pBar = new ProgressDialog(Main.this);
            pBar.setCancelable(false);
            pBar.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                BibileInfo.searchResults = FavouriteDB.getInstance(
                        getApplicationContext()).getFavourites(
                        getApplicationContext());
                return BibileInfo.searchResults.size() != 0;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                startActivity(new Intent(getApplicationContext(),
                        DisplaySearchResults.class));
            else
                Toast.makeText(getApplicationContext(), R.string.msg_no_fav,
                        Toast.LENGTH_SHORT).show();
            pBar.dismiss();
        }
    }
}
