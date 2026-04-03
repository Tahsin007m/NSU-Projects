public class OutdoorPlant extends BasePlant {

    public OutdoorPlant(String name, int waterFrequency, double height, int currentDay) {
        super(name, waterFrequency, height, currentDay);
    }

    @Override
    public void grow() {
        if (health <= 0) return; // Dead plants do not grow
        height += 0.2; // or 0.5/0.3 depending on subclass
    }

    @Override
    public String getCareTip() {
        return "Full sunlight; regular watering.";
    }

}
