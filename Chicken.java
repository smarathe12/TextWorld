import java.util.ArrayList;

public class Chicken extends Creature {

    public Chicken(Graph.Node currentRoom, boolean isAlive, Player p, Graph g) {
        super(currentRoom, isAlive, p, g);
        this.g.addCreatures(this);
    }

    @Override
    protected void move() {
        if (p.getCurrentRoom().getNumberOfChickens() != 0) {
            this.currentRoom.removeChickens(this);
        }
        this.currentRoom = surveySurroundings(currentRoom);
        this.currentRoom.addChickens(this);
    }

    private Graph.Node surveySurroundings(Graph.Node currentCreatureRoom){
        return currentCreatureRoom.getRandomRoom();
    }

}

