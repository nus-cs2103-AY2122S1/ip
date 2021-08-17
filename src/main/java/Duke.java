import java.util.*;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();

        System.out.println(greet());
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if (!input.equals("list")) {
                System.out.println(addList(input, lst));
            } else {
                System.out.println(printList(lst));
            }

            input = sc.nextLine();
        }

        System.out.println(exit());
    }

    public static String greet() {
        String output = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";

        return output;
    }

    public static String printList(ArrayList<String> lst) {
        StringBuilder s = new StringBuilder();
        s.append("    ____________________________________________________________\n");
        for (int i = 0; i < lst.size(); i++) {
            s.append(String.format("     %d. %s\n", i + 1, lst.get(i)));
        }
        s.append("    ____________________________________________________________\n");

        return s.toString();
    }

    public static String addList(String input, ArrayList<String> lst) {
        String output = "    ____________________________________________________________\n"
                + "     added:" + input +"\n"

                + "    ____________________________________________________________\n";
        lst.add(input);

        return output;
    }

    public static String exit() {
        String output = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        return output;
    }


}
