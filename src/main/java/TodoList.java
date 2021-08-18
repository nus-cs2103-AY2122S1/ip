import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> list;

    public TodoList() {
        this.list = new ArrayList<String>();
    }

    public String getList() {
        ArrayList<String> currentList = this.list;
        StringBuilder output = new StringBuilder();
        int i = 1;
        for (String task : currentList) {
            output.append(i).append(". ").append(task).append("\n      ");
            i += 1;
        }

        return output.toString();
    }

    public void insertTask(String input) {
        list.add(input);
    }
}
