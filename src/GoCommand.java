public class GoCommand implements Command {
    private Graph g;
    private String roomName;

    public GoCommand(Graph g){
        this.g = g;
    }

    @Override
    public void init(String userString) {
        this.roomName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        if (userString.contains("go")) {
            return userString.substring(userString.indexOf(" ") + 1);
        }
        return "";
    }

    @Override
    public boolean execute() {
        Player p = g.getPlayer();
        boolean success;
        if(p.getCurrentRoom().getNeighborNames().contains(roomName.substring(roomName.indexOf(" ") + 1))) {
            p.setCurrentRoom(g.getNode(roomName));
            success = true;
        }
        else {
            success = false;
        }
        return success;
    }
}
