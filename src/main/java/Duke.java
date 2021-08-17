import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Task[] task = new Task[100];
        int taskNum = 0;
        String indentation = "       ";
        String Horizontal_line = "---------------------------------------------------------------";
        String greeting = "Hello! I'm Duke.\n" + indentation + "What can I do for you?\n";
        final String LIST = "list";
        final String BLAH = "blah";
        final String BYE = "bye";
        final String DONE = "done";
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
            switch(keyword[0]) {
                case LIST:
                    System.out.println(indentation + Horizontal_line);
                    for (int i = 0; i < taskNum; i++) {
                        String s = indentation;
                        String s2 = "";

                        if (task[i] instanceof Todo) {
                            s += (task[i].getIndex() + 1) + "." + " [T]";
                            s2 = task[i].getName();
                        } else if (task[i] instanceof Deadline) {
                            s += (task[i].getIndex() + 1) + "." + " [D]";
                            s2 = task[i].getName() +  " ( " + ((Deadline) task[i]).getTime() + " )";
                        } else if (task[i] instanceof Event){
                            s += (task[i].getIndex() + 1) + "." + " [E]";
                            s2 = task[i].getName() +  " ( " + ((Event) task[i]).getTime() + " )";
                        }
                        if (task[i].isDone() == false) {
                            s += "[ ]" + s2;
                            System.out.println(s);
                        } else {
                            s += "[X]" + s2;
                            System.out.println(s);
                        }
                    }
                    System.out.println(indentation + Horizontal_line);
                    break;
                case DONE:

                    try {
                        Integer num = Integer.valueOf(keyword[1]) - 1;
                        task[num].setDone(true);
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Nice! I've marked this task as done:");
                        System.out.println(indentation + "  [X] " + task[num].getName());
                        System.out.println(indentation + Horizontal_line);
                    } catch (NullPointerException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, you do not have this task");
                        System.out.println(indentation + Horizontal_line);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, please enter a task number bigger than 0");
                        System.out.println(indentation + Horizontal_line);
                    }

                    break;
                case BYE:
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
                                task[taskNum] = new Deadline(taskname_ddl, false, taskNum, tasktime_ddl);
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
                                task[taskNum] = new Todo(taskname_todo, false, taskNum);
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
                                task[taskNum] = new Event(taskname_event, false, taskNum, tasktime_event);
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
