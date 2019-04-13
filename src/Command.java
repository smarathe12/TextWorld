public interface Command {
    void init(String userString);
    boolean execute();
}
