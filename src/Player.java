
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name, description;
    private HashMap<String, Item> items;
    Graph.Node currentRoom;

    public Player(String name, String description){
        this.name = name;
        this.description = description;
        items = new HashMap<>();
    }

    public boolean addItem(String item){
        for(String i: currentRoom.getItems().keySet()){
            if(i.equals(item)){
                Item newItem = currentRoom.getItems().get(item);
                if(newItem == null){
                    return false;
                }
                else {
                    items.put(newItem.getName(), newItem);
                    currentRoom.removeItem(item);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeItem(String name){
        for(String i: this.getItems().keySet()){
            if(i.equals(name)){
                Item newItem = this.getItems().get(name);
                if(newItem == null){
                    System.out.println("You do not have that item currently");
                    return false;
                }
                else{
                    Item output = items.get(name);
                    items.remove(name);
                    this.currentRoom.addItem(output);
                    return true;
                }
            }
        }
        return false;
    }

    public HashMap<String, Item> getItems(){
        return items;
    }
    public Graph.Node getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Graph.Node currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void displayInventory(){
        for(String name: this.getItems().keySet()){
            System.out.println(name + ". The description of this item is: " + this.getItems().get(name).getDescription());
        }
    }
}

