import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class ChatBot implements Serializable {

    private ArrayList<Task> list = new ArrayList<Task>();
    private int lastIndex = 0;
    private int currIndex = 1;
    private Writer saver = new Writer();

    public void setList(ArrayList list) {
        this.list = list;
        lastIndex = list.size();
    }

    private String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Why hello there! It's Duke here!\n" + "How can I help you today master?";

        return greeting;
    }

    private void printItem(Task task) {
        System.out.println(currIndex + "." + task.printTask());
        currIndex++;
    }

    private void printList() {
        list.forEach((task) -> printItem(task));
        currIndex = 1;
    }

    private boolean checkBye(String str) {
        boolean isBye = false;
        if (str.length() >= 3) {
            isBye = str.equals("bye");
        }
        return isBye;
    }

    private void byeSeq() {
        System.out.println("See ya again later!");
    }

    private boolean checkList(String str) {
        boolean isList = false;
        if (str.length() >= 4) {
            isList = str.equals("list");
        }
        return isList;
    }

    private void listSeq() throws InputError {
        try {
            if (list.isEmpty()) {
                throw new InputError("No items in list");
            }
            printList();
        } catch (InputError e) {
            System.out.println("Here is the error boss. " + e);
        }
    }

    private boolean checkDone(String str) {
        boolean isDone = false;
        if (str.length() >= 4) {
            isDone = str.substring(0,4).equals("done");
        }
        return isDone;
    }

    private void doneSeq(String str) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("No task indicated");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));

            if (indexNum > lastIndex) {
                throw new InputError("Invalid Number");
            }
            System.out.println("Good job for this thing done man:");
            Task currTask = list.get(indexNum - 1);
            currTask.setComplete();
            System.out.println("   " + currTask.printTask());
        } catch (InputError e) {
            System.out.println("Here is the error boss. " + e);
        }
    }

    private boolean checkToDo(String str) {
        boolean isToDo = false;
        if (str.length() >= 4) {
            isToDo = str.substring(0,4).equals("todo");
        }
        return isToDo;
    }

    private void todoSeq(String str) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("Description Please!");
            }
            System.out.println("Alrighty! I have added this task:");
            list.add(new ToDo(str.substring(5)));
            System.out.println("   " + list.get(lastIndex).printTask());
            lastIndex++;
            System.out.println("Now you have " + lastIndex + " task(s) in total!");
        } catch (InputError e) {
            System.out.println("Here is the error boss. " + e);
        }
    }

    private boolean checkDeadLine(String str) {
        boolean isDeadLine = false;
        if (str.length() >= 8) {
            isDeadLine = str.substring(0,8).equals("deadline");
        }
        return isDeadLine;
    }

    private void deadlineSeq(String str) throws InputError {
        try {
            if (str.length() == 8) {
                throw new InputError("Description Please!");
            }
            System.out.println("Alrighty! I have added this task:");
            list.add(new Deadline(str.substring(9, str.indexOf("/")), str.substring(str.indexOf("/") + 4)));
            System.out.println("   " + list.get(lastIndex).printTask());
            lastIndex++;
            System.out.println("Now you have " + lastIndex + " task(s) in total!");
        } catch (InputError e) {
            System.out.println("Here is the error boss. " + e);
        }
    }

    private boolean checkEvent(String str) {
        boolean isEvent = false;
        if (str.length() >= 5) {
            isEvent = str.substring(0,5).equals("event");
        }
        return isEvent;
    }

    private void eventSeq(String str) throws InputError {
        try {
            if (str.length() == 5) {
                throw new InputError("Description Please!");
            } else {
                System.out.println("Alrighty! I have added this task:");
                list.add(new Event(str.substring(6, str.indexOf("/")), str.substring(str.indexOf("/") + 4)));
                System.out.println("   " + list.get(lastIndex).printTask());
                lastIndex++;
                System.out.println("Now you have " + lastIndex + " task(s) in total!");
            }
        } catch (InputError e) {
            System.out.println("Here is the error boss. " + e);
        }
    }

    private boolean checkDelete(String str) {
        boolean isDelete = false;
        if (str.length() >= 6) {
            isDelete = str.substring(0,6).equals("delete");
        }
        return isDelete;
    }

    private void deleteSeq(String str) throws InputError {
        try {
            if (str.length() == 5) {
                throw new InputError("No Task to input");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            if (indexNum > lastIndex) {
                throw new InputError("Invalid Number");
            }
            System.out.println("Alrighty! I have deleted this task:");
            Task removed = list.remove(indexNum - 1);
            System.out.println("   " + removed.printTask());
            lastIndex--;
            System.out.println("Now you have " + lastIndex + " task(s) in total!");
        } catch (InputError e) {
            System.out.println("Here is the error boss. " + e);
            System.out.println("You have " + lastIndex + " item(s) in your list!");
        }
    }

    private void startInput() throws InputError {
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        int caseNum = 0;

        if (checkBye(input)) {
            caseNum = 1;
        } else if (checkList(input)) {
            caseNum = 2;
        } else if (checkDone(input)) {
            caseNum = 3;
        } else if (checkToDo(input)) {
            caseNum = 4;
        } else if (checkDeadLine(input)) {
            caseNum = 5;
        } else if (checkEvent(input)) {
            caseNum = 6;
        } else if (checkDelete(input)) {
            caseNum = 7;
        }

        switch (caseNum) {
            case 1:
                byeSeq();
                userInput.close();
                saver.fileSaver(list);
                return;
            case 2:
                listSeq();
                break;
            case 3:
                doneSeq(input);
                saver.fileSaver(list);
                break;
            case 4:
                todoSeq(input);
                saver.fileSaver(list);
                break;
            case 5:
                deadlineSeq(input);
                saver.fileSaver(list);
                break;
            case 6:
                eventSeq(input);
                saver.fileSaver(list);
                break;
            case 7:
                deleteSeq(input);
                saver.fileSaver(list);
                break;
            default:
                try {
                    throw new InputError("Invalid Input");
                } catch (InputError e) {
                    System.out.println("Here is the error boss. " + e);
                    System.out.println("I'm not too sure what you meant.");
                    System.out.println("Try again with these keywords.");
                    System.out.println("todo deadline event");
                }
        }
        startInput();
    }

    void welcomeSeq() throws InputError {
        ArrayList savedList = saver.fileReader();
        if (savedList != null) {
            setList(savedList);
        }
        System.out.println(this.welcomeMessage());
        startInput();
    }
}
