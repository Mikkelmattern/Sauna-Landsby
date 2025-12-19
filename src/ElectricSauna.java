import java.util.ArrayList;

public class ElectricSauna implements Sauna {
    private String name;
    private ArrayList<Integer> temperatures = new ArrayList<>();

    ElectricSauna(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addTemperature(int degrees) {
        temperatures.add(degrees);

    }

    @Override
    public String getType() {
        return "electric";
    }


    @Override
    public double calculateDailyCost() {
        double cost = 0;
        for (int degree : temperatures) {
            if (degree > 50) {
                cost += 8;
                if (degree > 70) {
                    cost += (degree - 70) * 2;
                }
            }
        }
        return cost;
    }
}
