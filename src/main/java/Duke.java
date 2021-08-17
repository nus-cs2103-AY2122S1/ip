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
        else {
            addList((str));
        }
        return true;
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
        for (int i = 0; i <list.size(); i++) {
            System.out.println("\t"+(i+1) + ". " + list.get(i));
        }
        System.out.println(divider);
    }

    public boolean echo(String echo) {
        if (echo.equals("bye")) {
            System.out.println(divider + "\tBye. Hope to see you again soon!\n" + divider);
            return false;
        } else {
            System.out.println(divider +"\t"+ echo+"\n" + divider);
            return true;
        }

    }
}
