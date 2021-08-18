import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
//    private static Task[] myList = new Task[100];
    private static ArrayList<Task> myList = new ArrayList<Task>();
//    private static int index = 0;
    private final static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("hi sis, type out your task right away! :D");
        while (sc.hasNextLine()) {
            try {
                String text = sc.nextLine();
                if (text.equals("q")) {
                    System.out.println("ciao!");
                    break;
                } else if (text.equals("ls")) {
//                    printList(index);
                    printList();
                } else if (text.contains("done")) {
                    markAsDone(text);
                } else if (text.contains("delete")) {
                    deleteTask(text);
                } else {
                    addTask(text);
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private static void deleteTask(String text) throws DukeException {
        text = text.trim();
        try {
            int i = Integer.parseInt(text.split(" ")[1]) - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println(myList.get(i));
            myList.remove(i);
            System.out.printf("Now you have %d task(s) in the list.\n", myList.size());
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Indicate which task you want to delete.");
        }
    }

    private static void addTask(String text) throws DukeException {
        text = text.trim();
        try {
            if (text.contains("todo")) {
//                myList[i] = new Todo(text.split(" ", 2)[1]);
                myList.add(new Todo(text.split(" ", 2)[1]));
            } else if (text.contains("deadline")) {
                try {
                    String[] task = text.split(" ", 2)[1].split(" /by ", 2);
//                    myList[i] = new Deadline(task[0], task[1]);
                    myList.add(new Deadline(task[0], task[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("use the format --deadline  xx /by xx--");
                }
            } else if (text.contains("event")) {
                try {
                    String[] task = text.split(" ", 2)[1].split(" /at ", 2);
//                    myList[i] = new Event(task[0], task[1]);
                    myList.add(new Event(task[0], task[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("use the format --event xx /at xx--");
                }
            } else {
                throw new DukeException("Please use the keyword --todo, deadline or event--");
            }
//            System.out.println("added: " + myList[i]);
            System.out.println("added: " + myList.get(myList.size() - 1));
//            index++;
            System.out.printf("Now you have %d task(s) in the list.\n", myList.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a task cannot be empty.");
        }
    }

    private static void printList() {
//        if (len == 0) { System.out.println("The list is empty!"); }
        int len = myList.size();
        if (len == 0) { System.out.println("The list is empty!"); }
        for (int i = 0; i < len; i++) {
//            Task curr = myList[i];
            Task curr = (Task) myList.get(i);
            System.out.printf("%s. %s\n", i + 1, curr);
        }
    }

    private static void markAsDone(String text) throws DukeException {
        try {
            int i = Integer.parseInt(text.split(" ")[1]) - 1;
//        Task curr = myList[i];
            Task curr = (Task) myList.get(i);
            curr.setDone();
            System.out.println(curr);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Indicate which task you want to mark as done.");
        }
    }
}
