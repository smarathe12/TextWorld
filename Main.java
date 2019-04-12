import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, Command> commands = new HashMap<>();
    public static Player p = new Player("Bolt", "The talking dog");
    public static Graph g = new Graph(p);


    public static void main(String[] args) {

        initCommands();
        g.addNode("hall", "a long dank hallway");
        g.addNode("closet", "a dark, dark closet");
        g.addNode("dungeon", "where the monsters live...");

        Item blackRobe = new Item("black robe", "a secret invisibility cloak");
        Item sword = new Item("sword", "to defeat the monsters in the dungeon");
        Item phone = new Item("phone", "to call your loved ones");
        Item spellBook = new Item("spell book", "to summon the most awesome spells");
        Item wand = new Item("wand", "you can't use the spells without the wand");
        Item medicine = new Item("medicine", "in case the dungeon monster attacks you");

        ArrayList<Creature> chickens = new ArrayList<>();
        ArrayList<Creature> wumpuses = new ArrayList<>();
        ArrayList<Creature> popstars = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Graph.Node randRoom = g.getRandomRoom();
            System.out.println(g.getRandomRoom().getName());
            Creature chicken = new Chicken(randRoom, true, p, g);
            Creature wumpus = new Wumpus(randRoom, true, p, g);
            Creature popstar = new PopStar(randRoom, true, p, g);
            randRoom.addChickens(chicken);
            chickens.add(chicken);
            randRoom.addChickens(wumpus);
            wumpuses.add(wumpus);
            randRoom.addChickens(popstar);
            popstars.add(popstar);
        }

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        p.setCurrentRoom(g.getNode("hall"));
        g.getNode("hall").addItem(blackRobe);
        g.getNode("hall").addItem(sword);
        g.getNode("hall").addItem(phone);
        g.getNode("closet").addItem(blackRobe);
        g.getNode("closet").addItem(spellBook);
        g.getNode("dungeon").addItem(wand);
        g.getNode("dungeon").addItem(medicine);


        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + p.getCurrentRoom().getName());
            if (p.getItems().size() == 0) {
                System.out.println("You currently possess no items");
            } else {
                System.out.println("You possess the: ");
                p.displayInventory();
            }
            if (response.equals("quit")) {
                response = "quit";
            }
            g.tickAllCreatures();
            System.out.println("What do you want to do? >");
            response = s.nextLine();
            Command command = lookUpCommand(response);
            boolean success = command.execute();
            if (success == false) {
                System.out.println("The commands you can run are: ");
                System.out.println("Type go <roomname> to go to that room");
                System.out.println("Type look to see the neighbors for the room you are in");
                System.out.println("Type add room <roomname> to add a neighbor to your current room");
                System.out.println("Type take <itemname> to take an item avaible in your current room");
                System.out.println("Type drop <itemname> to drop an item you possess in your current room");
                System.out.println("Type quit to exit the game");
            } else {
                System.out.println("action was successful!");
            }
        } while (!response.equals("quit"));
    }


    private static void initCommands() {
        commands.put("take", new TakeCommand(g));
        commands.put("look", new LookCommand(g));
        commands.put("add-room", new AddRoomCommand(g));
        commands.put("drop", new DropCommand(g));
        commands.put("empty", new EmptyCommand());
        commands.put("go", new GoCommand(g));
    }

    private static Command lookUpCommand(String response) {
        String commandWord = getFirstWordIn(response);
        Command c = commands.get(commandWord);
        if (c == null) return new EmptyCommand();
        c.init(response);
        return c;
    }

    private static String getFirstWordIn(String response) {
        if (response.contains(" ")) {
            return response.substring(0, response.indexOf(" "));
        }
        return response;
    }
}
