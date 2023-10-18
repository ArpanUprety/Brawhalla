import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalArena {
    public static Random rand = new Random();
    public static final int MAXHEALTH = 20;
    public static final int MAXSTRENGTH = 10;

    public static final int MILLIDELAY = 500; //use this variable to control timing in console output.
    public static void main(String[] args) {
        Animal liz = CreateAnimalUsingDefaultConstructorAndMutators();
        Animal dog = CreateAnimalWithParameterizedConstructor();
        System.out.println(liz);
        System.out.println(dog);
        pause(2000);
        Fight(liz, dog);
    }

    public static Animal CreateAnimalUsingDefaultConstructorAndMutators(){
        //default constructor. We create an OBJECT from the Animal CLASS
        Animal a = new Animal();
        a.setType("lizard"); //mutator methods. Notice "set[Property]"
        a.setStrength(ThreadLocalRandom.current().nextInt((MAXSTRENGTH )+ 1));//+1 to make sure it's between 1 and 10
        a.setHealth(ThreadLocalRandom.current().nextInt(11,MAXHEALTH + 1));// +1 to set the EXCLUSIVE upper bound to max+1
        return a;
    }

    public static Animal CreateAnimalWithParameterizedConstructor(){
        int strength = ThreadLocalRandom.current().nextInt((MAXSTRENGTH )+ 1); //+1 to make sure it's between 1 and 10
        int health = ThreadLocalRandom.current().nextInt(11,MAXHEALTH + 1); // +1 to set the EXCLUSIVE upper bound to max+1
        //parameterized constructor. We create an OBJECT from the Animal CLASS
        Animal a = new Mammal("dog",strength,health);
        return a;
    }

    public static void Fight(Animal a1, Animal a2){

        //loop as long as both animals are alive
        while(a1.getHealth() > 0 && a2.getHealth() > 0){
            animalAttack(a1, a2);
            if(a2.getHealth() <= 0){
                System.out.println(a2.getType() + " is dead. " + a1.getType() + " won");
            }else{
                animalAttack(a2, a1);
                if(a1.getHealth() <= 0){
                    System.out.println(a1.getType() + " is dead. " + a2.getType() + " won");
                }
            }
        }
    }

    public static void animalAttack(Animal attacker, Animal defender){
        //get a random attack value
        int attack;
        if(attacker instanceof Mammal){
            AnimalAttack a = attacker.detailedAttack();
            attack = a.getDamage();
            System.out.println(ConsoleColors.RED + attacker.getType() + " attacks with " + a.getAttackType()+ ConsoleColors.RESET);
        }else{
            attack = attacker.attack();
        }

        //use getter methods to print stats
        if(defender instanceof Mammal){
            Mammal def = (Mammal) defender;
            System.out.println("Defended!");
            int newAttack = def.defend(attack);
            if(newAttack < attack){
                System.out.println(ConsoleColors.GREEN + "Damage reduced from " + attack + " to " + newAttack + ConsoleColors.RESET);
            }
            attack = newAttack;
        }
        System.out.println(attacker.getType() + " attacks " + defender.getType() +
                " delivering " + attack + " damage");
        //update the animals health with the setter
        defender.setHealth(defender.getHealth() - attack);
        pause(MILLIDELAY);
        System.out.println(defender.getType() + " has " + defender.getHealth() + " health remaining.");
        pause(MILLIDELAY);
    }
    /**
     * helper method to clean up code a bit and pull the try/catch sleep out
     */
    public static void pause(int millis){
        //need a try catch because the sleep method can throw an exception.
        //java will not compile if you do not handle a method that declares it may throw and exception.
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
