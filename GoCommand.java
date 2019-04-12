public class GoCommand implements Command {
    Graph g;
    String roomName;

    public GoCommand(Graph g){
        this.g = g;
    }

    @Override
    public void init(String userString) {
        this.roomName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        if (userString.indexOf("go") != -1) {
            return userString.substring(userString.indexOf(" ") + 1);
        }
        return "";
    }

    @Override
    public boolean execute() {
        Player p = g.getPlayer();
        boolean success;
        if(p.getCurrentRoom().getNeighborNames().indexOf(roomName.substring(roomName.indexOf(" ") + 1)) != -1) {
            p.setCurrentRoom(g.getNode(roomName));
            System.out.println(roomName.toString());
            success = true;
        }
        else {
            success = false;
        }
        return success;
    }
}
