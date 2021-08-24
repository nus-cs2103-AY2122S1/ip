import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Recieve {
    private String input;
    private static ArrayList<Task> inputs = new ArrayList<>();


    public Recieve() {
    }

    private static void readFile(String filePath) throws FileNotFoundException {
        inputs.clear();

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String readLine = s.nextLine();
            if (readLine.contains("[T]")) {
                Todo task = new Todo(readLine.substring(7));
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                inputs.add(task);
            }
            else if (readLine.contains("[D]")) {
                String[] spl = readLine.split("--");
                int length = spl[1].length();
                Deadline task = new Deadline(spl[0].substring(7), spl[1].substring(5, length - 1));
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                inputs.add(task);
            }
            else {
                String[] spl = readLine.split("--");
                int length = spl[1].length();
                Event task = new Event(spl[0].substring(7), spl[1].substring(5, length - 1));
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                inputs.add(task);
            }
        }
    }

    private static void writeToFile(String filePath, ArrayList<Task> arr) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task str: arr) {
            fw.write(str.toString() + System.lineSeparator());
        }
        fw.close();
    }

    public void run(String filePath) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                readFile(filePath);
                input = sc.nextLine();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                else if (input.startsWith("done ") || input.startsWith("done")) {
                    if (input.length() > 5 && Character.isDigit(input.charAt(5))) {
                        try {
                            int pos = Integer.parseInt(input.substring(5)) - 1;
                            if (pos < 0) {
                                throw new InvalidTaskIndexException("Task list index starts from 1!");
                            }

                            else if (pos < inputs.size()) {

                                try {
                                    inputs.get(pos).markAsDone();
                                    writeToFile(filePath, inputs);
                                    System.out.println("Nice! I've marked this task as done: ");
                                    System.out.println(inputs.get(pos).toString());
                                } catch (IOException e) {
                                    System.out.println("Something went wrong: " + e.getMessage());
                                }


                            }
                            else {
                                throw new InvalidTaskIndexException("There are only " + pos + " tasks!");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new InvalidTaskIndexException("A task index should only contain numbers!");
                        }

                    } else {
                        throw new InvalidTaskIndexException("Please specify the task index to be marked as done!");
                    }


                }
                else if (input.equals("list")) {
                    if (inputs.size() == 0) {
                        System.out.println("There is no task for now :)");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i < inputs.size() + 1; i++) {
                            System.out.println(i + "." + inputs.get(i - 1).toString());
                        }
                    }


                }
                else if (input.startsWith("delete ") || input.startsWith("delete")) {
                    if (input.length() > 7 && Character.isDigit(input.charAt(7))) {
                        try {
                            int pos = Integer.parseInt(input.substring(7)) - 1;

                            if (pos < 0) {
                                throw new InvalidTaskIndexException("Task list index starts from 1!");
                            }

                            else if (pos < inputs.size()) {
                                String temp = inputs.get(pos).toString();

                                inputs.remove(pos);
                                try {
                                    writeToFile(filePath, inputs);
                                } catch (IOException e) {
                                    System.out.println("Something went wrong: " + e.getMessage());
                                }

                                System.out.println("Noted. I've removed this task: ");
                                System.out.println(temp);

                                System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                            }
                            else {
                                throw new InvalidTaskIndexException("There are only " + pos + " tasks!");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new InvalidTaskIndexException("A task index should only contain numbers!");
                        }

                    }
                    else {
                        throw new InvalidTaskIndexException("Please specify the task index to be deleted!");
                    }

                }
                else {
                    if (input.startsWith("todo ") || input.equals("todo")) {
                        if (input.length() > 5 ) {
                            Todo task = new Todo(input.substring(5));


                            try {
                                inputs.add(task);
                                writeToFile(filePath, inputs);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(task.toString());
                                System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }


                        }
                        else {
                            throw new InvalidFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }

                    }
                    else if (input.startsWith("deadline ") || input.equals("deadline")) {
                        if (!input.contains(" /by ")) {
                            throw new InvalidFormatException("OOPS!! To add a Deadline, type -> deadline <Description> /by <deadline>!");
                        }
                        if (input.length() > 12 && input.contains("/by")) {
                            String[] spl = input.substring(9).split("/");
                            if (spl[0].length() < 2 || spl[1].length() < 4) {
                                System.out.println(spl[0].length());
                                System.out.println(spl[1].length());
                                throw new InvalidFormatException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
                            }
                            else {
                                Deadline task = new Deadline(spl[0], spl[1].substring(3));
                                inputs.add(task);

                                try {
                                    writeToFile(filePath, inputs);
                                } catch (IOException e) {
                                    System.out.println("Something went wrong: " + e.getMessage());
                                }

                                System.out.println("Got it. I've added this task:");
                                System.out.println(task.toString());
                                System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                            }
                        }
                        else {
                            throw new InvalidFormatException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
                        }

                    }
                    else if (input.startsWith("event ") || input.equals("event") ) {
                        if (!input.contains(" /at ")) {
                            throw new InvalidTaskIndexException("OOPS!! To add an Event, type -> event <Description> /at <details>!");
                        }
                        if (input.length() > 9 && input.contains("/at")) {
                            String[] spl = input.substring(6).split("/");
                            if (spl[0].length() < 2 || spl[1].length() < 4) {
                                throw new InvalidFormatException("☹ OOPS!!! The descriptions of an event cannot be empty.");
                            }
                            else {
                                Event task = new Event(spl[0], spl[1].substring(3));
                                inputs.add(task);

                                try {
                                    writeToFile(filePath, inputs);
                                } catch (IOException e) {
                                    System.out.println("Something went wrong: " + e.getMessage());
                                }

                                System.out.println("Got it. I've added this task:");
                                System.out.println(task.toString());
                                System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                            }
                        }
                        else {
                            throw new InvalidFormatException("☹ OOPS!!! The descriptions of an event cannot be empty.");
                        }

                    }
                    else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (InvalidTaskIndexException | InvalidFormatException | DukeException | FileNotFoundException ex) {
                System.out.println(ex.toString());
            }

        }
        sc.close();
    }



}
