package widder.marry.utils;

public class MarriageData {
    public String player1;
    public String player2;
    public String color;

    public MarriageData() {};

    public MarriageData(String player1, String player2, String color) {
        this.player1 = player1;
        this.player2 = player2;
        this.color = color;
    }

    public boolean involves(String player) {
        return player1.equals(player) || player2.equals(player);
    }
}