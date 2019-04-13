import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.NodeChangeEvent;

public class Graph {
    private HashMap<String, Node> nodes;
    private ArrayList<Creature> creatures;
    private Player p;

    public Player getPlayer() {
        return p;
    }

    public Graph(Player p){
        nodes = new HashMap<>();
        creatures = new ArrayList<>();
        this.p = p;
    }

    public void addNode(String name, String description) {
        Node n = new Node(name, description);
        nodes.put(name, n);
    }

    public void addDirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        if(n1 == null || n2 == null){
            System.out.println("One of your nodes do not exist");
        }
        else{
            n1.addNeighbor(n2);
        }
    }

    public void addUndirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        if(n1 == null || n2 == null){
            System.out.println("One of your nodes do not exist");
        }
        else{
            n1.addNeighbor(n2);
            n2.addNeighbor(n1);
        }
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public Graph.Node getRandomRoom(){
        ArrayList<Node> rooms = new ArrayList<>(nodes.values());
        if(rooms.size() == 0){
            return null;
        }
        else {
            int randomNum = (int)(Math.random() * rooms.size());
            return rooms.get(randomNum);
        }
    }

    public void addCreatures(Creature c){
        creatures.add(c);
    }

    public void tickAllCreatures(){
        for (int i = 0; i < this.creatures.size(); i++) {
             this.creatures.get(i).move();
        }
    }


    public class Node{
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private HashMap<String, Item> nodeItems;
        private ArrayList<Creature> chickens;
        private ArrayList<Creature> wumpuses;
        private ArrayList<Creature> popstars;

        public String getDescription() {
            return description;
        }

        public Node(String name, String description) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            nodeItems = new HashMap<>();
            chickens = new ArrayList<>();
            wumpuses = new ArrayList<>();
            popstars = new ArrayList<>();
        }

        public void addNeighbor(Node n){
            neighbors.put(n.getName(), n);
        }

        public String getNeighborNames(){
            String output = "";
            for (String name: neighbors.keySet()) {
                output += name + " ";
            }
            return output;
        }
        public String getName(){
            return name;
        }
        public HashMap<String, Item> getItems(){
            return nodeItems;
        }
        public void displayItems(){
            for(String name: this.getItems().keySet()){
                System.out.println(name + ". The description of this item is: " + this.getItems().get(name).getDescription());
            }
        }
        public void addItem(Item item){
            if(item == null){
                System.out.println("You already dropped that item or you don't possess that item right now");
            }
            else{
                nodeItems.put(item.getName(), item);
            }
        }
        public Item removeItem(String name){
            Item output = nodeItems.get(name);
            nodeItems.remove(name);
            return output;
        }

        public Graph.Node getRandomRoom(){
            ArrayList<Graph.Node> rooms = new ArrayList<>(neighbors.values());
            if(rooms.size() == 0){
                return this;
            }
            else {
                int randomNum = (int)(Math.random() * rooms.size());
                return rooms.get(randomNum);
            }
        }

        public void addWumpuses(Creature c){
            wumpuses.add(c);
        }

        public Creature removeWumpuses(Creature c){
            Creature output = c;
            wumpuses.remove(c);
            return output;
        }

        public int getNumberOfWumpuses(){
            return wumpuses.size();
        }

        public void addChickens(Creature c){
            chickens.add(c);
        }

        public Creature removeChickens(Creature c){
            Creature output = c;
            chickens.remove(c);
            return output;
        }

        public int getNumberOfChickens(){
            return chickens.size();
        }

        public void addPopstars(Creature c){
            popstars.add(c);
        }

        public Creature removePopstars(Creature c){
            Creature output = c;
            popstars.remove(c);
            return output;
        }

        public int getNumberOfPopstars(){
            return popstars.size();
        }

        public HashMap<String, Node> getNeighbors() {
            return neighbors;
        }

    }


}

