package duke;

import duke.commands.Command;

import java.util.Scanner;

public class Duke {

    private TaskList taskList;

    private Ui ui = new Ui();

    private DukeStorage storage;

    public Duke(String path) {
        this.storage = new DukeStorage(path);
        try {
            this.taskList = this.storage.readTasks();
        } catch (DukeException e) {
            ui.loadErrorMessage();
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.startMessage();
        boolean check = false;

        while(!check) {
            try {
                String strCommand = scanner.nextLine();
                ui.showLine();
                Command command = Parser.parse(strCommand);
                command.execute(taskList, ui, storage);
                check = command.isExit();
            } catch (DukeException e) {
                ui.errorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("tasklist.txt");
        duke.run();
    }

//    public static void main(String[] args) {
//        try {
//            Scanner sc = new Scanner(System.in);
//            boolean check = true;
//            DukeStorage storage = new DukeStorage("tasklist.txt");
//            ArrayList<Task> tasks = storage.readTasks();
//            int counter = tasks.size();
//            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
//
//            System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?");
//
//            while (check) {
//                try {
//                    String s = sc.nextLine();
//                    String[] strArray = s.split(" ");
//
//                    if (s.equals("bye")) {
//                        storage.writeTasks(tasks);
//                        check = false;
//                        System.out.println("Bye. Hope to see you again soon!");
//                    } else if (s.equals("list")) {
//                        for (int i = 0; i < counter; i++) {
//                            System.out.println((i + 1) + "." + tasks.get(i).toString());
//                        }
//                    } else if (strArray[0].equals("done")) {
//                        if (strArray.length == 1) {
//                            throw new DukeException("OOPS!!! You haven't specified which task you've done :-(");
//                        } else {
//                            int idx = Integer.parseInt(strArray[1]) - 1;
//                            tasks.get(idx).setDone();
//                            System.out.println("Nice! I've marked this task as done: \n" + "\t" + tasks.get(idx).toString());
//                        }
//                    } else if (strArray[0].equals("todo")) {
//                        String description = new String();
//                        for (int i = 1; i < strArray.length; i++) {
//                            description = description + strArray[i] + " ";
//                        }
//                        if (description.equals("")) {
//                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//                        } else {
//                            tasks.add(new Todo(description));
//                            System.out.println("Got it. I've added this task:\n" + "\t" + tasks.get(counter).toString());
//                            counter++;
//                            System.out.println("Now you have " + counter + " duke.tasks in your list.");
//                        }
//                    } else if (strArray[0].equals("deadline")) {
//                        String description = new String();
//                        String date = new String();
//                        for (int i = 1; i < strArray.length; i++) {
//                            if (strArray[i].equals("/by")) {
//                                i++;
//                                if (i + 2 < strArray.length) {
//                                    throw new DukeException("OOPS!!! Invalid date input.");
//                                }
//                                date = strArray[i] + " " + strArray[i + 1];
//                                break;
//                            }
//                            description = description + strArray[i] + " ";
//                        }
//
//                        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
//                        if (description.equals("")) {
//                            throw new DukeException("OOPS!!! duke.tasks.Deadline description cannot be empty :-(");
//                        } else if (date.equals("")) {
//                            throw new DukeException("OOPS!!! duke.tasks.Deadline date cannot be empty :-(");
//                        } else {
//                            tasks.add(new Deadline(description, dateTime.format(outputFormatter)));
//                            System.out.println("Got it. I've added this task:\n" + "\t" + tasks.get(counter).toString());
//                            counter++;
//                            System.out.println("Now you have " + counter + " duke.tasks in your list.");
//                        }
//                    } else if (strArray[0].equals("event")) {
//                        String description = new String();
//                        String date = new String();
//                        for (int i = 1; i < strArray.length; i++) {
//                            if (strArray[i].equals("/at")) {
//                                i++;
//                                if (i + 2 < strArray.length) {
//                                    throw new DukeException("OOPS!!! Invalid date input.");
//                                }
//                                date = strArray[i] + " " + strArray[i + 1];
//                                break;
//                            }
//                            description = description + strArray[i] + " ";
//                        }
//
//                        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
//                        if (description.equals("")) {
//                            throw new DukeException("OOPS!!! duke.tasks.Event description cannot be empty :-(");
//                        } else if (date.equals("")) {
//                            throw new DukeException("OOPS!!! duke.tasks.Event date cannot be empty :-(");
//                        } else {
//                            tasks.add(new Event(description, dateTime.format(outputFormatter)));
//                            System.out.println("Got it. I've added this task:\n" + "\t" + tasks.get(counter).toString());
//                            counter++;
//                            System.out.println("Now you have " + counter + " duke.tasks in your list.");
//                        }
//                    } else if (strArray[0].equals("delete")) {
//                        if (strArray.length != 2) {
//                            throw new DukeException("OOPS!!! You haven't specified which task you've done :-(");
//                        } else {
//                            int idx = Integer.parseInt(strArray[1]) - 1;
//                            System.out.println("Noted. I've removed this task:\n" + "\t" + tasks.get(idx).toString());
//                            tasks.remove(idx);
//                            counter--;
//                            System.out.println("Now you have " + counter + " duke.tasks in your list");
//                        }
//                    } else {
//                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                    continue;
//                }
//            }
//            sc.close();
//        } catch (DukeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}