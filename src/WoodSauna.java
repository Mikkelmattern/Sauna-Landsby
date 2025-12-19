import java.util.ArrayList;

public class WoodSauna implements Sauna {
    private String name;
    private ArrayList<Integer> temperatures = new ArrayList<>();

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
        return "Wood";
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

    @Override
    public String toString() {
        return " "+getType()+": " + name + " " + calculateDailyCost() + "kr";
    }
}
