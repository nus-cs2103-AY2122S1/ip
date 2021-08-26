import java.io.*;

public class Storage {

    //path of file containing stored data
    private String filePath;
    //path of folder containing data file
    private String directoryPath;
    //FIle containing stored data
    private File dataFile;
    //the instance of Duke that this instance of Storage is operating under
    private Duke dukeInstance;
    //instance of taskList used to store tasks
    private TaskList taskList;


    public Storage(String filePath, String directoryPath, Duke dukeInstance, TaskList taskList) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        this.dukeInstance = dukeInstance;
        this.taskList = taskList;
    }

    public void start() {
        //set up the file
        dataFile = new File(filePath);
        //file does not exist: attempt to create a new file.
        if (!dataFile.canRead()) {
            try {
                new File(directoryPath).mkdir();
                if (dataFile.createNewFile()) {
                    System.out.println("A new save file has been created in root/data.");
                }

            } catch (Exception e) {
                System.out.println("An error has occurred when creating your file.");
                System.out.println("Please try running Duke again!");
                return;
            }
        }

        loadData(dukeInstance);
    }

    private void loadData(Duke dukeInstance) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                taskList.addTask((DataParser.readData(nextLine)));
            }
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Whoops, there is some problem with your file!");
            } else {
                System.out.println("Your save file is corrupted! Did you not take care of it?");
                System.out.println("Try deleting your save file then try again!");
            }

        }

    }

    public void saveData(Duke dukeInstance) {
        try {
            FileWriter writer = new FileWriter(dataFile);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < taskList.getTaskNumber(); i++) {
                Task task = taskList.getTask(i);
                str.append(task.toDataString()).append("\n");
            }
            writer.write(str.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured while saving your file.");
        }

    }
}
