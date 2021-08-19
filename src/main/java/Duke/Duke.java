package Duke;

import Duke.Commands.Command;
import Duke.Task.TaskList;

import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "Hello I'm Duke!";
    private static final String EXIT_MESSAGE = "Bye bye! Hope you have a productive day :)";
    private static final String RULER = "\n````````````````````````````````````````````````````````\n";
    private static final String INPUT_PROMPT = "> ";

    private boolean stopped = false;
    private final TaskList taskList = new TaskList();

    public void say(String message) {
        message = RULER + message + RULER;
        String indentedMessage = String.join("\n\t", message.split("\n"));
        System.out.println(indentedMessage);
    }

    public void run() {
        this.say(GREETING_MESSAGE);
        while (!this.stopped) {
            UserInput input = new UserInput();
            input.readAndParse();
            Command.matching(input).run(this, input);
        }
        this.say(EXIT_MESSAGE);
    }

    public void stop() {
        this.stopped = true;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public static class UserInput {
        private static final Scanner scanner = new Scanner(System.in);

        private String raw;
        private String keyword;
        private String args;

        private void readAndParse() {
            System.out.print(INPUT_PROMPT);
            this.raw = UserInput.scanner.nextLine();
            String[] splitInput = this.raw.trim().split("\\s+", 2);
            this.keyword = splitInput[0];
            this.args = splitInput.length > 1 ? splitInput[1] : "";
        }

        public String getKeyword() {
            return this.keyword;
        }

        public String getArgs() {
            return this.args;
        }

        public String getRaw() {
            return this.raw;
        }
    }
}
