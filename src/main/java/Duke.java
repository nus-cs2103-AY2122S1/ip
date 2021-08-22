import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        int count = 0;
        boolean exit = false;
        ArrayList<Task> arrList = new ArrayList<>();
        String line = "____________________________________________________________\n";
        String logo = line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line;
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);
        String filePath = "data/duke.txt";
        Save save = new Save(filePath);
        arrList = save.readFile(filePath);

        while(sc.hasNext()){
            String command = sc.next();
            switch(command){
                case "bye":
                    System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                    exit = true;
                    break;
                case "list":
                    String list = "";
                    int listNum = 1;
                    for(Task x:arrList){
                        list += listNum + "." + x +"\n";
                        listNum++;
                    }
                    System.out.println(line + list + line);
                    break;
                case "done":
                    int doneNum = sc.nextInt() - 1;
                    try{
                        arrList.get(doneNum).markAsDone();
                        System.out.println(line + "Nice! I've marked this task as done:\n" + arrList.get(doneNum));
                        break;
                    }catch (Exception e){
                        System.out.println("No such task");
                        break;
                    }
                case "delete":
                    int delNum = sc.nextInt()-1;
                    Task delete = arrList.get(delNum);
                    count--;
                    System.out.println(line + "Noted. I've removed this task:\n" + delete +
                            "\nNow you have " + count + " tasks in the list.\n" + line);
                    arrList.remove(delNum);
                    break;
                case "todo":
                    try {
                        Task todo = new Todo(sc.nextLine().trim(), count);
                        arrList.add(todo);
                        count++;
                        System.out.println(line + "Got it. I've added this task:\n" + todo +
                                "\nNow you have " + count + " tasks in the list.\n" + line);
                        break;
                    } catch (Exception e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The description of a todo cannot be empty.\n" + line);
                        break;
                    }
                case "deadline":
                    try {
                        String[] deadlineArr = sc.nextLine().split("/by");
                        LocalDate d1 = LocalDate.parse(deadlineArr[1].trim());
                        Task deadline = new Deadline(deadlineArr[0].trim(),
                                d1.format(DateTimeFormatter.ofPattern("MMM dd YYYY")), count);
                        arrList.add(deadline);
                        count++;
                        System.out.println(line + "Got it. I've added this task:\n" + deadline +
                                "\nNow you have " + count + " tasks in the list.\n" + line);
                        break;
                    } catch (DateTimeParseException e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The date must be in the format YYYY-MM-DD\n" + line);
                    } catch (Exception e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The description of a deadline cannot be empty.\n" + line);
                        break;
                    }
                case "event":
                    try {
                        String[] eventArr = sc.nextLine().split("/at");
                        LocalDate d2 = LocalDate.parse(eventArr[1].trim());
                        Task event = new Event(eventArr[0].trim(),
                                d2.format(DateTimeFormatter.ofPattern("MMM dd YYYY")), count);
                        arrList.add(event);
                        count++;
                        System.out.println(line + "Got it. I've added this task:\n" + event +
                                "\nNow you have " + count + " tasks in the list.\n" + line);
                        break;
                    } catch (DateTimeParseException e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The date must be in the format YYYY-MM-DD\n" + line);
                    } catch (Exception e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The description of a event cannot be empty.\n" + line);
                        break;
                    }
                default:
                    System.out.println("\n" + line +
                            "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(.\n" + line);
            }

            save.writeToFile(filePath,arrList);

            if(exit){
                break;
            }
        }
    }
}
