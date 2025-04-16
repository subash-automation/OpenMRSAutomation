package enums;

public enum Timeout {

    THREE_SEC(3),
    FIVE_SEC(5),
    TEN_SEC(10),
    TWENTY_SEC(20),
    THIRTY_SEC(30);

    private int seconds;
    Timeout(int seconds){
        this.seconds = seconds;
    }

    public int getSeconds(){
        return seconds;
    }
}
