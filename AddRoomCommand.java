import java.util.Scanner;

public class AddRoomCommand implements Command {
    Graph g;
    String roomName;

    public AddRoomCommand(Graph g){
        this.g = g;
    }

    @Override
    public void init(String userString) {
        this.roomName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        if (userString.indexOf("go") != -1) {
            return userString.substring(userString.indexOf("m") + 2);
        }
        return "";
    }

    @Override
    public boolean execute() {
        Player p = g.getPlayer();
        Scanner userDescription = new Scanner(System.in);
        System.out.println("Enter a description for your room");
        g.addNode(roomName, userDescription.nextLine());
        g.addDirectedEdge(p.getCurrentRoom().getName(), roomName);
        System.out.println("You have successfully added " + roomName);
        return true;
    }
}
