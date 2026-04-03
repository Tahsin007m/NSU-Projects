public class IndoorPlant extends BasePlant {

    public IndoorPlant(String name, int waterFrequency, double height, int currentDay) {
        super(name, waterFrequency, height, currentDay);
    }

    @Override
    public void grow() {
        if (health <= 0) return; // Dead plants do not grow
        height += 0.2; // or 0.5/0.3 depending on subclass
    }

    @Override
    public String getCareTip() {
        return "Indirect sunlight; water weekly.";
    }

    // New method: prune
    public void prune() {
        height -= 0.1;
        health += 5;
        if (health > 100) health = 100;
        System.out.println(name + " has been pruned. Health: " + health + "%");
    }
}
