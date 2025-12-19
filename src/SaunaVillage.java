import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaunaVillage {
    private final ArrayList<Sauna> saunas = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public void run(){
        loadFromCSV("sauna");
        printReport();
        System.out.println("===Udskriv gennemsnit for===\n1:Wood\n2:Electric");
        int i = Integer.parseInt(sc.nextLine());
        double printAverage = 0;
        String type = "";
        switch (i) {
            case 1 -> {
                printAverage = getAverageCost("Wood");
                type = "træ";
            }
            case 2 -> {
                printAverage = getAverageCost("electric");
                type = "elektrisk";
            }
            default -> System.out.println("Vælg wood eller electric");
        }
        System.out.printf("Gennemsnitsomkostningerne for typen %s er %.2fkr", type, printAverage);
        Sauna e = getMostExpensive();
        Sauna c = getCheapest();
        System.out.println("\n"+"\nDen dyrest at betjene er"+e.toString());
        System.out.println("\nDen billigeste at betjene er"+c.toString());

    }

    public void printReport() {
        for (Sauna s : saunas) {
            double cost = s.calculateDailyCost();
            System.out.println(cost + "kr");
        }
    }

    public void loadFromCSV(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                Sauna newWoodSauna;
                Sauna newElecricSauna;
                scanner.nextLine();
                String s = scanner.nextLine();
                String[] array = s.split(",");
                Sauna found = null;
                for (Sauna sauna : saunas) {
                    if (sauna.getName().equals(array[2]))
                        found = sauna;
                    break;
                }
                if (!(found == null)) {
                }
                if (array[3].equalsIgnoreCase("wood")) {
                    newWoodSauna = new WoodSauna(array[2]);
                    found = newWoodSauna;
                } else {
                    newElecricSauna = new ElectricSauna(array[2]);
                    found = newElecricSauna;
                }
                found.addTemperature(Integer.parseInt(array[1]));
                saunas.add(found);

            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
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

    private Sauna getMostExpensive() {
        Sauna mostExpensive = null;
        double prevExpensive = Double.NEGATIVE_INFINITY;
        double i;
        if(saunas.isEmpty()){
            System.out.println("getMostExpensive er tom");
            return null;
        }
        for (Sauna s : saunas) {
             i = s.calculateDailyCost();
            if (i > prevExpensive) {
                mostExpensive = s;
                prevExpensive = i;
            }
        }
        return mostExpensive;
    }

    private Sauna getCheapest() {
        Sauna cheapestSauna = null;
        double prevCheapest = Double.POSITIVE_INFINITY;
        double i;
        if(saunas.isEmpty()){
            System.out.println("getCheapest er tom");
            return null;
        }
        for (Sauna s : saunas) {
           i = s.calculateDailyCost();
            if(prevCheapest>i){
                prevCheapest = i;
                cheapestSauna = s;
            }
        }
        return cheapestSauna;
    }
}
