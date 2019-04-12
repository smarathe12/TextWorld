import java.util.ArrayList;

public abstract class Creature {
    protected Graph.Node currentRoom;
    protected boolean isAlive;
    protected Player p;
    protected Graph g;

    public Creature(Graph.Node currentRoom, boolean isAlive, Player p, Graph g) {
        this.currentRoom = currentRoom;
        this.isAlive = isAlive;
        this.p = p;
        this.g = g;
}

    protected abstract void move();

    public boolean isAlive() {
        return isAlive;
    }

    public Graph.Node getCurrentRoom() {
        return currentRoom;
    }

}
