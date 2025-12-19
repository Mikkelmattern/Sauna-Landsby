import java.util.ArrayList;

public class WoodSauna implements Sauna {
    private String name;
    private ArrayList<Integer> temperatures;

    WoodSauna(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType()
    {
        return "wood";
    }

    @Override
    public void addTemperature(int degrees) {
        temperatures.add(degrees);
    }

    @Override
    public double calculateDailyCost() {
        double cost = 0;
        for (int i : temperatures) {
            if (i > 50) {
                cost += 12;
            }
        }
        return cost;
    }
}
