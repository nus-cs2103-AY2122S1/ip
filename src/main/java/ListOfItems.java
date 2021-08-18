import java.util.ArrayList;

public class ListOfItems {
    private ArrayList<String> list;

    public ListOfItems() {
       this.list = new ArrayList<>();
    }

    public void addToList(String input) {
        list.add(input);
    }

    @Override
    public String toString() {
        String l = "";
        for (String s : list) {
            l += (list.indexOf(s) + 1) + ". " + s + "\n";
        }
        return l;
    }
}
