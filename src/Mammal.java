public class Mammal extends Animal{
    private AnimalAttack[] attacks = new AnimalAttack[3];


    public Mammal(String type, int strength, int health){
        super(type, strength, health);
        attacks[0] = new AnimalAttack("claws", rand.nextInt(getStrength()));
        attacks[1] = new AnimalAttack("bites", rand.nextInt(getStrength()));
        attacks[2] = new AnimalAttack("kicks", rand.nextInt(getStrength()));


    }

    public int defend(int attack){
        return rand.nextInt(attack+1);
    }

    @Override
    public int attack(){
        return detailedAttack().getDamage();
    }

    @Override
    public AnimalAttack detailedAttack(){
        int attackNum = rand.nextInt(3);
        return attacks[attackNum];
    }

    @Override
    public String toString(){
        return super.toString() + " : Mammal";
    }
}
