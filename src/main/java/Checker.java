public class Checker {
    public static Task check(String str, String check) throws DukeException {
        int partition = str.indexOf(check);
        if (partition < 0 || partition + 4 > str.length()) {
            throw new DukeException("☹ OOPS!!! The task is formatted wrongly.");
        }
        String str1 = str.substring(0, partition);
        String str2 = str.substring(partition + 4);
        switch (check) {
            case "/by ":
                return new Deadline(str1, str2);

            case "/at ":
                return new Event(str1, str2);

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
