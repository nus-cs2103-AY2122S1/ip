import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//rmb to document ur code!!
public class Duke {
    ArrayList<Task> list;

    Duke() {
        list = new ArrayList<>();
    }

    //print a list
    public void printList() {
        System.out.println("Here are the tasks on your list: ");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i-1));
        }
    }

    //mark a task as done
    public void markAsDone(String str) throws OutOfBoundException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > list.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I have marked this task as done!");
            System.out.println(task);
        }
    }

    //method to add a task to a list

    public void addTask(String str) throws EmptyDescriptionException, InvalidTaskException, InvalidDeadlineException {
        //first check if the task only contain 1 word
        if (str.split(" ").length == 1) {
            //check if task if valid
            if (str.startsWith("todo") || str.startsWith("deadline")) {
                throw new EmptyDescriptionException();
            }
            else if (str.startsWith("event")) {
                throw new EmptyDescriptionException();
            }
            //check for invalid task
            else {
                throw new InvalidTaskException();
            }
        }

        //task that contain more than 1 word.
        //check for invalid task. otherwise, add the correct task to the list.
        else {
            //check for invalid task
            if (!str.startsWith("todo") && !str.startsWith("deadline") && !str.startsWith("event")) {
                throw new InvalidTaskException();
            }

            //add correct task to the list
            else {
                if (str.startsWith("todo")) {
                    list.add(new ToDos(str.substring(5)));
                } else if (str.startsWith("deadline")) {
                    String[] message = str.split("/by ");
                    if (message.length == 1) {
                        throw new InvalidTaskException();
                    } else {
                        list.add(new Deadline(message[0].substring(9), message[1]));
                    }
                } else {
                    String[] message = str.split("/at ");
                    if (message.length == 1) {
                        throw new InvalidTaskException();
                    } else {
                        list.add(new Events(message[0].substring(6), message[1]));
                    }
                }

                System.out.println("Got it. I've added this task.");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size()
                        + (list.size() == 1 ? " task in the list" : " tasks in the list."));
            }
        }
    }

    //delete a task
    public void deleteTask(String str) throws OutOfBoundException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > list.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task);
            System.out.println("Now you have " + list.size()
                    + (list.size() == 1 ? " task in the list" : " tasks in the list."));
        }
    }

    public void updateFile() throws IOException {
        File file = new File(".\\src\\main\\level-7.txt");
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            File newFile = new File(".\\src\\main\\level-7.txt");
            newFile.createNewFile();
            FileWriter writer = new FileWriter(file);

            for (Task task : list) {
                writer.write(task.writeTask() + "\n");
            }
            writer.close();
        }
    }

    public void load() throws IOException, InvalidTaskException, InvalidDeadlineException {
        File file = new File(".\\src\\main\\level-7.txt");
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] stuff = str.split(" \\| ");
                Task task;
                switch (stuff[0]) {
                    case "T":
                        task = new ToDos(stuff[2]);
                        break;
                    case "D":
                        task = new Deadline(stuff[2], stuff[3]);
                        break;
                    case "E":
                        task = new Events(stuff[2], stuff[3]);
                        break;
                    default:
                        throw new InvalidTaskException();
                }
                if (stuff[1].equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! This is Duke :)" + "\n" + "What can I do for you?");
        //load file
        try {
            duke.load();
            System.out.println("Duke is loading!");
        } catch (IOException e) {
            System.out.println("e.getMessage()");
        } catch (InvalidTaskException e) {
            System.out.println(e);
        }

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            //print out list of task
            if (input.equals("list")) {
                duke.printList();
            }

            //mark a task as done
            else if (input.length() >= 4 && input.startsWith("done")) {
                try {
                    duke.markAsDone(input);
                } catch (OutOfBoundException e) {
                    System.out.println(e);
                } catch (NumberFormatException e) {
                    System.out.println("Task does not exist. Please send a correct number ><");
                }
            }

            else if (input.startsWith("delete")) {
                try {
                    duke.deleteTask(input);
                } catch (NumberFormatException e) {
                    System.out.println("Task does not exist. Please send a correct number ><");
                } catch (OutOfBoundException e) {
                    System.out.println(e);
                }
            }

            //add new task
            else {
                try {
                    duke.addTask(input);
                } catch (InvalidTaskException | InvalidDeadlineException e) {
                    System.out.println(e);
                } catch (EmptyDescriptionException e) {
                    System.out.println("☹ OOPS!!! The description of a" + input + "cannot be empty.");
                }
            }

            //update the file here
            try {
                duke.updateFile();
            } catch (IOException e) {
                System.out.println("hehe");
            }

            input = sc.nextLine();
        }

        System.out.println("Bye! See you again soon!!");
    }
}
