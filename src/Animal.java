import java.util.Random;

public class Animal {
    public static Random rand = new Random();
    private String type;
    private int strength;

    private int health;

    public Animal(){
        type = "";
        strength = 1;
        health = 10;
    }
    public Animal(String type, int strength, int health) {
        this.type = type;
        this.strength = strength;
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        //protect the private variable
        //it doesn't make sense to have less than 0 health
        if(health < 0){
            this.health = 0;
        }else{
            this.health = health;
        }
    }

    public int attack(){
        return rand.nextInt(getStrength() + 1);
    }



    public AnimalAttack detailedAttack(){
        return new AnimalAttack("attacks", attack());
    }

    /**
     * Override the toString method so that it prints out info on the animal instead of
     * the memory address.
     * @return
     */
    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", strength=" + strength +
                ", health=" + health +
                '}';
    }
}
