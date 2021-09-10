package gnosis.database;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gnosis.model.Place;


public class PlaceDbManager extends DatabaseManager {

    public PlaceDbManager(String fileName) {
        super(fileName);
    }

    /**
     * Loads up Gnosis file to display.
     *
     * @return places Returns a list of places from file.
     */
    public List<Place> loadGnosisPlaces() {
        //check if user has folder:
        // if no folder -> means no data found -> create one from scratch
        // if folder found -> load to arraylist places
        if (this.isDataFileAvail()) {
            return this.getTasksFromFile();
        } else {
            this.createDataFolder();
            return new ArrayList<>();
        }
    }

    /**
     * Writes Places into file from input.
     *
     * @param places List of places to write to file.
     */
    public void writePlacesToFile(List<Place> places) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.getFilePath()));
            writer.write("Place visited,Address,DateTime");
            writer.newLine();

            for (Place record: places) {

                String oneLine = record.getName() + DatabaseManager.DELIMITER
                        + record.getAddress() + DatabaseManager.DELIMITER
                        + record.getDateVisited();

                writer.write(oneLine);
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Place> getTasksFromFile() {

        List<Place> places = new ArrayList<>();
        try {
            places = Files.newBufferedReader(Paths.get(this.getFilePath()))
                    .lines()
                    .skip(1)
                    .map(PlaceDbManager::parsePlace)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return places;
    }

    private static Place parsePlace(String s) {
        String[] tokens = s.split(DELIMITER);

        String name = tokens[0];
        String address = tokens[1];
        LocalDateTime dt = LocalDateTime.parse(tokens[2]);
        return new Place(name, address, dt);
    }
}
