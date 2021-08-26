import java.io.IOException;
import java.util.Scanner;
import java.io.File;


public class Duke {
    private static TaskList list = new TaskList();
    private static String filePath = "data/duke.txt";

    public static void main(String[] args) {
        try {
            File data = new File(filePath);
            data.createNewFile();
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
//                System.out.println(s.nextLine());
                String[] array = s.nextLine().split("\\|");
                switch (array[0]) {
                    case "T": {
                        list.loadTask(new Todo(array[2], Boolean.parseBoolean(array[1])));
                        break;
                    }
                    case "D": {
                        list.addTask(new Deadline(array[2], array[3], Boolean.parseBoolean(array[1])));
                        break;
                    }
                    case "E": {
                        list.addTask(new Event(array[2], array[3], Boolean.parseBoolean(array[1])));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + logo);
        while(true) {
            System.out.println("____________________________________________________________");
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (command.equals("list")) {
                list.printList();
                continue;
            }
            String[] commandSplit = command.split("\\s", 2);

            try {
                switch (commandSplit[0]) {
                    case "done":
                        //Marks tasks as done
                        int index = Integer.valueOf(commandSplit[1]) - 1;
                        list.setDone(index);
                        list.saveTask(filePath);
                        break;

                    case "delete":
                        //Deletes tasks
                        int indexD = Integer.valueOf(commandSplit[1]) - 1;
                        list.deleteTask(indexD);
                        list.saveTask(filePath);
                        break;

                    case "todo":
                        //Adds a new Todo to the list
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of a todo cannot be empty");
                        }
                        Todo newT = new Todo(commandSplit[1], false);
                        list.addTask(newT);
                        list.saveTask(filePath);
                        break;
                    case "deadline":
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of a deadline cannot be empty");
                        }
                        Task newD = Deadline.parseCommand(commandSplit[1]);
                        list.addTask(newD);
                        list.saveTask(filePath);
                        break;
                    case "event":
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of an event cannot be empty");
                        }
                        Task newE = Event.parseCommand(commandSplit[1]);
                        list.addTask(newE);
                        list.saveTask(filePath);
                        break;

                    default:
                        throw new DukeException();


                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (TaskException e){
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Now you have " + list.getNumTask() + " task" + (list.getNumTask() > 1 ? "s " : " ") + "in the list");

//            if (commandSplit[0].equals("todo")) {
//                Todo newTask = new Todo(commandSplit[1]);
//                list.add(newTask);
//
//            }
//            Task newTask = new Task(command);

//            list.add(newTask);

        }
    }
}
