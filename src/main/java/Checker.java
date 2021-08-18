public class Checker {
    public static int check(String str, String check) throws DukeException {
        int partition = str.indexOf(check);
        if (partition - 1 < 0 || partition + 3 > str.length()) {
            throw new DukeException("☹ OOPS!!! The task is formatted wrongly.");
        }
        return partition;
    }
}
