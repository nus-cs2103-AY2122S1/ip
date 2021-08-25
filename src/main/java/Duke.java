import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {

    public static void main(String[] args) {
        String startUp = " Hello! I'm Duke\n"
                + " What can I do for you?";
        ArrayList<Task> list = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);

        msgTemplate(startUp);

        while(true) {
            String input = myObj.nextLine();
            if (input.startsWith("todo")) {
                try {
                    if (input.charAt(4) != ' ') throw new IllegalArgumentException();
                    if (input.equals("todo ")) throw new StringIndexOutOfBoundsException();
                    String item = input.substring(5);
                    list.add(new Todo(item));
                    msgTemplate(" Got it. I've added this task:\n" + "  " + list.get(list.size() - 1) + System.lineSeparator() + " Now you have " + list.size() + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    msgTemplate(" ☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (IllegalArgumentException e) {
                    msgTemplate(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.charAt(8) != ' ') throw new IllegalArgumentException();
                    if (!input.contains("/by")) throw new DukeException(" ☹ OOPS!!! Please specify a date.");
                    String item = input.substring(9, input.indexOf("/by ") - 1);
                    String date = input.substring(input.indexOf("/by ") + 4);
                    list.add(new Deadline(item, date));
                    msgTemplate(" Got it. I've added this task:\n" + "  " + list.get(list.size() - 1) + System.lineSeparator() + " Now you have " + list.size() + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    msgTemplate(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                } catch (IllegalArgumentException e) {
                    msgTemplate(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    msgTemplate(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    if (input.charAt(5) != ' ') throw new IllegalArgumentException();
                    if (!input.contains("/at")) throw new DukeException(" ☹ OOPS!!! Please specify a date.");
                    String item = input.substring(6, input.indexOf("/at ") - 1);
                    String date = input.substring(input.indexOf("/at ") + 4);
                    list.add(new Event(item, date));
                    msgTemplate(" Got it. I've added this task:\n" + "  " + list.get(list.size() - 1) + System.lineSeparator() + " Now you have " + list.size() + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    msgTemplate(" ☹ OOPS!!! The description of a event cannot be empty.");
                } catch (IllegalArgumentException e) {
                    msgTemplate(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    msgTemplate(e.getMessage());
                }
            } else if (input.startsWith("done")) {
                try {
                    if (input.equals("done")) throw new NumberFormatException();
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    list.get(index).setDone();
                    msgTemplate(" Nice! I've marked this task as done:\n" + "  " + list.get(index));
                } catch (IndexOutOfBoundsException e) {
                    msgTemplate(" ☹ OOPS!!! This entry does not exist!");
                } catch (NumberFormatException e) {
                    msgTemplate(" ☹ OOPS!!! Please enter a number after done!");
                }
            } else if (input.startsWith("delete")) {
                try {
                    if (input.equals("delete")) throw new NumberFormatException();
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task task = list.get(index);
                    list.remove(index);
                    msgTemplate(" Noted. I've removed this task:\n" + "  " + task + System.lineSeparator() + " Now you have " + list.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    msgTemplate(" ☹ OOPS!!! This entry does not exist!");
                } catch (NumberFormatException e) {
                    msgTemplate(" ☹ OOPS!!! Please enter a number after delete!");
                }
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + list.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (input.equals("bye")) {
                msgTemplate(" Bye. Hope to see you again soon!");
                break;
            } else {
                msgTemplate(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void msgTemplate(String msg){
        String topLine = "____________________________________________________________\n";
        String bottomLine = "____________________________________________________________";
        System.out.println(topLine + msg + System.lineSeparator() + bottomLine);
    }
}
