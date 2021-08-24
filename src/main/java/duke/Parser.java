package duke;

public class Parser {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    private boolean checkBye(String str) {
        boolean isBye = false;
        if (str.length() >= 3) {
            isBye = str.equals("bye");
        }
        return isBye;
    }

    private boolean checkList(String str) {
        boolean isList = false;
        if (str.length() >= 4) {
            isList = str.equals("list");
        }
        return isList;
    }

    private boolean checkDone(String str) {
        boolean isDone = false;
        if (str.length() >= 4) {
            isDone = str.substring(0,4).equals("done");
        }
        return isDone;
    }

    private boolean checkToDo(String str) {
        boolean isToDo = false;
        if (str.length() >= 4) {
            isToDo = str.substring(0,4).equals("todo");
        }
        return isToDo;
    }

    private boolean checkDeadLine(String str) {
        boolean isDeadLine = false;
        if (str.length() >= 8) {
            isDeadLine = str.substring(0,8).equals("deadline");
        }
        return isDeadLine;
    }

    private boolean checkEvent(String str) {
        boolean isEvent = false;
        if (str.length() >= 5) {
            isEvent = str.substring(0,5).equals("event");
        }
        return isEvent;
    }

    private boolean checkDelete(String str) {
        boolean isDelete = false;
        if (str.length() >= 6) {
            isDelete = str.substring(0,6).equals("delete");
        }
        return isDelete;
    }

    private boolean checkFind(String str) {
        boolean isFind = false;
        if (str.length() >= 4) {
            isFind = str.substring(0,4).equals("find");
        }
        return isFind;
    }

    public int checkCase(String input) {
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
        } else if (checkFind(input)) {
            caseNum = 8;
        }
        return caseNum;
    }

    private void listSeq(TaskList taskList) throws InputError {
        try {
            if (taskList.size() == 0) {
                throw new InputError("No items in list");
            }
            taskList.printList();
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void doneSeq(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("No task indicated");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            taskList.doneItem(indexNum);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void findSeq(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("No task indicated");
            }
            String searchWord = str.substring(5);
            TaskList foundList = taskList.findTasks(searchWord);
            if (foundList.currList().isEmpty()) {
                throw new InputError("No such tasks found");
            }
            System.out.println("We found these for you boss:");
            foundList.printList();
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void todoSeq(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("Description Please!");
            }
            taskList.addTodo(str);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void deadlineSeq(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 8) {
                throw new InputError("Description Please!");
            }
            taskList.addDeadline(str);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void eventSeq(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 5) {
                throw new InputError("Description Please!");
            }
            taskList.addEvent(str);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void deleteSeq(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 5) {
                throw new InputError("No Task to delete");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            taskList.deleteItem(indexNum);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }



    public void handle(int caseNum, String input, TaskList taskList) throws InputError {
        switch (caseNum) {
            case 2:
                listSeq(taskList);
                break;
            case 3:
                doneSeq(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 4:
                todoSeq(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 5:
                deadlineSeq(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 6:
                eventSeq(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 7:
                deleteSeq(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 8:
                findSeq(input, taskList);
                break;
            default:
                ui.invalidInput();
        }
    }
}
