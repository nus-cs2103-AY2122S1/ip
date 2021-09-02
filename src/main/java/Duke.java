import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws IOException {
        String startUp = " Hello! I'm Duke\n"
                + " What can I do for you?";

        ArrayList<Task> list = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);

        File f = new File("src/main/java/list.txt");

        try {
            if (!f.exists()) {
                f.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] taskArr = str.split(" \\| ");
            System.out.println(taskArr[0]);
            System.out.println(taskArr[1]);
            System.out.println(taskArr[2]);

            switch (taskArr[0]) {
                case "T":
                    Todo todo = new Todo(taskArr[2]);
                    list.add(todo);
                    if (taskArr[1].equals("1")) {
                        todo.setDone();
                    }
                    break;
                case "E":
                    Event event = new Event(taskArr[2], taskArr[3]);
                    list.add(event);
                    if (taskArr[1].equals("1")) {
                        event.setDone();
                    }
                    break;
                case "D":
                    Deadline deadline = new Deadline(taskArr[2], taskArr[3]);
                    list.add(deadline);
                    if (taskArr[1].equals("1")) {
                        deadline.setDone();
                    }
                    break;
                default:
                    break;
            }
        }

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
                    updateText(list, f);
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
                    updateText(list, f);
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
                    updateText(list, f);
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
                    updateText(list, f);
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
                    updateText(list, f);
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

    public static void updateText(ArrayList<Task> list, File file) throws IOException{
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        try {

            for (Task task : list) {
                char taskType = task.toString().charAt(2);
                char isDone = '0';
                if (task.isDone) {
                    isDone = '1';
                }

                switch (taskType) {
                    case 'T':
                        bw.write(taskType + " | " + isDone + " | " + task.description);
                        bw.newLine();
                        break;
                    case 'E':
                        Event event = (Event) task;
                        bw.write(taskType + " | " + isDone + " | " + task.description + " | " + event.at);
                        bw.newLine();
                        break;
                    case 'D':
                        Deadline deadline = (Deadline) task;
                        bw.write(taskType + " | " + isDone + " | " + task.description + " | " + deadline.by);
                        bw.newLine();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }

            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        try {
            bw.close();
        } catch (Exception e) {
            msgTemplate(e.getMessage());
        }
    }
}
