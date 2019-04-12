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
        if (p.getCurrentRoom().getNumberOfWumpuses() != 0) {
            this.currentRoom.removeWumpuses(this);
        }

        Graph.Node next = selectRoom();
        this.currentRoom = next;
//        System.out.println(this);
        this.currentRoom.addWumpuses(this);
    }

    public Graph.Node selectRoom() {
        Graph.Node newRoom;
        ArrayList<Graph.Node> rooms = new ArrayList<>(p.currentRoom.getNeighbors().values());
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
//    public Graph.Node selectRoomFor2Step() {
//        ArrayList<Graph.Node> rooms = new ArrayList<>(currentRoom.getNeighbors().values());
//        Collections.shuffle(rooms);
//        while (rooms.size() > 0) {
//            Graph.Node next = rooms.remove(0);
//            ArrayList<Graph.Node> rooms2 = new ArrayList<>(next.getNeighbors().values());
//            Collections.shuffle(rooms2);
//            while (rooms2.size() > 0) {
//                Graph.Node next2 = rooms2.remove(0);
//                if (!p.getCurrentRoom().equals(next2)) return next;
//            }
//        }
//        return null;
//    }
}

//    public Graph.Node selectRoom() {
//
//        ArrayList<Graph.Node> rooms = new ArrayList<>(p.currentRoom.getNeighbors().values());
//        Collections.shuffle(rooms);
//        while (rooms.size() > 0) {
//            Graph.Node next = rooms.remove(0);
//            if (!p.getCurrentRoom().equals(next)) return next;
//        }
//        return null;
//    }