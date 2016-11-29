package abanoubm.engeel.opr;

import java.util.ArrayList;

import abanoubm.engeel.R;
import abanoubm.engeel.main.BibileInfo;
import abanoubm.engeel.main.DB;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VerseChooser extends Activity {
	private int cid, bid;
	private ListView lv;

	private class GetVersesTask extends
			AsyncTask<Integer, Void, ArrayList<Spanned>> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(VerseChooser.this);
			pBar.setCancelable(false);
			pBar.show();
		}

		@Override
		protected ArrayList<Spanned> doInBackground(Integer... params) {
			try {
				return DB.getInstance(getApplicationContext()).getChapter(
						params[0].intValue(), params[1].intValue());
			} catch (Exception e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(ArrayList<Spanned> result) {
			if (result != null) {
				ArrayAdapter<Spanned> adapter = new ArrayAdapter<Spanned>(
						getApplicationContext(), R.layout.item, R.id.item,
						result);
				lv.setAdapter(adapter);

			} else
				Toast.makeText(getApplicationContext(), R.string.err_msg_db,
						Toast.LENGTH_LONG).show();
			pBar.dismiss();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooser);

		bid = getIntent().getIntExtra("bid", 0);
		cid = getIntent().getIntExtra("cid", 1);
		lv = (ListView) findViewById(R.id.list);

		((TextView) findViewById(R.id.subhead)).setText(BibileInfo
				.getBibleChapterStr(bid, cid));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				startActivity(new Intent(getApplicationContext(),
						DisplayVerse.class).putExtra("cid", cid)
						.putExtra("bid", bid).putExtra("vid", position + 1));

			}
		});
		new GetVersesTask().execute(bid, cid);

	}
}
