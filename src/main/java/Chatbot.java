import java.util.*;
import java.util.List;

public class Chatbot {
    private enum ChatCommands {
        BYE("bye"),
        LIST("list");

        private final String command;

        ChatCommands(final String command) {
            this.command = command;
        }

        public static ChatCommands toEnum (String str) {
            for(ChatCommands chatCommand: ChatCommands.values()) {
                if (chatCommand.command.equalsIgnoreCase(str)) {
                    return chatCommand;
                }
            }
            return null;
        }
    }

    private enum TaskCommands {
        DONE("done"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private final String command;

        TaskCommands(final String command) {
            this.command = command;
        }

        public static TaskCommands toEnum (String str) {
            String[] splitCommand = str.split(" ", 2);
            for(TaskCommands chatCommand: TaskCommands.values()) {
                if (chatCommand.command.equalsIgnoreCase(splitCommand[0])) {
                    return chatCommand;
                }
            }
            return null;
        }
    }

    private enum ChatContinue {
        CONTINUE,
        END,
    }

    private Scanner scanner;
    private List<Task> memory;

    public Chatbot() {
        this.scanner = new Scanner(System.in);
        this.memory = new LinkedList<>();

        System.out.println("Hey there, I'm Duke! Nice to meet you!");
        System.out.println("I'm a bot build by SpdPnd98, let me know how I can help you!");
        System.out.println("Here are some things I can do now:");
        System.out.println("1. Bye");
        System.out.println("I'll learn what you're saying and " +
                           "repeat it to you next time you say it if it's something new!");
    }

    public void chat() {
        boolean keepChatting = true;
        while (keepChatting) {
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("How can I help you?");
            System.out.print("User: ");
            try {
                keepChatting = interpret() == ChatContinue.CONTINUE;
            } catch (DukeArgumentException e) {
                System.out.println(e.getMessage());
            } catch (DukeTaskException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    private ChatContinue interpret() {
        String input = scanner.nextLine();
        ChatCommands command = ChatCommands.toEnum(input);
        if (command != null) {
            return builtInCommands(command);
        }
        TaskCommands taskCommand = TaskCommands.toEnum(input);
        if (taskCommand != null) {
            String[] arguments = input.split(" ", 2);
            if (arguments.length == 1) {
                throw new DukeArgumentException("No items specified for '" + taskCommand.command + "'!");
            }
            return addTask(taskCommand, arguments[1]);
        }
        throw new DukeArgumentException("Looks like I don't support those commands yet...");
    }

    private ChatContinue addTask(TaskCommands command, String input) {
        switch (command) {
            case DONE:
                return this.markDone(input);
            case DELETE:
                return this.delete(input);
            case TODO:
                return this.addTodo(input);
            case DEADLINE:
                return this.addDeadline(input);
            case EVENT:
                return this.addEvent(input);
            default:
                System.out.println("This action is not supported yet. Contact the devs for more information.");
                return ChatContinue.CONTINUE;
        }
    }

    private ChatContinue addTodo(String input) {
        ToDo todo = new ToDo(input);
        memory.add(todo);
        this.displayAddTaskSuccessful(todo);
        return ChatContinue.CONTINUE;
    }

    private ChatContinue addDeadline(String input) {
        Deadline deadline = new Deadline(input);
        memory.add(deadline);
        this.displayAddTaskSuccessful(deadline);
        return ChatContinue.CONTINUE;
    }

    private ChatContinue addEvent(String input) {
        Event event = new Event(input);
        memory.add(event);
        this.displayAddTaskSuccessful(event);
        return ChatContinue.CONTINUE;
    }

    private void displayAddTaskSuccessful(Task task) {
        System.out.println("Got it. Added the following task");
        System.out.println(task);
    }

    private ChatContinue builtInCommands(ChatCommands command) {
        switch (command) {
            case BYE:
                return this.farewell();
            case LIST:
                return this.list();
            default:
                System.out.println("How did you get here?");
                return ChatContinue.END;
        }
    }

    private ChatContinue farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        return ChatContinue.END;
    }

    private ChatContinue list() {
        if (memory.size() == 0) {
            System.out.println("You haven't asked me to store anything yet... Good! :)");
        } else {
            System.out.println("Here are the tasks in the list:");
            Iterator<Task> itr = memory.iterator();
            for (int i = 1; i <= memory.size(); i++) {
                System.out.println(i + ". " + itr.next());
            }
        }
        return ChatContinue.CONTINUE;
    }

    private ChatContinue markDone(String args) {
        try {
            int index = Integer.parseInt(args);
            Task task = memory.get(index - 1);
            task.markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeArgumentException("Sorry, that item does not exist!");
        } catch (NumberFormatException e) {
            throw new DukeArgumentException("The index is invalid");
        }
        return ChatContinue.CONTINUE;
    }

    private ChatContinue delete(String args) {
        try {
            int index = Integer.parseInt(args);
            if (index > 0 && index <= memory.size()) {
                Task task = memory.get(index - 1);
                memory.remove(index - 1);
                System.out.println("Noted. I have removed this task.");
                System.out.println("  " + task);
            } else {
                throw new DukeTaskException("Task does not exist!");
            }
        } catch (NumberFormatException e) {
            throw new DukeTaskException("Task does not exist!");
        }
        return ChatContinue.CONTINUE;
    }
}
