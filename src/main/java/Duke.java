import java.util.Scanner;

public class Duke {
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";
    private static String END = "bye";
    private static String LIST = "list";
    private static String DONE = "done";
    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        Tasks myTasks = new Tasks();
        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals(END)) {
                myPrint("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals(LIST)) {
                myTasks.printTasks();
            } else if (next.length() > 3 && next.substring(0, 4).equals(DONE)) {
                if (next.length() > 5) {
                    String emp = next.substring(4, 5);
                    String index = next.substring(5);
                    int position = Integer.parseInt(index);
                    myTasks.complete(position);
                }

            } else {
                myTasks.addTask(next);
            }
            }
    }

    private static void greeting() {
        String g = "Hello! I'm Duke";
        String g2 = "What can I do for you?";
        myPrint(g + "\n" + ind2+ g2 );
    }

    private static void myPrint(String s) {
        System.out.println(div + "\n" + ind2 + s + "\n" + div);
    }


}
