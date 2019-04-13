public class DropCommand implements Command {
    private Graph g;
    private String itemName;

    public DropCommand(Graph g){
        this.g = g;
    }

    @Override
    public void init(String userString) {
        this.itemName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        if (userString.contains("drop")) {
            return userString.substring(userString.indexOf(" ") + 1);
        }
        return "";
    }

    @Override
    public boolean execute() {
        Player p = g.getPlayer();
        return p.removeItem(itemName);
    }
}
