public class EmptyCommand implements Command {
    @Override
    public void init(String userString) {

    }

    @Override
    public boolean execute() {
        return false;
    }
}
