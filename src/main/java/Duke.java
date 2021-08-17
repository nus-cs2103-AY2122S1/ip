import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> list = new ArrayList<>();
    static final String divider = "\t____________________________________________________________\n";

    Duke() {
    }

    public void greet() {
        System.out.println(divider +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                divider);
    }

    public boolean process(String str) {
        Scanner scanner = new Scanner(str);
        String firstWord;
        if (!scanner.hasNext()) {
            //No input given
            return true;
        }
        firstWord = scanner.next();
        if (str.equals("bye")) {
            System.out.println(divider + "\tBye. Hope to see you again soon!\n" + divider);
            return false;
        } else if (str.equals("list")) {
            displayList();
        } else if (firstWord.equals("done")) {
            if(!scanner.hasNext()){
                System.out.println(divider + "\tTask number should be given.\n" + divider);
                return true;
            }
            //Exceptions
            int i = Integer.parseInt(scanner.next());
            if (i > 0 && i <=list.size()) {
                list.get(i-1).markDone();
            }
            else {
                System.out.println(divider + "\tNo such task found in list.\n" + divider);
            }
        }
        else if (firstWord.equals("todo")) {
            if(!scanner.hasNextLine()) {
                System.out.println(divider + "\tCannot be empty!\n" + divider);
            } else {
                addTodo(scanner.nextLine());
            }
        }
        else if (firstWord.equals("deadline")) {
            if(!scanner.hasNextLine()) {
                System.out.println(divider + "\tCannot be empty!\n" + divider);
            } else {
                addDeadline(scanner.nextLine());
            }
        }
        else if (firstWord.equals("event")) {
            if(!scanner.hasNextLine()) {
                System.out.println(divider + "\tCannot be empty!\n" + divider);
            } else {
                addEvent(scanner.nextLine());
            }
        }
        else {
            addList((str));
        }
        return true;
    }

    private void addEvent(String str) {
        if(str.contains("/at ")) {
            String arr[] = str.split("/at ",2);
            Task t = new Event(arr[0],arr[1]);
            addTask(t);
        } else {
            System.out.println(divider + "\tDate cannot be empty!\n" + divider);
        }
    }

    private void addDeadline(String str) {
        if(str.contains("/by ")) {
            String arr[] = str.split("/by ",2);
            Task t = new Deadline(arr[0],arr[1]);
            addTask(t);
        } else {
            System.out.println(divider + "\tDate cannot be empty!\n" + divider);
        }
    }


    private void addTodo(String str) {
        Task t = new Todo(str);
        addTask(t);
    }

    private void addTask(Task t) {
        list.add(t);
        System.out.println(divider +"\tGot it. I've added this task: ");
        System.out.println("\t"+ t);
        System.out.println("\tNow you have "+ list.size() + " task"+ (list.size()==1?" ":"s " ) +"in the list.");
        System.out.println(divider);
    }

    private void addList(String str) {
        Task t = new Task(str);
        list.add(t);
        System.out.println(divider +"\tadded: "+ t+"\n" + divider);
    }

    private void displayList() {
        if (list.size() == 0) {
            System.out.println(divider + "\tlist empty\n" + divider);
            return;
        }
        System.out.print(divider);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i <list.size(); i++) {
            System.out.println("\t"+(i+1) + ". " + list.get(i));
        }
        System.out.println(divider);
    }

}
