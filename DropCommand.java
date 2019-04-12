public class DropCommand implements Command {
    Graph g;
    String itemName;

    public DropCommand(Graph g){
        this.g = g;
    }

    @Override
    public void init(String userString) {
        this.itemName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        if (userString.indexOf("drop") != -1) {
            return userString.substring(userString.indexOf(" ") + 1);
        }
        return "";
    }

    @Override
    public boolean execute() {
        Player p = g.getPlayer();
        boolean success = p.removeItem(itemName);
        return success;
    }
}
