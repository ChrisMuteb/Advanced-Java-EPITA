import fr.epita.training.datamodel.Passenger;
import fr.epita.training.services.PassengerCSVReader;

import java.io.IOException;
import java.util.List;

public class TestMVN2 {
    public static void test() throws IOException {
        PassengerCSVReader reader = new PassengerCSVReader();
        List<Passenger> list = reader.read("./training-exam/data.csv");
        reader.sortOnPClass(list);
        System.out.println(list);
    }
}
