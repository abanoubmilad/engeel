package abanoubm.engeel.data;

public class VerseContainer {

    private String rightText, leftText;

    public VerseContainer(String rightText, String leftText) {
        this.rightText = rightText;
        this.leftText = leftText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

}
