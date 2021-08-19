import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine();
        Scanner inputScanner = new Scanner(input);
        String checkForKeyword = inputScanner.next();

        while(!input.equals("bye")){
            if(input.equals("list")){
                list.list();
            }else if(checkForKeyword.equals("done")){
                try {
                    int taskNumber = inputScanner.nextInt() - 1;
                    list.markDone(taskNumber);

                }catch(Exception e){
                    System.out.println("No such task number");
                }
            }
            else if(checkForKeyword.equals("todo")){
                String secondWord = inputScanner.nextLine();
                list.addTask(new Todo(secondWord));
            }
            else if(checkForKeyword.equals("deadline")){
                String[] contentAndDate = inputScanner.nextLine().split("/by",2);
                String content = contentAndDate[0];
                String date = contentAndDate[1];
                list.addTask(new Deadline(content, date));
            }
            else if(checkForKeyword.equals("event")){
                String[] contentAndDate = inputScanner.nextLine().split("/at",2);
                String content = contentAndDate[0];
                String date = contentAndDate[1];
                list.addTask(new Event(content, date));
            }
            else{
                    System.out.println("Sorry i do not recognize this input\n" +
                            "Create a task with the following keywords: todo, event and deadline");
            }
            input = scanner.nextLine();
            inputScanner = new Scanner(input);
            checkForKeyword = inputScanner.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
