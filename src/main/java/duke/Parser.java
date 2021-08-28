package duke;

public class Parser {

    public static Command parse(String input) throws DukeException{
        String first_word = input.split(" ")[0];


        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (first_word.equals("done")) {
            int index;
            try {
                index = Integer.parseInt(input.split(" ")[1]) - 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Sorry, please enter an integer after 'done'. (e.g. done 2)");
            }
            return new DoneCommand(index);
        } else if (first_word.equals("delete")) {
            int index;
            try {
                index = Integer.parseInt(input.split(" ")[1]) - 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Sorry, please enter an integer after 'delete'. (e.g. delete 2)");
            }
            return new DeleteCommand(index);
        }
        else {
            String remaining;
            try {
                remaining = input.split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Sorry, tasks must include descriptions.");
            }
            return new AddCommand(first_word, remaining);
        }


    }
}
