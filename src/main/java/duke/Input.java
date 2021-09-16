package duke;

import java.util.ArrayList;

public class Input {

    private String input;

    public Input(String input) {
        this.input = input;
    }

    public String getDescription(String tag) {
        if (tag.equals("todo")) {
            return input.replaceFirst("^todo", "");
        } else if (tag.equals("deadline")) {
            return this.input.replaceFirst("^deadline", "").split(" /")[0];
        } else if (tag.equals("event")) {
            return this.input.replaceFirst("^event", "").split(" /")[0];
        }
        return "";
    }

    public String getDate(String tag) {
        if (tag.equals("deadline")) {
            return this.input.substring(input.indexOf("/by") + 4);
        } else if (tag.equals("event")) {
            return this.input.substring(input.indexOf("/at") + 4);
        }
        return "";
    }

    public ArrayList<Integer> getIndexArray(String tag, int lsSize) throws DukeException {
        if (tag.equals("delete")) {
            return generateIndexArray(lsSize, this.input.substring(7).split(" "));
        } else if (tag.equals("done")) {
            return generateIndexArray(lsSize, this.input.substring(5).split(" "));
        } else {
            return new ArrayList<>();
        }
    }

    public ArrayList<Integer> generateIndexArray(int lsSize, String ... a) throws DukeException {
        ArrayList<Integer> arr = new ArrayList<>();
        for (String i : a) {
            int index = Integer.parseInt(i) - 1;
            if (index < 0 || index >= lsSize) {
                throw new DukeException("Item does not exist in the list.");
            }
            arr.add(index);
        }
        return arr;
    }

    public String getKeyword() {
        return this.input.substring(5);
    }

    public boolean checkIfContains(String keyword) {
        return this.input.contains(keyword);
    }

    public boolean hasCommandWordOnly(String tag) {
        if (tag.equals("delete")) {
            return (this.input.equals("delete") || input.equals("delete "));
        } else if (tag.equals("done")) {
            return (equals("done") || input.equals("done "));
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return this.input.equals(o);
    }

    public String checkType() {
        if (input.equals("bye")) {
            return "bye";
        } else if (input.equals("list")) {
            return "list";
        } else if (input.startsWith("done")) {
            return "done";
        } else if (input.startsWith("delete")) {
            return "delete";
        } else if (input.startsWith("find")) {
            return "find";
        } else if (input.startsWith("todo")) {
            return "todo";
        } else if (input.startsWith("deadline")) {
            return "deadline";
        } else if (input.startsWith("event")) {
            return "event";
        } else {
            return "error";
        }
    }
}
