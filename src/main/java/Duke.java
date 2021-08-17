import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> task = new ArrayList<>();
        int taskNum = 0;
        String indentation = "       ";
        String Horizontal_line = "---------------------------------------------------------------";
        String greeting = "Hello! I'm Duke.\n" + indentation + "What can I do for you?\n";
        final String LIST = "list";
        final String BYE = "bye";
        final String DONE = "done";
        final String DELETE = "delete";
        //Enumeration
        enum Command {
            CLIST, CDONE, CBYE, CDELETE, COTHER
        };

        boolean isEnd = false;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(indentation + Horizontal_line);
        System.out.println(indentation + greeting);
        System.out.println(indentation + Horizontal_line);
        Scanner scanner = new Scanner(System.in);


        while (!isEnd) {
            String keywords = scanner.nextLine();
            String[] keyword = keywords.split(" ");
            Command command;
            if (keyword[0].equals("list")) {
                command = Command.CLIST;
            } else if (keyword[0].equals("bye")) {
                command = Command.CBYE;
            } else if (keyword[0].equals("done")) {
                command = Command.CDONE;
            } else if (keyword[0].equals("delete")) {
                command = Command.CDELETE;
            } else {
                command = Command.COTHER;
            }

            switch(command) {
                case CLIST:
                    System.out.println(indentation + Horizontal_line);
                    try {

                        for (int i = 0; i < task.size(); i++) {
                            String s = indentation;
                            String s2 = "";

                            if (task.get(i) instanceof Todo) {
                                s += (i + 1) + "." + " [T]";
                                s2 = task.get(i).getName();
                            } else if (task.get(i) instanceof Deadline) {
                                s += (i + 1) + "." + " [D]";
                                s2 = task.get(i).getName() +  " ( " + ((Deadline) task.get(i)).getTime() + " )";
                            } else if (task.get(i) instanceof Event){
                                s += (i + 1) + "." + " [E]";
                                s2 = task.get(i).getName() +  " ( " + ((Event) task.get(i)).getTime() + " )";
                            }
                            if (task.get(i).isDone() == false) {
                                s += "[ ]" + s2;
                                System.out.println(s);
                            } else {
                                s += "[X]" + s2;
                                System.out.println(s);
                            }
                        }

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(indentation + "Currently you have no task to do");
                    }
                    System.out.println(indentation + Horizontal_line);

                    break;
                case CDONE:

                    try {
                        Integer num = Integer.valueOf(keyword[1]) - 1;
                        task.get(num).setDone(true);
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Nice! I've marked this task as done:");
                        String s = indentation;
                        String s2 = "";
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Noted. I've removed this task:");
                        if (task.get(num) instanceof Todo) {
                            s += (task.get(num).getIndex() + 1) + "." + " [T]";
                            s2 = task.get(num).getName();
                        } else if (task.get(num) instanceof Deadline) {
                            s += (task.get(num).getIndex() + 1) + "." + " [D]";
                            s2 = task.get(num).getName() +  " ( " + ((Deadline) task.get(num)).getTime() + " )";
                        } else if (task.get(num) instanceof Event){
                            s += (task.get(num).getIndex() + 1) + "." + " [E]";
                            s2 = task.get(num).getName() +  " ( " + ((Event) task.get(num)).getTime() + " )";
                        }

                        s += "[X]" + s2;
                        System.out.println(s);

                        System.out.println(indentation + Horizontal_line);
                    } catch (NullPointerException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, you do not have this task");
                        System.out.println(indentation + Horizontal_line);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, please enter a task number bigger than 0");
                        System.out.println(indentation + Horizontal_line);
                    }

                    break;
                case CDELETE:
                    try {
                        Integer num = Integer.valueOf(keyword[1]) - 1;

                        String s = indentation + "     ";
                        String s2 = "";

                        if (task.get(num) instanceof Todo) {
                            s += (task.get(num).getIndex() + 1) + "." + " [T]";
                            s2 = task.get(num).getName();
                        } else if (task.get(num) instanceof Deadline) {
                            s += (task.get(num).getIndex() + 1) + "." + " [D]";
                            s2 = task.get(num).getName() +  " ( " + ((Deadline) task.get(num)).getTime() + " )";
                        } else if (task.get(num) instanceof Event){
                            s += (task.get(num).getIndex() + 1) + "." + " [E]";
                            s2 = task.get(num).getName() +  " ( " + ((Event) task.get(num)).getTime() + " )";
                        }
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Noted. I've removed this task:");
                        if (task.get(num).isDone() == false) {
                            s += "[ ]" + s2;
                            System.out.println(s);
                        } else {
                            s += "[X]" + s2;
                            System.out.println(s);
                        }
                        task.remove(num.intValue());
                        System.out.format(indentation + "Now you have %d tasks in the list.%n", task.size());
                        System.out.println(indentation + Horizontal_line);
                    } catch (NullPointerException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, you do not have this task");
                        System.out.println(indentation + Horizontal_line);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, you do not have this task");
                        System.out.println(indentation + Horizontal_line);
                    }
                    break;
                case CBYE:
                    System.out.println(indentation + Horizontal_line);
                    System.out.println(indentation + "Bye. Hope to see you again soon!");
                    System.out.println(indentation + Horizontal_line);
                    scanner.close();
                    isEnd = true;
                    break;
                default:
                    try {
                        switch (keyword[0]) {

                            case "deadline":
                                if (keyword.length == 1) {
                                    System.out.println(indentation + Horizontal_line);
                                    System.out.println(indentation + "OOPS!!! The description of a deadline cannot be empty.");
                                    System.out.println(indentation + Horizontal_line);
                                    break;
                                }
                                String taskname_ddl = "";
                                String tasktime_ddl = "";
                                boolean timepart_ddl = false;
                                for (int i = 1; i < keyword.length; i++) {
                                    if (keyword[i].startsWith("/")) {
                                        timepart_ddl = true;
                                        tasktime_ddl = keyword[i].substring(1) + ": ";
                                    } else if (timepart_ddl) {
                                        tasktime_ddl += " " + keyword[i];
                                    } else {
                                        taskname_ddl += " " + keyword[i];
                                    }
                                }
                                if (tasktime_ddl.equals("")) {
                                    System.out.println(indentation + Horizontal_line);
                                    System.out.println(indentation + "OOPS!!! The time of a deadline cannot be empty.");
                                    System.out.println(indentation + Horizontal_line);
                                    break;
                                }
                                task.add(new Deadline(taskname_ddl, false, taskNum, tasktime_ddl));
                                taskNum++;
                                System.out.println(indentation + Horizontal_line);
                                System.out.println(indentation + "Got it. I've added this task:");
                                System.out.println(indentation + "   [D][ ] "+ taskname_ddl + " ( " + tasktime_ddl + " )");
                                System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
                                System.out.println(indentation + Horizontal_line);
                                break;
                            case "todo":
                                if (keyword.length == 1) {
                                    System.out.println(indentation + Horizontal_line);
                                    System.out.println(indentation + "OOPS!!! The description of a todo cannot be empty.");
                                    System.out.println(indentation + Horizontal_line);
                                    break;
                                }
                                String taskname_todo = "";
                                for (int i = 1; i < keyword.length; i++) {
                                    taskname_todo += " " + keyword[i];
                                }
                                task.add(new Todo(taskname_todo, false, taskNum));
                                taskNum++;
                                System.out.println(indentation + Horizontal_line);
                                System.out.println(indentation + "Got it. I've added this task:");
                                System.out.println(indentation + "   [T][ ] "+ taskname_todo);
                                System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
                                System.out.println(indentation + Horizontal_line);
                                break;
                            case "event":
                                if (keyword.length == 1) {
                                    System.out.println(indentation + Horizontal_line);
                                    System.out.println(indentation + "OOPS!!! The description of a event cannot be empty.");
                                    System.out.println(indentation + Horizontal_line);
                                    break;
                                }

                                String taskname_event = "";
                                String tasktime_event = "";
                                boolean timepart_event = false;
                                for (int i = 1; i < keyword.length; i++) {
                                    if (keyword[i].startsWith("/")) {
                                        timepart_event = true;
                                        tasktime_event = keyword[i].substring(1) + ": ";
                                    } else if (timepart_event) {
                                        tasktime_event += " " + keyword[i];
                                    } else {
                                        taskname_event += " " + keyword[i];
                                    }
                                }
                                if (tasktime_event.equals("")) {
                                    System.out.println(indentation + Horizontal_line);
                                    System.out.println(indentation + "OOPS!!! The time of a event cannot be empty.");
                                    System.out.println(indentation + Horizontal_line);
                                    break;
                                }
                                task.add(new Event(taskname_event, false, taskNum, tasktime_event));
                                taskNum++;
                                System.out.println(indentation + Horizontal_line);
                                System.out.println(indentation + "Got it. I've added this task:");
                                System.out.println(indentation + "   [E][ ] "+ taskname_event + " ( " + tasktime_event + " )");
                                System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
                                System.out.println(indentation + Horizontal_line);
                                break;
                            default:
                                System.out.println(indentation + Horizontal_line);
                                System.out.println(indentation + "OOPS!!! I'm sorry, but I don't know what that means :-(");
                                System.out.println(indentation + Horizontal_line);
                                break;

                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "please follow the format of adding event");
                        System.out.println(indentation + Horizontal_line);
                    }

            }
        }


    }
}
