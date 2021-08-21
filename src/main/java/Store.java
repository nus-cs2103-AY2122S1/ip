import java.io.*;

public class Store {

    private final String filePath;

    public Store(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks in hard disk to current task list
     *
     * @throws FileWritingException throws an FileWritingException if error encountered during
     *                              loading of tasks
     */
    public Tasklist loadTaskFromStore() throws FileWritingException {
        Tasklist result;
        try {

            File inputFile = new File(this.filePath);
            if (!inputFile.exists()){
                inputFile.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine;
            Tasklist storeList = new Tasklist();

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("")) {
                    continue;
                }
                Task currentTask = parseLine(currentLine);
                storeList.addTask(currentTask);
            }
            reader.close();
            result = storeList;

        } catch (IOException e) {
            throw new FileWritingException();
        }
        return result;
    }

    /**
     * Loads tasks in hard disk to current task list
     *
     * @throws FileWritingException throws an FileWritingException if error encountered during
     *                              loading of tasks
     */
    public void saveTasksToStore(Tasklist list) throws FileWritingException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            String fileString = "";
            fileString += list.toString();
            System.out.println(fileString);
            bw.write(fileString);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Task parseLine(String line) throws FileWritingException{
        char type = line.charAt(3);
        boolean isCompleted = line.charAt(6) == ('X') ? true : false;
        switch (type) {
            case 'T':
                Task tempTodoTask = new ToDo(line.substring(9));
                if (isCompleted) {
                    tempTodoTask.completeTask();
                }
                return tempTodoTask;
            case 'E':
                String eventDetails = line.substring(9);
                String[] checkEventDetails = eventDetails.split("at: ", 2);
                String eventDate = checkEventDetails[1].substring(0, checkEventDetails[1].length() - 1);
                Task tempEventTask = new Event(checkEventDetails[0].substring(0, checkEventDetails[0].length() -2), eventDate);
                if (isCompleted) {
                    tempEventTask.completeTask();
                }
                return tempEventTask;
            case 'D':
                String deadlineDetails = line.substring(9);
                String[] checkDeadlineDetails = deadlineDetails.split("by: ", 2);
                String deadlineDate = checkDeadlineDetails[1].substring(0, checkDeadlineDetails[1].length() - 1);
                Task tempDeadlineTask = new Event(checkDeadlineDetails[0].substring(0, checkDeadlineDetails[0].length() - 2), deadlineDate);
                if (isCompleted) {
                    tempDeadlineTask.completeTask();
                }
                return tempDeadlineTask;
            default:
                throw new FileWritingException();
        }

    }
}
