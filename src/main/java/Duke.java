import java.io.*;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;

public class Duke {
    public static Task[] tasks;
    public static Integer counter1 = 0;
    private static ArrayList<Task> list = new ArrayList<>();

    public static String niceLine() {
        return "______________________________________________________________\n";
    }

    private static void printFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    private static void appendToFile(String path, String textToAppend) throws IOException {
        FileWriter file = new FileWriter(path, true); // create a FileWriter in append mode
        file.write(textToAppend);
        file.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter file = new FileWriter(filePath);
        file.write(textToAdd);
        file.close();
    }

    private static void getContent(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            /*System.out.println(s);
            String[] st = s.split(" ");
            for (int i = 0; i < st.length; i++) {
                System.out.println(st[i]);
            }*/
            try {
                if(s.split("/")[0].equals("T")) {
                    ToDo todo = new ToDo(s.split("/")[2]);
                    list.add(todo);
                    if(s.split("/")[1].equals("1")) {
                        todo.setDone();
                    }
                } else if(s.split("/")[0].equals("D")) {
                    Deadline deadline = new Deadline(s.split("/", 4)[2], s.split("/", 4)[3]);
                    list.add(deadline);
                    if(s.split("/")[1].equals("1")) {
                        deadline.setDone();
                    }
                } else {
                    Event event = new Event(s.split("/")[2], s.split("/")[3]);
                    list.add(event);
                    if(s.split("/")[1].equals("1")) {
                        event.setDone();
                    }
                }
            } catch (DukeException1 e) {
                System.out.println("error!");
            }
        }
    }

    public static void main(String[] args) throws DukeException1{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.println(new File("../").getAbsolutePath());

        //File file = new File("/Users/neharikha/Desktop/ip/src/main/data/duke.txt");
        try {
            String path = "data" + File.separator + "duke.txt";
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            getContent("data/duke.txt");
            if(list.size() == 0) {
                System.out.println("There are no tasks");
            } else {
                System.out.println("im here!!");
                printFile("data/duke.txt");
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("error!");
        }

        //File file1 = new File("ip/data/duke.txt");
        //System.out.println(file1.exists());
        /*try {
            //System.out.println("im trying to print");
            printFile("../../data/duke.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }*/
        String message = niceLine() + "Hello! I'm Duke \n" + "What can I do for you?\n" + niceLine();
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //ArrayList<Task> list = new ArrayList<>();
        //File f = new File("../data/duke.txt");
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println((niceLine() + "\tHere are the tasks in your list:"));
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + Integer.toString(i + 1) + "." + list.get(i).toString());
                }
                System.out.println("\n" + niceLine());
                input = scanner.nextLine();
            } else if(input.split(" ")[0].equals("done")) {
                try {
                    Integer count = Integer.valueOf(input.split(" ")[1]);
                    list.get(count - 1).markAsDone();
                    System.out.println(niceLine() +  "\tNice! I've marked this task as done: \n \t \t" +
                            " [" + list.get(count - 1).getStatusIcon() + "] " + list.get(count - 1).description);
                    System.out.println("\n" + niceLine());
                    //Task task = list.get(count);
                    //BufferedWriter write;
                    //write = new BufferedWriter(new FileWriter("data/duke.txt", true));
                    //write.append("T/0/" + task);
                    //write.newLine();
                    PrintWriter writer = new PrintWriter("data/duke.txt");
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        if(i == count - 1) {
                            writer.println("T/1/" + task.getInfo());
                            //write.append("T/1/" + task.getInfo());
                            //write.newLine();
                            //appendToFile("data/duke.txt", "T/1/" + task.getInfo());
                        } else {
                            writer.println(task.getType() + "/" + task.getDone() + "/" + task.getInfo());
                            //write.append(task.getType() + "/" + task.getDone() + "/" + task.getInfo());
                            //write.newLine();
                            //writeToFile("data/duke.txt", task.getType() + "/" + task.getDone() + "/" + task.getInfo());
                        }
                    }
                    writer.close();
                    input = scanner.nextLine();
                } catch (IOException e) {
                    System.out.println("error occurred!");
                    input = scanner.nextLine();
                }

            } else if(input.split(" ")[0].equals("todo")) {
                try {
                    if(input.split(" ", 2).length == 1) {
                        ToDo todo = new ToDo(input.split(" ", 2)[0]);
                    } else {
                        ToDo todo = new ToDo (input.split(" ", 2)[1]);
                        list.add(todo);
                        String task = input.split(" ", 2)[1];
                        BufferedWriter write;
                        write = new BufferedWriter(new FileWriter("data/duke.txt", true));
                        write.append("T/0/" + task);
                        write.newLine();
                        write.close();
                        //appendToFile("data/duke.txt", "T/0/" + task);
                        System.out.println(niceLine() +  "\t" + "Got it. I've added this task: " + "\n\t\t" + todo.toString() +
                                "\n\tNow you have " + Integer.toString(list.size()) + " tasks in the list.");
                        System.out.println("\n" + niceLine());
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println(niceLine() +  "\tOOPS!!! The description of a todo cannot be empty.");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                } catch(IOException e) {
                    System.out.println("error occurred!");
                    input = scanner.nextLine();
                }

            } else if(input.split(" ")[0].equals("deadline")) {
                try {
                    if (input.split(" ", 2).length == 1) {
                        Deadline deadline = new Deadline(input.split(" ", 2)[0], "");
                    } else {
                        String description = input.split(" ", 2)[1].split(" /")[0];
                        String by = input.split("/by ")[1];
                        Deadline deadline = new Deadline(description, by);
                        list.add(deadline);
                        BufferedWriter write;
                        write = new BufferedWriter(new FileWriter("data/duke.txt", true));
                        write.append("D/0/" + description + "/" + by);
                        write.newLine();
                        write.close();
                        //appendToFile("data/duke.txt", "D/0/" + description + "/" + by);
                        System.out.println(niceLine() +  "\t" + "Got it. I've added this task: " + "\n\t\t" + deadline.toString() +
                                "\n\tNow you have " + Integer.toString(list.size()) + " tasks in the list.");
                        System.out.println("\n" + niceLine());
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println(niceLine() +  "\tOOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                } catch(IOException e) {
                    System.out.println("error occurred!");
                    input = scanner.nextLine();
                }
            } else if(input.equals("blah")) {
                /*try {
                    Task task = new Task(input);
                } catch(DukeException1 e) {
                    System.out.println(niceLine() +  "\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                }*/
                System.out.println(niceLine() +  "\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\n" + niceLine());
                input = scanner.nextLine();
            } else if(input.split(" ")[0].equals("delete")) {
                try {
                    Integer number = Integer.valueOf(input.split(" ")[1]);
                    System.out.println(niceLine() + "\tNoted. I've removed this task: \n\t\t" + list.get(number - 1).toString() +
                            "\n\tNow you have " + Integer.toString(list.size() - 1) + " tasks in the list.");
                    System.out.println("\n" + niceLine());
                    list.remove(number - 1);
                    PrintWriter writer = new PrintWriter("data/duke.txt");
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        //if (i != number) {
                            writer.println(task.getType() + "/" + task.getDone() + "/" + task.getInfo());
                            //writeToFile("data/duke.txt", task.getType() + " | " + task.getDone() + " | " + task.getInfo());
                        //}
                    }
                    writer.close();
                    input = scanner.nextLine();
                } catch (IOException e) {
                    System.out.println("error occurred!");
                    input = scanner.nextLine();
                }
            } else {
                try {
                    if (input.split(" ", 2).length == 1) {
                        Event event = new Event(input.split(" ", 2)[0], "");
                    } else {
                        String description = input.split(" ", 2)[1].split(" /")[0];
                        String at = input.split("/at ")[1];
                        Event event = new Event(description, at);
                        list.add(event);
                        BufferedWriter write = new BufferedWriter(new FileWriter("data/duke.txt", true));
                        write.append("E/0/" + description + "/" + at);
                        write.newLine();
                        write.close();
                        //appendToFile("data/duke.txt", "E/0/" + description + "/" + at);
                        System.out.println(niceLine() + "\tGot it. I've added this task: " + "\n\t\t" + event.toString() +
                                "\n\tNow you have " + Integer.toString(list.size()) + " tasks in the list.");
                        System.out.println("\n" + niceLine());
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println(niceLine() + "\tOOPS!!! The description of a event cannot be empty.");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                } catch(IOException e) {
                    System.out.println("error occurred!");
                    input = scanner.nextLine();
                }
            }
        }
        System.out.println(niceLine() + "\tBye. Hope to see you again soon!");
        System.out.println("\n" + niceLine());
    }
}
