package widder.marry.utils;

import java.util.UUID;

public class MarriageData {
    public UUID player1;
    public UUID player2;
    public String color;

    public MarriageData() {};

    public MarriageData(UUID player1, UUID player2, String color) {
        this.player1 = player1;
        this.player2 = player2;
        this.color = color;
    }

    public boolean involves(UUID player) {
        return player1.equals(player) || player2.equals(player);
    }
}