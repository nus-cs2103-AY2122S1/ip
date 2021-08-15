import java.util.ArrayList;

public class MyList {
    private ArrayList<Task> myList = new ArrayList<Task>();

    public void addTask(Task t) {
        myList.add(t);
        System.out.println("Got it! I have added:");
        System.out.printf("%s %s%s \n",t.getTypeIcon(), t.getStatusIcon(), t.getTaskName());
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
                System.out.printf("%d. %s %s%s \n", i + 1, typeIcon, statusIcon, t.getTaskName());
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