import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaunaVillage {
    private ArrayList<Sauna> saunas;

    private FileHandler fh = new FileHandler();

    public void run() throws FileNotFoundException {
        loadFromCSV("sauna");
    }

    public void printReport() {
        for (Sauna s : saunas) {
            double cost = s.calculateDailyCost();
            System.out.println(cost);
        }
    }

    public void loadFromCSV(String fileName) {
        try {
            Scanner scanner = new Scanner(fileName);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] array = s.split(",");
                Sauna found = null;
                for (Sauna sauna : saunas) {
                    if (sauna.getName().equals(array[2]))
                        found = sauna;
                    break;
                }
                if (found == null) {
                    Sauna newSauna;
                    if (array[3].equalsIgnoreCase("wood")) {
                        newSauna = new WoodSauna(array[2]);
                    } else {
                        newSauna = new ElectricSauna(array[2]);
                    }
                    saunas.add(newSauna);
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public double getAverageCost(String type) {
        double averageAmount = 0;
        int count = 0;
        for (Sauna s : saunas) {
            if (type.equalsIgnoreCase(s.getType())) {
                averageAmount += s.calculateDailyCost();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return averageAmount / count;
    }
}
