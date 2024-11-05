package Models;

public class Players {
    public PlayersType type;
    public String Name;

    public Players(PlayersType type, String Name) {
        this.type = type;
        this.Name = Name;
    }

    public PlayersType getPlayerType() {
        return type;
    }

    public String getName() {
        return this.Name;
    }

    public void setPlayerType(PlayersType playerType) {
        this.type = playerType;
    }

    public void setPlayerName(String name) {
        Name = name;
    }

}
