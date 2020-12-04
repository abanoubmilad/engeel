package abanoubm.engeel.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;

import abanoubm.engeel.data.Verse;

public class FavouriteDB extends SQLiteOpenHelper {
    private static final String TAG_BID = "bid", TAG_CID = "cid",
            TAG_VID = "vid";
    private static String DB_NAME = "fav_db";
    private static String Tb_NAME = "fav_tb";
    private static FavouriteDB dbm;

    private FavouriteDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public static FavouriteDB getInstance(Context context) throws IOException {
        if (dbm == null)
            dbm = new FavouriteDB(context);
        return dbm;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {

        String sql = "create table " + Tb_NAME + " ( " + TAG_BID + " integer, "
                + TAG_CID + " integer, " + TAG_VID + " integer) ";

        arg0.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public Boolean checkVerse(int bid, int cid, int vid) {
        String selectQuery = "SELECT  " + TAG_BID + " FROM " + Tb_NAME
                + " WHERE " + TAG_BID + "=" + bid + " AND " + TAG_CID + "="
                + cid + " AND " + TAG_VID + "=" + vid + " limit 1";
        try {
            Cursor c = getReadableDatabase().rawQuery(selectQuery, null);
            return c.moveToNext();
        } catch (Exception e) {
            return null;
        }

    }

    public boolean addVerse(int bid, int cid, int vid, String comm) {
        ContentValues values = new ContentValues();
        values.put(TAG_BID, bid);
        values.put(TAG_CID, cid);
        values.put(TAG_VID, vid);
        return (int) getWritableDatabase().insert(Tb_NAME, null, values) != -1;
    }

    public void removeVerse(int bid, int cid, int vid) {
        String deleteQuery = "DELETE FROM " + Tb_NAME + " WHERE " + TAG_BID
                + "=" + bid + " AND " + TAG_CID + "=" + cid + " AND " + TAG_VID
                + "=" + vid;
        getWritableDatabase().execSQL(deleteQuery);
    }

    public ArrayList<Verse> getFavourites(Context context) {
        String selectQuery = "SELECT  * FROM " + Tb_NAME;
        try {
            Cursor c = getReadableDatabase().rawQuery(selectQuery, null);
            ArrayList<Verse> result = new ArrayList<Verse>(c.getCount());
            if (c.moveToFirst()) {
                int bidCol = c.getColumnIndex(TAG_BID);
                int cidCol = c.getColumnIndex(TAG_CID);
                int vidCol = c.getColumnIndex(TAG_VID);
                DB db = DB.getInstance(context);
                String temp;
                do {
                    temp = db.getVerse(c.getInt(bidCol), c.getInt(cidCol),
                            c.getInt(vidCol));
                    result.add(new Verse(c.getInt(bidCol), c.getInt(cidCol), c
                            .getInt(vidCol), temp));
                } while (c.moveToNext());
            }
            return result;
        } catch (Exception e) {
            return null;
        }

    }
}
