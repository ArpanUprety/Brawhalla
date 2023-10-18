import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalBrawl {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Animal> animals; // says we will have a list of animal, but it doesn't exist yet
    // we cannot add elements yet

    /**
     * Drives the program
     * User will add animals until done
     * Animals will fight uf there's more than one
     * Winner will be the last one still alive
     */
    private static final int MILIDELAY = 500;
    public static void main(String[] args) {
        animals = new ArrayList<>(); /* This is New! - Instantiate the list!*/
      String  userIn ;
        do {
            System.out.print("Enter A to add an animal, M to add a mammal, and B to start the brawl -> ");
            userIn = input.nextLine().trim().toUpperCase();
            // creates the appropriate object `and add it to the list based on user input( A/M)
            if (userIn.equals("A")) {
                addAnimal();
            } else if (userIn.equals("M")) {
                addMammal();
            }

        }while (!userIn.equals("B")); /* NEW - use string.equals for string comparisons  */
          //make sure the list has at least 2 animals in it. If it doesn't report that
        // there are not enough animals for battle
        if(animals.size() < 2){
            System.out.println("There are not enough animals for the brawl!");
        } else{
            PrintRoster();
            brawl();
    System.out.println(ConsoleColors.YELLOW + animals.get(0).getType() + " IS THE LAST ONE STANDING!!");
        };//NEW - listname.size() tells how many elements there are
    }





    public static void PrintRoster(){
        /*NEW ForEach loop */
        // ForEach loop loops through each object in an array

        //loop through the animal list and print each animal out
        for(Animal a :animals) { // This is  foreach . Animal a is the element we are focused on as we iterate through the list

            System.out.println(a.toString());
        }
    }
    /**
     *
     *

     * Note: addAnimal is OVERLOADED because there are two methods with the same name
     * but different signatures
     * @param isMammal
     */

    public static void brawl(){
       while (animals.size() > 1){
           // select a random index for the attacker
           int attacker = ThreadLocalRandom.current().nextInt(animals.size());
           int defender;
           do {
               defender = ThreadLocalRandom.current().nextInt(animals.size());
           } while (defender == attacker);
           AnimalArena.animalAttack(animals.get(attacker), animals.get(defender));
           if(animals.get(defender).getHealth() == 0)
           { System.out.println(ConsoleColors.RED + animals.get(defender).getType() + " is dead." + ConsoleColors.RESET);
               animals.remove(defender); } /* NEW */ // remove an element from a list with listname.remove(index)
        }
//select a random animal to be the attacker
        // select a random animal to be the defender
        //attack
        //if the defender has no health left
        //report it and remove the defender from the list
        //until all but one 1 animals die

    }
    public static void addAnimal(boolean isMammal){
        System.out.print("Please enter the species: ");
        String type = input.nextLine();
        System.out.print("Please enter the strength( invalid values will be treated as ) -> ");
        String Userinput = input.nextLine();
        int strength = Utilities.parseInt(Userinput,1);
        System.out.print("Please enter the strength( invalid values will be treated as ) -> ");
         Userinput = input.nextLine();
        int health = Utilities.parseInt(Userinput,1);
        Animal a;
        //IF isMammal is true, make a mammal, otherwise make a = an animal
        if (isMammal){
          a = new Mammal(type,strength,health);
        }else {
             a = new Animal(type,strength,health);
        }
       animals.add(a); /* NEW use listname.add(); to add name to list */
    }

    public static void addAnimal(){
        System.out.println("Creating an Animal");
        addAnimal(false);
    }

    public static void addMammal(){
        System.out.println("Creating a Mammal");
        addAnimal(true);
    }
}
