import java.util.ArrayList;

public class MyList {
    private ArrayList<Task> myList = new ArrayList<Task>();

    public void addTask(Task t) {
        myList.add(t);
        System.out.println("Got it! I have added:");
        System.out.println(t.toString());
    }

    public void listAll() {
        int listLength = myList.size();
        if (listLength == 0) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Your list items:");
            for (int i = 0; i < listLength; i ++) {
                Task t = myList.get(i);
                String statusIcon = t.getStatusIcon();
                String typeIcon = t.getTypeIcon();
                System.out.printf("%d. %s \n", i + 1, t.toString());
                System.out.println("You now have " + listLength + " items in your list.");
            }
        }
    }

    public void markComplete(int index) {
        try {
            myList.get(index - 1).markComplete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index, please try again");
        }
    }
}