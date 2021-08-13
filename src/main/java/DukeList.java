import java.util.LinkedList;

public class DukeList {
    private LinkedList<String> list = new LinkedList<>();

    public String addToList(String input) {
        this.list.push(input);
        return "\tadded: " + input + "\n";
    }

    public String displayList() {
        StringBuilder output = new StringBuilder();
        int i = 1;
        for (String s: list) {
            String temp = "\t" + i + ". " + s + "\n";
            output.append(temp);
            i++;
        }
        return output.toString();
    }
}
