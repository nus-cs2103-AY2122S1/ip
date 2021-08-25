package duke.logic;

/**
 * The enums of the commands available. Also contains the help messages of each command.
 */
public enum LCommandsEnum {
    DELETE() {
        @Override
        public String helpMessage() {
            return "Usage: delete <task number>\n" +
                    "For example: delete 1\n" +
                    "This will delete the first task in the list.";
        }

    }, HELP() {
        @Override
        public String helpMessage() {
            return "Usage: help [command]\n" +
                    "For example: help\n" +
                    "This will display the help messages for every command. Alternatively,\n" +
                    "             help list\n" +
                    "This will display the help message for the list command.";
        }


    }, LIST() {
        @Override
        public String helpMessage() {
            return "Usage: list\n" +
                    "This will display the list of tasks.";
        }
    }, BYE() {
        @Override
        public String helpMessage() {
            return "Usage: bye\n" +
                    "This will quit the Duke chatbot.";
        }
    }, DONE() {
        @Override
        public String helpMessage() {
            return "Usage: done <task number>\n" +
                    "For example: done 1\n" +
                    "This will mark the first task as done.";
        }

    }, UPCOMING() {
        @Override
        public String helpMessage() {
            return "Usage: upcoming\n" +
                    "This will display all the upcoming tasks, in chronological order.";
        }
    }, TODO() {
        @Override
        public String helpMessage() {
            return "Usage: todo <task description>\n" +
                    "For example: todo Quit smoking\n" +
                    "This will add a todo task \"Quit smoking\" to the list.";
        }
    }, EVENT() {
        @Override
        public String helpMessage() {
            return "Usage: event <task description> /at <date and/or time>" +
                    "Where date and time is in the format: D/M/YYYY hh:mm\n" +
                    "For example: event Shopee 21.7 sale /at 21/7/2021\n" +
                    "This will add an event task \"Shopee 21.7 sale\" to the list," +
                    " with the date on 21 July 2021, 23:59 by default.\n" +
                    "             event first date /at 31/12/2090 12:00\n" +
                    "This will add an event task \"first date\" to the list," +
                    " with the date on 31 December 2090 on 12pm." ;
        }

    }, DEADLINE() {
        @Override
        public String helpMessage() {
            return "Usage: deadline <task description> /by <date and/or time>" +
                    "Where date and time is in the format: D/M/YYYY hh:mm\n" +
                    "For example: deadline haircut /by 19:00\n" +
                    "This will add a deadline task \"haircut\" to the list," +
                    " with today's date (or tomorrow if it is past 19:00) and the time 19:00.\n" +
                    "             deadline give $1 million to charity /by 31/12/2090 12:00\n" +
                    "This will add a deadline task \"give $1 million to charity\" to the list," +
                    " with the date on 31 December 2090 on 12pm." ;
        }
    }, FIND() {
        @Override
        public String helpMessage() {
            return "Usage: find <item>\n" +
                    "For example: find book\n" +
                    "This will list every task that contains \"book\".";
        }
    };

    /**
     * Gets the help message of the specific command.
     * @return the associated string.
     */
    public abstract String helpMessage();

}