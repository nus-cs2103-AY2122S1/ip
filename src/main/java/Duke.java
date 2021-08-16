import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        System.out.println("Look at me! I'm DUKE\n"  + "How can I help?");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(!userInput.equals("bye")) {
            handleInputs(todoList, userInput);
            System.out.print("Whats next?");
            userInput = scanner.nextLine();
        }
        PrintResponse.print("Ooooh yeah! Can do!");
    }

    private static void handleInputs(TodoList todoList, String userInput) {
        try {
            int indexOfSpace = userInput.indexOf(' ');
            String firstWord = indexOfSpace == -1 ?userInput : userInput.substring(0, indexOfSpace);
            switch (firstWord) {
                case "list":
                    todoList.list();
                    break;
                case "done":
                    if (userInput.matches("^done [0-9]+")) {
                        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                        todoList.markAsDone(taskNumber);
                    } else {
                        throw new DukeException(">:( follow format below:\n" +
                                "done <number between 1 and 100>");
                    }
                    break;
                case "delete":
                    if (userInput.matches("^delete [0-9]+")) {
                        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                        todoList.deleteTask(taskNumber);
                    } else {
                        throw new DukeException(">:( follow format below:\n" +
                                "done <number between 1 and 100>");
                    }
                    break;
                case "deadline":
                    if (userInput.matches("^deadline .+ /by .+")) {
                        String[] deadlineDetails = userInput.substring(indexOfSpace + 1).split(" /by ");
                        String name = deadlineDetails[0];
                        String dateTime = deadlineDetails[1];
                        todoList.addDeadline(name, dateTime);
                    } else {
                        throw new DukeException(">:( Follow format below:\n" +
                                "deadline <taskname...> /by <datetime...>");
                    }
                    break;
                case "event":
                    if(userInput.matches("^event .+ /at .+")) {
                        String[] deadlineDetails = userInput.substring(indexOfSpace + 1).split(" /at ");
                        String name = deadlineDetails[0];
                        String dateTime = deadlineDetails[1];
                        todoList.addEvent(name, dateTime);
                    } else {
                        throw new DukeException(">:( Follow format below:\n" +
                                "deadline <taskname...> /at <datetime...>");
                    }
                    break;
                case "todo":
                    if(userInput.matches("^todo .+")) {
                        String name = userInput.substring(indexOfSpace + 1);
                        todoList.addTodo(name);
                    } else {
                        throw new DukeException(">:( include task name after todo:\n" +
                                "todo <some task name>");
                    }
                    break;
                default:
                    throw new DukeException("Say something I can understand!! >:(");
            }
        } catch (DukeException e) {
            PrintResponse.print(e.getMessage());
        }
    }


}
