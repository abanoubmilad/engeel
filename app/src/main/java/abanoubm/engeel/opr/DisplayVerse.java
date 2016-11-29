package abanoubm.engeel.opr;

import java.net.URLEncoder;
import java.util.List;

import abanoubm.engeel.R;
import abanoubm.engeel.main.BibileInfo;
import abanoubm.engeel.main.DB;
import abanoubm.engeel.main.FavouriteDB;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayVerse extends Activity {
	private TextView displayText;
	private String shahd;
	private ImageView favouriteBtn;
	private boolean isFavourite = false;

	private class FetchVerseTask extends AsyncTask<Integer, Void, String> {

		@Override
		protected String doInBackground(Integer... params) {

			try {
				return DB.getInstance(getApplicationContext()).getVerse(
						params[0], params[1], params[2]);
			} catch (Exception e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(String str) {
			if (str != null)
				displayText.setText(str + " " + shahd);
			else
				Toast.makeText(getApplicationContext(), R.string.err_msg_db,
						Toast.LENGTH_SHORT).show();
		}
	}

	private class CheckFavouriteTask extends AsyncTask<Integer, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(DisplayVerse.this);
			pBar.setCancelable(false);
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(Integer... params) {

			try {
				return FavouriteDB.getInstance(getApplicationContext())
						.checkVerse(params[0], params[1], params[2]);
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				favouriteBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.ic_fav));
				isFavourite = true;
			} else {
				favouriteBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.ic_add));
				isFavourite = false;

			}
			pBar.dismiss();
		}
	}

	private class AddFavouriteTask extends AsyncTask<Integer, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(DisplayVerse.this);
			pBar.setCancelable(false);
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(Integer... params) {

			try {
				return FavouriteDB.getInstance(getApplicationContext())
						.addVerse(params[0], params[1], params[2], "");
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result == true) {
				favouriteBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.ic_fav));
				isFavourite = true;
				Toast.makeText(getApplicationContext(), R.string.msg_added,
						Toast.LENGTH_SHORT).show();
			}
			pBar.dismiss();
		}
	}

	private class RemoveFavouriteTask extends AsyncTask<Integer, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(DisplayVerse.this);
			pBar.setCancelable(false);
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(Integer... params) {

			try {
				FavouriteDB.getInstance(getApplicationContext()).removeVerse(
						params[0], params[1], params[2]);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result == true) {
				favouriteBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.ic_add));
				isFavourite = false;
				Toast.makeText(getApplicationContext(), R.string.msg_removed,
						Toast.LENGTH_SHORT).show();
			}
			pBar.dismiss();
		}
	}

	private String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_verse);

		final int bid = getIntent().getIntExtra("bid", 0), cid = getIntent()
				.getIntExtra("cid", 1), vid = getIntent().getIntExtra("vid", 1);

		shahd = BibileInfo.shahhd[bid] + "(" + BibileInfo.getArabicNum(cid)
				+ " : " + BibileInfo.getArabicNum(vid) + ")";

		((TextView) findViewById(R.id.subhead)).setText(shahd);

		TextView tw = (TextView) findViewById(R.id.tw_iv);
		TextView fb = (TextView) findViewById(R.id.fb_iv);

		favouriteBtn = (ImageView) findViewById(R.id.fav);
		displayText = (TextView) findViewById(R.id.display);

		new FetchVerseTask().execute(bid, cid, vid);
		new CheckFavouriteTask().execute(bid, cid, vid);

		favouriteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isFavourite) {
					new RemoveFavouriteTask().execute(bid, cid, vid);
				} else {
					new AddFavouriteTask().execute(bid, cid, vid);

				}

			}
		});
		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent shareIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						getSelection());
				v.getContext().startActivity(
						Intent.createChooser(shareIntent, "شير"));
			}
		});
		tw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String tweetUrl = String.format(
						"https://twitter.com/intent/tweet?text=%s",
						urlEncode(getSelection()));
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(tweetUrl));

				List<ResolveInfo> matches = getPackageManager()
						.queryIntentActivities(intent, 0);
				for (ResolveInfo info : matches) {
					if (info.activityInfo.packageName.toLowerCase().startsWith(
							"com.twitter")) {
						intent.setPackage(info.activityInfo.packageName);
					}
				}

				startActivity(intent);
			}
		});
	}

	private String getSelection() {
		String temp = displayText.getText().toString();

		if (displayText.isFocused()) {
			int selStart = displayText.getSelectionStart();
			int selEnd = displayText.getSelectionEnd();

			temp = displayText
					.getText()
					.subSequence(Math.max(0, Math.min(selStart, selEnd)),
							Math.max(0, Math.max(selStart, selEnd))).toString();
			if (temp.length() < 2)
				temp = displayText.getText().toString();
		}
		return temp;
	}
}
