import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Wumpus extends Creature {

    public Wumpus(Graph.Node currentRoom, boolean isAlive, Player p, Graph g) {
        super(currentRoom, isAlive, p, g);
        this.g.addCreatures(this);
    }

    @Override
    protected void move() {
        this.currentRoom.removeWumpuses(this);
        this.currentRoom = selectRoom();
        this.currentRoom.addWumpuses(this);
    }

    private Graph.Node selectRoom() {
        if (currentRoom.equals(p.currentRoom)) {
            return currentRoom.getRandomRoom();
        }
        ArrayList<Graph.Node> rooms = new ArrayList<>(currentRoom.getNeighbors().values());
        Collections.shuffle(rooms);
        while (rooms.size() > 0) {
            Graph.Node next = rooms.remove(0);
            if (!p.getCurrentRoom().equals(next)) {
                ArrayList<Graph.Node> rooms2 = new ArrayList<>(next.getNeighbors().values());
                Collections.shuffle(rooms2);
                while (rooms2.size() > 0) {
                    Graph.Node next2 = rooms2.remove(0);
                    if (!p.getCurrentRoom().equals(next2)) return next;
                }
            }
        }
        return currentRoom;
    }
}