public abstract class BasePlant implements CareTips {
    protected String name;
    protected int waterFrequency; // days
    protected double height; // cm
    protected int lastWateredDay; // simulated day counter
    protected int health = 100; // dynamic health

    public BasePlant(String name, int waterFrequency, double height, int currentDay) {
        this.name = name;
        this.waterFrequency = waterFrequency;
        this.height = height;
        this.lastWateredDay = currentDay; // day plant was last watered
    }

    // Water the plant
    public void water(int currentDay) {
        lastWateredDay = currentDay;
        if (health <= 0) health = 10; // revive with 10% health
        else health += 10;
        if (health > 100) health = 100;
        System.out.println(name + " has been watered. Health: " + ((health <= 0) ? "Dead" : health + "%"));
    }

    public abstract void grow();

    public String getName() { return name; }

    public int getHealth() { return health; }

    // Check neglect based on simulated days
    public void checkNeglect(int currentDay) {
        int daysMissed = currentDay - lastWateredDay;
        if (daysMissed > waterFrequency) {
            health -= 10;
            if (health < 0) health = 0;
        }
    }

    @Override
    public String toString() {
        String healthDisplay = (health <= 0) ? "Dead" : health + "%";
        return String.format("%-15s | Height: %4.1f cm | Health: %s | Care: %s",
                name, height, healthDisplay, getCareTip());
    }
}
