import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task> ();

    public static void main(String[] args) {
        echo();
    }

    public static void echo() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();

        while (!s.equals("bye")) {
            try {
                if (s.equals("list")) {
                    int index = 1;

                    System.out.println("---------");
                    for (Task item : list) {
                        System.out.println(index + ". " + item.toString());
                        index++;
                    }
                    System.out.println("---------");
                } else if (s.startsWith("done")) {
                    int index = Integer.parseInt(s.split(" ")[1]) - 1;
                    list.get(index).setStatus(true);

                    System.out.println("---------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index));
                    System.out.println("---------");
                } else if (s.startsWith("delete")) {
                    int index = Integer.parseInt(s.replace("delete", "").trim()) - 1;

                    System.out.println("---------");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(list.get(index));
                    list.remove(index);
                    System.out.println("Now you have " + list.size() + " task in the list");
                    System.out.println("---------");

                } else {
                    // Adding tasks

                    if (s.startsWith("todo")) {
                        if (s.replace("todo", "").trim().equals("")) {
                            throw new DukeException("ToDos need to have a description");
                        }
                        list.add(new ToDo(s.replace("todo", "").trim()));
                    } else if (s.startsWith("deadline")) {
                        if (!s.contains("/by")) {
                            throw new DukeException("deadlines require a /by");
                        }
                        String[] data = s.replace("deadline ", "").split("/by");
                        list.add(new Deadline(data[0].trim(), data[1].trim()));
                    } else if (s.startsWith("event")) {
                        if (!s.contains("/at")) {
                            throw new DukeException("events require an /at");
                        }

                        String[] data = s.replace("event ", "").split("/at");
                        list.add(new Event(data[0].trim(), data[1].trim()));
                    } else {
                        throw new DukeException("Sorry I don't know what that means");
                    }

                    int len = list.size();
                    System.out.println("---------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(len - 1).toString());
                    System.out.println("Now you have " + len + " task in the list");
                    System.out.println("---------");
                }

                s = in.nextLine();
            } catch (DukeException err) {
                System.out.println("---------");
                System.out.println(err);
                System.out.println("---------");
                s = in.nextLine();
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
        return;
    }
}
