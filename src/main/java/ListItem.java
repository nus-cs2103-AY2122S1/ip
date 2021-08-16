public class ListItem {
    private String name;

    public ListItem(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("added: %s", this.name);
    }
}
