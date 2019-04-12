public class LookCommand implements Command {
    Graph g;

    public LookCommand(Graph g){
        this.g = g;
    }

    @Override
    public void init(String userString) {
    }

    @Override
    public boolean execute() {
        Player p = g.getPlayer();
        if(p.getCurrentRoom().getNeighborNames().equals("")){
            System.out.println("There are no neighbors to your current room");
        }
        else{
            System.out.println("The neighbors to your current room are: " + p.getCurrentRoom().getNeighborNames());
        }
        System.out.println("Your current room, the " + p.getCurrentRoom().getName() + ", is " + p.getCurrentRoom().getDescription());
        if(p.getCurrentRoom().getItems().size() > 1){
            System.out.println("The items in your current room available for pick up are: ");
            p.getCurrentRoom().displayItems();
        }
        else if(p.getCurrentRoom().getItems().size() == 1){
            System.out.println("The item in your current room available to pick up is: ");
            p.getCurrentRoom().displayItems();
        }
        else {
            System.out.println("There are no items available for pick up in your current room");
        }
        System.out.println("There are " + p.getCurrentRoom().getNumberOfChickens() + " chickens in your current room");
        System.out.println("There are " + p.getCurrentRoom().getNumberOfWumpuses() + " wumpuses in your current room");
        System.out.println("There are " + p.getCurrentRoom().getNumberOfPopstars() + " popstars in your current room");
        return true;
    }
}
