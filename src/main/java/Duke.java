import java.util.ArrayList;

public class Duke {
    String[] list = new String[100];
    int index = 0;
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
        if (str.equals("bye")) {
            System.out.println(divider + "\tBye. Hope to see you again soon!\n" + divider);
            return false;
        } else if (str.equals("list")) {
            displayList();
            return true;
        } else {
            addList((str));
            return true;
        }
    }

    private void addList(String str) {
        list[index++] = str;
        System.out.println(divider +"\tadded: "+ str+"\n" + divider);
    }

    private void displayList() {
        if (index == 0) {
            System.out.println(divider + "\tlist empty\n" + divider);
            return;
        }
        System.out.print(divider);
        for (int i = 0; i <index; i++) {
            System.out.println("\t"+(i+1) + ". " + list[i]);
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
