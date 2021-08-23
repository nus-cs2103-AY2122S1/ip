public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }

    public static class AddCommand extends Command {
        private int taskType;
        private String input;

        public AddCommand(String input, int type) {
            this.taskType = type;
            this.input = input;
        }

        @Override
        public void execute(TaskList taskList, Ui ui, Storage storage) {
            try {
                TaskList.Task task = taskList.add(input, taskType);
                ui.addUi(taskList, task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class DoneCommand extends Command {
        private int taskNumber;

        public DoneCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList taskList, Ui ui, Storage storage) {
            try {
                ui.doneUi(taskList.done(taskNumber));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class DeleteCommand extends Command {
        private int taskNumber;

        public DeleteCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList taskList, Ui ui, Storage storage) {
            try {
                ui.deleteUi(taskList, taskList.delete(taskNumber));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class ListCommand extends Command {
        public ListCommand() {};

        @Override
        public void execute(TaskList taskList, Ui ui, Storage storage) {
            ui.listUi(taskList);
        }
    }

    public static class ExitCommand extends Command {
        public ExitCommand() {};

        @Override
        public void execute(TaskList taskList, Ui ui, Storage storage) {
            storage.write(taskList);
            ui.exitUi();
        }

        @Override
        public boolean isExit() {
            return true;
        }
    }

    public static class UnknowCommand extends Command {
        public UnknowCommand() {};

        @Override
        public void execute(TaskList taskList, Ui ui, Storage storage) {
            ui.unknownUi();
        }
    }
}

