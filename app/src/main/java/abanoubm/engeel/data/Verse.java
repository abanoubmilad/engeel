package abanoubm.engeel.data;

public class Verse {

    private int bid;
    private int cid;
    private int vid;
    private String text;

    public Verse(int bid, int cid, int vid, String text) {
        super();
        this.bid = bid;
        this.cid = cid;
        this.vid = vid;
        this.text = text;
    }

    public int getBid() {
        return bid;
    }

    public int getCid() {
        return cid;
    }

    public int getVid() {
        return vid;
    }

    public String getText() {
        return text;
    }

}
