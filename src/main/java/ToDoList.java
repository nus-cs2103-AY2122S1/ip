import java.util.ArrayList;

public class ToDoList {

    private final ArrayList<String> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        ToDoList l = new ToDoList();
        System.out.println(l.arrayList);
        l.add("Hello!");
        System.out.println(l.arrayList);
    }

    public String add(String str) {
        this.arrayList.add(str);
        return "Added: " + str;
    }

    public String list() {
        int i = 1;
        String returnString = "";
        for (String s : this.arrayList) {
            returnString += String.valueOf(i) + ". " + s + "\n";
            i++;
        }
        return returnString;
    }

}
