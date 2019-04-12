import java.util.ArrayList;
import java.util.Collections;

public class PopStar extends Creature{

    public PopStar(Graph.Node currentRoom, boolean isAlive, Player p, Graph g) {
        super(currentRoom, isAlive, p, g);
        this.g.addCreatures(this);
    }

    @Override
    protected void move() {
        if (p.getCurrentRoom().getNumberOfPopstars() != 0) {
            this.currentRoom.removePopstars(this);
        }
        Graph.Node next = selectRoom();
        this.currentRoom = next;
        this.currentRoom.addPopstars(this);
    }

    private Graph.Node selectRoom() {
        ArrayList<Graph.Node> rooms = new ArrayList<>(p.currentRoom.getNeighbors().values());
        Collections.shuffle(rooms);
        while (rooms.size() > 0) {
            Graph.Node next = rooms.remove(0);
            if (p.getCurrentRoom().equals(next)) {
                ArrayList<Graph.Node> rooms2 = new ArrayList<>(next.getNeighbors().values());
                Collections.shuffle(rooms2);
                while (rooms2.size() > 0) {
                    Graph.Node next2 = rooms2.remove(0);
                    if (p.getCurrentRoom().equals(next2)) return next;
                }
            }
        }
        return currentRoom;
    }

}
