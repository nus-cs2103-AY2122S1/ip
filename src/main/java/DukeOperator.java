import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class DukeOperator {
    
    private String userInput;
    private final TaskStorage storage;
    private final Operation operation;
    private final String filePath = "./data/duke.txt";

    public DukeOperator(String userInput, TaskStorage storage) throws DukeException {
        this.userInput = userInput;
        this.storage = storage;
        DukeInputProcessor operationChecker = new DukeInputProcessor(userInput);
        this.operation = operationChecker.checkOperation();        
    }

    public DukeOperator(Operation operation, TaskStorage storage) {
        this.storage = storage;
        this.operation = operation;
    }

    public final boolean operate() {
        try {
            switch(this.operation) {
            case start:
                start();
                break;
            case bye:
                bye();
                return false;
            case list:
                list();
                break;
            case done:
                done();
                break;
            case delete:
                delete();
                break;
            case todo: 
                todo();
                break;
            case event:
                event();
                break;
            case deadline:
                deadline();
                break;
            }
            // if it is any operation that changes adjusts the tasks (start/bye)
            if (this.operation != Operation.start) {
                save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            System.out.println(
                StringFormat.tabAndFormat(
                        "☹ OOPS!!! Please enter an appropriate date (and optionally, 24-hour time)\n" + 
                        "Format: YYYY-MM-DD HH:MM"
                )
            );
        }
        return true;
    }

    private void start() throws IOException {
        // Starting Message
        String[] startMessage = {" ____        _        ", 
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "Hello! I'm Duke",
                "What can I do for you?"};
        System.out.println(StringFormat.formatString(startMessage));
        File saveFile = new File(filePath);
        saveFile.createNewFile();
        Scanner saveReader = new Scanner(saveFile);
        while(saveReader.hasNextLine()) {
            String inputRead = saveReader.nextLine();
            String[] inputs = inputRead.split("[|]");

            switch(inputs[0]) {
            case "T":
                storage.add(new Todo(inputs[2]));
                break;
            case "D":
                if (inputs.length == 4) {
                    storage.add(new Deadline(inputs[2], inputs[3]));
                    break;
                }
                storage.add(new Deadline(inputs[2], inputs[3], inputs[4]));
                break;
            case "E":
                if (inputs.length == 4) {
                    storage.add(new Event(inputs[2], inputs[3]));
                    break;
                }
                storage.add(new Event(inputs[2], inputs[3], inputs[4]));
                break;
            }

            if (inputs[1].equals("1")) {
                storage.markDone(storage.size() - 1);
            }
        }
        saveReader.close();
    }

    private void save() throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        for (int i = 0; i < storage.size(); i++){
            writer.write(storage.get(i).databaseString() + "\n");
        }
        writer.close();
    }

    private void bye() {
        System.out.println(
            StringFormat.formatString("Bye. Hope to see you again soon!")
        );
    }

    private void list() {
        System.out.println(
            StringFormat.tabAndFormat(this.storage.toString())
        );
    }

    private void done() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;
        System.out.println(
            StringFormat.tabAndFormat(storage.markDone(ind))
        );
    }

    private void delete() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;
        System.out.println(
            StringFormat.tabAndFormat(storage.delete(ind))
        );
    }

    private void todo() {
        String[] inputs = this.userInput.split(" ", 2);
        System.out.println(
            StringFormat.tabAndFormat(
                storage.add(new Todo(inputs[1]))
            )
        );  
    }

    private void event() {
        String[] inputs = this.userInput.split(" ", 2);
        String[] args = inputs[1].split(" /at ", 2);
        String[] datetimeArgs = args[1].split(" ", 2);
        if (datetimeArgs.length == 1) {
            System.out.println(
                StringFormat.tabAndFormat(
                    storage.add(
                        new Event(args[0], args[1])
                    )
                )
            ); 
        } else {
            System.out.println(
                StringFormat.tabAndFormat(
                    storage.add(
                        new Event(args[0], datetimeArgs[0], datetimeArgs[1])
                    )
                )
            );
        }
    }

    private void deadline() {
        String[] inputs = this.userInput.split(" ", 2);
        String[] args = inputs[1].split(" /by ", 2);

        String[] datetimeArgs = args[1].split(" ", 2);
        if (datetimeArgs.length == 1) {
            System.out.println(
                StringFormat.tabAndFormat(
                    storage.add(
                        new Deadline(args[0], args[1])
                    )
                )
            ); 
        } else {
            System.out.println(
                StringFormat.tabAndFormat(
                    storage.add(
                        new Deadline(args[0], datetimeArgs[0], datetimeArgs[1])
                    )
                )
            );
        }
    }

}
