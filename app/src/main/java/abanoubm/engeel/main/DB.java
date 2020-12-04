package abanoubm.engeel.main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;
import android.text.Spanned;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipInputStream;

import abanoubm.engeel.data.Verse;

public class DB extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private static String DB_NAME = ".systedefault";
    private static String assets_DB_NAME = "systedefault.zip";
    private static String Tb_NAME = "bshara_tb";
    private static DB dbm;
    private final Context mContext;
    private SQLiteDatabase db;

    private DB(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getFilesDir().getPath();
        this.mContext = context;

        String mPath = DB_PATH + DB_NAME;

        if (!new File(DB_PATH + DB_NAME).exists())
            unpackZip();

        db = SQLiteDatabase.openDatabase(mPath, null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public static DB getInstance(Context context) throws IOException {
        if (dbm == null)
            dbm = new DB(context);
        return dbm;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    private void unpackZip() throws IOException {
        InputStream is;
        ZipInputStream zis;

        is = mContext.getAssets().open(assets_DB_NAME);
        zis = new ZipInputStream(new BufferedInputStream(is));

        while (zis.getNextEntry() != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int count;

            File dir = new File(DB_PATH);
            dir.mkdirs();

            FileOutputStream fout = new FileOutputStream(DB_PATH + DB_NAME);

            while ((count = zis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
                byte[] bytes = baos.toByteArray();
                fout.write(bytes);
                baos.reset();
            }

            fout.close();
            zis.closeEntry();
        }

        zis.close();
    }

    public ArrayList<Verse> search(String text, int choice, int bibleID) {
        String selectQuery;
        if (choice == 0) {
            selectQuery = "SELECT  bid,cid,vid,vtd FROM " + Tb_NAME + " WHERE "
                    + "vt" + " like " + "'%" + text + "%' AND bid < 46";
        } else if (choice == 1) {
            selectQuery = "SELECT  bid,cid,vid,vtd FROM " + Tb_NAME + " WHERE "
                    + "vt" + " like " + "'%" + text + "%' AND bid > 45";
        } else {

            selectQuery = "SELECT  bid,cid,vid,vtd FROM " + Tb_NAME + " WHERE "
                    + "vt" + " like " + "'%" + text + "%'";
        }

        try {
            Cursor c = db.rawQuery(selectQuery, null);
            ArrayList<Verse> result = new ArrayList<Verse>(c.getCount());
            if (c.moveToFirst()) {
                int bidCol = c.getColumnIndex("bid");
                int cidCol = c.getColumnIndex("cid");
                int vidCol = c.getColumnIndex("vid");
                int vtdCol = c.getColumnIndex("vtd");
                do {
                    result.add(new Verse(c.getInt(bidCol), c.getInt(cidCol), c
                            .getInt(vidCol), c.getString(vtdCol)));
                } while (c.moveToNext());
            }
            return result;
        } catch (Exception e) {
            return null;
        }

    }

    public ArrayList<Spanned> getChapter(int bid, int cid) {
        String selectQuery = "SELECT  vtd FROM " + Tb_NAME + " WHERE bid="
                + bid + " AND cid =" + cid;

        try {
            Cursor c = db.rawQuery(selectQuery, null);
            ArrayList<Spanned> result = new ArrayList<Spanned>(c.getCount());
            if (c.moveToNext()) {
                int vtdCol = c.getColumnIndex("vtd");
                int i = 1;
                do {
                    result.add(Html.fromHtml("<font color=\"#0D47A1\">"
                            + BibileInfo.getArabicNum(i) + "</font> "
                            + c.getString(vtdCol)));
                    i++;
                } while (c.moveToNext());
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public String getVerse(int bid, int cid, int vid) {
        String selectQuery = "SELECT  vtd FROM " + Tb_NAME + " WHERE bid="
                + bid + " AND cid =" + cid + " AND vid =" + vid + " limit 1";
        try {
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToNext())
                return c.getString(c.getColumnIndex("vtd"));
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public String getChapterString(int bid, int cid) {
        String selectQuery = "SELECT  vtd FROM " + Tb_NAME + " WHERE bid="
                + bid + " AND cid =" + cid;
        try {
            Cursor c = db.rawQuery(selectQuery, null);
            String results = "";
            if (c.moveToNext()) {
                int vtdCol = c.getColumnIndex("vtd");
                int i = 1;
                do {
                    results += " " + BibileInfo.getArabicNum(i) + " "
                            + c.getString(vtdCol);
                    i++;
                } while (c.moveToNext());
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }
}
