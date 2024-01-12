package fr.epita.training.services;

import fr.epita.training.datamodel.Passenger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PassengerCSVReader {
    public List<Passenger> read(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        lines.remove(0);
        List<Passenger> results = new ArrayList<>();
        for(String line: lines){
            String[] split = line.split(";");
            Passenger passenger = new Passenger();
            passenger.setName(split[0].trim());
            passenger.setpClass(split[1].trim());
            if(split[2] == null || split[2].trim().isEmpty()){
                passenger.setAge(0);
            }else {
                passenger.setAge(Double.parseDouble(split[2].trim()));
            }
            passenger.setSex(split[3].trim());
            passenger.setSurvived("0".equals(split[1]) ? false : true);
            results.add(passenger);
        }
        return results;
    }
    public List<Passenger> sortOnPClass(List<Passenger> list){
        list.sort(Comparator.comparing(Passenger::getpClass));
        return list;
    }
}
