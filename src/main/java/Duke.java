import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException, FolderNotFoundException, IOException {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            File f = new File("../../../data/duke.txt");
            if (f.exists()){
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String toRead = s.nextLine();
                    String[] strSplit = toRead.split(" \\| ");
                    if (strSplit[0].equals("T")) {
                        ToDo toDo = new ToDo(strSplit[2]);
                        if (strSplit[1].equals("1")) {
                            toDo.complete();
                        }
                        list.add(toDo);
                    } else if (strSplit[0].equals("D")) {
                        Deadline deadline = new Deadline(strSplit[2], strSplit[3]);
                        if (strSplit[1].equals("1")) {
                            deadline.complete();
                        }
                        list.add(deadline);
                    } else if (strSplit[0].equals("E")) {
                        Event event = new Event(strSplit[2], strSplit[3]);
                        if (strSplit[1].equals("1")) {
                            event.complete();
                        }
                        list.add(event);
                    }
                }
                s.close();
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String command = input.split(" ")[0];
        while(!command.equals("bye")){
            try{ 
                if(command.equals("list")){
                    System.out.println("Here are the tasks in your list: ");
                    for(int i = 0; i < list.size(); i++){
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                } else if (command.equals("done")) {
                    int toComplete = Integer.parseInt(input.split(" ")[1]) - 1;
                    list.get(toComplete).complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("  %s", list.get(toComplete)));
                } else if (command.equals("delete")) {
                    int toDelete = Integer.parseInt(input.split(" ")[1]) - 1;
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(String.format("  %s", list.get(toDelete)));
                    list.remove(toDelete);
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                } else if(command.equals("todo")) {
                    String task = input.replaceFirst("todo ","");
                    if (task.equals("todo")){
                        throw new IllegalTaskException();
                    } else { 
                        ToDo toDo = new ToDo(task);
                        list.add(toDo);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(String.format("  %s", toDo.toString()));
                        System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                    }
                } else if (command.equals("deadline")) {
                    String[] taskDate = input.replaceFirst("deadline ", "").split("/by ");
                    String task = taskDate[0];
                    String date = taskDate[1];
                    String[] splitDateTime = date.split(" ");
                    String[] splitDate = splitDateTime[0].split("/");
                    LocalDate localDate;
                    if (splitDate[1].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
                    } else if (splitDate[0].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
                    } else { 
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
                    }
                    LocalTime localTime;
                    localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
                    Deadline deadline = new Deadline(task, localDate, localTime);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(String.format("  %s", deadline.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                } else if (command.equals("event")) {
                    String[] taskDate = input.replaceFirst("event ", "").split("/at ");
                    String task = taskDate[0];
                    String date = taskDate[1];
                    String[] splitDateTime = date.split(" ");
                    String[] splitDate = splitDateTime[0].split("/");
                    LocalDate localDate;
                    if (splitDate[1].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
                    } else if (splitDate[0].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
                    } else { 
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
                    }
                    LocalTime localTime;
                    localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
                    Event event = new Event(task, localDate, localTime);
                    list.add(event);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(String.format("  %s", event.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                } else {
                    throw new IllegalCommandException();
                }
            } catch (IllegalCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IllegalTaskException e){
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            input = sc.nextLine();
            command = input.split(" ")[0];
        }
        File toWrite = new File("../../../data");
        if (!toWrite.exists()) {
            toWrite.mkdir();
        }
        FileWriter fw = new FileWriter("../../../data/duke.txt");
        for (Task t: list) {
            fw.write(t.getToWrite());
            fw.write(System.getProperty("line.separator"));
        }
        System.out.println("Bye. Hope to see you again soon!");
        fw.close();
        sc.close();
    }
}
