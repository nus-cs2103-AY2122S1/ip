public abstract class Parser {
    public static Command parse(String in) throws DukeException {
        if (in.equals("list")) {
            return new ListCommand();
        } else if (in.startsWith("done")) {
            try {
                String[] temp = in.split(" ");
                return new MarkDoneCommand(Integer.parseInt(temp[1]) - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry! Your done command has an invalid index choice");
            } catch (NumberFormatException e) {
                System.out.println("Sorry! I can't understand the index for your done command");
            }
        } else if (in.startsWith("todo") || in.startsWith("deadline") || in.startsWith("event")) {
            String type;
            if (in.startsWith("todo")) {
                in = in.replaceFirst("todo", "");
                type = "todo";
            } else if (in.startsWith("deadline")) {
                in = in.replaceFirst("deadline", "");
                type = "deadline";
            } else {
                in = in.replaceFirst("event", "");
                type = "event";
            }

            if (in.equals("")) {
                throw new DukeException("Command has empty description");
            }
            return new AddCommand(type, in);

        } else if (in.startsWith("delete")) {
            try {
                String[] temp = in.split(" ");
                return new DeleteCommand(Integer.parseInt(temp[1]) - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry! Your delete command has an invalid index choice");
            } catch (NumberFormatException e) {
                System.out.println("Sorry! I can't understand the index for your delete command");
            }
        } else if (in.equals("bye")) {
            return new DoneCommand();
        } else {
            return new InvalidCommand(); //temporary nonsense
        }
        return new InvalidCommand();
    }
}
