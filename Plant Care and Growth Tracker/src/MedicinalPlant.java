public class MedicinalPlant extends BasePlant {

    public MedicinalPlant(String name, int waterFrequency, double height, int currentDay) {
        super(name, waterFrequency, height, currentDay);
    }

    @Override
    public void grow() {
        if (health <= 0) return; // Dead plants do not grow
        height += 0.2; // or 0.5/0.3 depending on subclass
    }

    @Override
    public String getCareTip() {
        return "Limited water; moderate sunlight.";
    }

    // New method: harvestLeaves
    public void harvestLeaves() {
        System.out.println(name + " leaves harvested for medicinal use.");
        height -= 0.2;
        health -= 2;
        if (health < 0) health = 0;
    }
}

