import java.util.ArrayList;
import java.util.Scanner;

public class PlantManager {
    private ArrayList<BasePlant> plants = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private int currentDay = 0;

    public static void main(String[] args) {
        new PlantManager().run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Plant Care Tracker (Day " + currentDay + ") ---");
            System.out.println("1. Add Plant");
            System.out.println("2. Water Plant");
            System.out.println("3. Grow Plants");
            System.out.println("4. Show All Plants");
            System.out.println("5. Advance 1 Day");
            System.out.println("6. Prune Indoor Plant");
            System.out.println("7. Harvest Medicinal Plant");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> addPlant();
                case "2" -> waterPlant();
                case "3" -> growPlants();
                case "4" -> showPlants();
                case "5" -> advanceDay();
                case "6" -> prunePlant();
                case "7" -> harvestPlant();
                case "0" -> exit = true;
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void addPlant() {
        try {
            System.out.print("Enter Plant Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Water Frequency (days): ");
            int wf = Integer.parseInt(sc.nextLine());
            System.out.print("Choose Plant Type (1=Indoor, 2=Outdoor, 3=Medicinal): ");
            int type = Integer.parseInt(sc.nextLine());

            BasePlant plant;
            switch (type) {
                case 1 -> plant = new IndoorPlant(name, wf, 1.0, currentDay);
                case 2 -> plant = new OutdoorPlant(name, wf, 1.0, currentDay);
                case 3 -> plant = new MedicinalPlant(name, wf, 1.0, currentDay);
                default -> {
                    System.out.println("Invalid type!");
                    return;
                }
            }
            plants.add(plant);
            System.out.println(name + " added successfully!");
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        }
    }

    private void waterPlant() {
        System.out.print("Enter Plant Name to water: ");
        String name = sc.nextLine();
        BasePlant p = findPlant(name);
        if (p != null) p.water(currentDay);
        else System.out.println("Plant not found!");
    }

    private void growPlants() {
        for (BasePlant p : plants) {
            p.checkNeglect(currentDay);
            p.grow();
        }
        System.out.println("All plants have grown a bit!");
    }

    private void showPlants() {
        if (plants.isEmpty()) {
            System.out.println("No plants added yet.");
            return;
        }

        System.out.println("\n----------------------- All Plants -------------------------");
        System.out.printf("%-15s | %-10s | %-6s | %s\n", "Plant Name", "Height", "Health", "Care Tips");
        System.out.println("--------------------------------------------------------------");
        for (BasePlant p : plants) {
            p.checkNeglect(currentDay);
            System.out.println(p); // calls toString(), includes getCareTip()
        }
        System.out.println("--------------------------------------------------------------");
    }

    private void advanceDay() {
        currentDay++;
        System.out.println("Advanced 1 day. Today is day " + currentDay);
    }

    private void prunePlant() {
        System.out.print("Enter Indoor Plant name to prune: ");
        String name = sc.nextLine();
        BasePlant p = findPlant(name);

        if (p instanceof IndoorPlant) {
            ((IndoorPlant) p).prune(); // downcast and call prune
        } else {
            System.out.println("Plant not found or not an Indoor Plant.");
        }
    }

    private void harvestPlant() {
        System.out.print("Enter Medicinal Plant name to harvest: ");
        String name = sc.nextLine();
        BasePlant p = findPlant(name);

        if (p instanceof MedicinalPlant) {
            ((MedicinalPlant) p).harvestLeaves(); // downcast and call harvestLeaves
        } else {
            System.out.println("Plant not found or not a Medicinal Plant.");
        }
    }

    private BasePlant findPlant(String name) {
        for (BasePlant p : plants) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }
}
