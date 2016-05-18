package ms.game.battleship;

import java.util.*;

public class BattleShipRunner {
    // declare and intitialize the variable we'll neet
    private GameHelper helper = new GameHelper();
    private ArrayList<BattleShip> battleShipList = new ArrayList<BattleShip>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        // first make some dot coms and give them locations
        // make three BattleShip objects, give'em names, and stick'em in the ArrayList
        BattleShip one = new BattleShip();
        one.setName("Pets.com");
        BattleShip two = new BattleShip();
        two.setName("eToys.com");
        BattleShip three = new BattleShip();
        three.setName("Go2.com");
        battleShipList.add(one);
        battleShipList.add(two);
        battleShipList.add(three);
        // print brief instructions for user
        System.out.println("Your goal is to sink three battleships.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for (BattleShip battleShip : battleShipList) {
            // repeat with each BattleShip in the list
            ArrayList<String> newLocation = helper.placeDotCom(3);
            // ask the helper for a BattleShip location
            battleShip.setLocationCells(newLocation);
//            System.out.println("Pets.com is here," + newLocation);
            // call the setter method on this BattleShip to give it the location you just got from the helper
        } // close for loop
    } // close setUpGame method

    public void startPlaying() {
        while (!battleShipList.isEmpty()) {
            // as long as the BattleShip list is Not empty
            String userGuess = helper.getUserInput("Enter a guess");
            // get the user input
            checkUserGuess(userGuess);  // call our own checkUserGuess method
        } // close while
        finishGame();
        // call our own finishGame method
    } // close method

    public void checkUserGuess(String userGuess) {
        // find out if there's a hit (and kill on any BattleShip)

        numOfGuesses++;
        // increment the number of guesses the user has made
        String result = "miss";
        // assume its a 'miss', unless told otherwise

        for (int x = 0; x < battleShipList.size(); x++) {
            // repeat with all DotComs in the list
            result = battleShipList.get(x).checkYourself(userGuess);
            // ask the BattleShip to check the user guess, looking for a hit (or kill)
            if (result.equals("hit")) {
                break;
                // get out of the loop early, no point in testing the others
            }
            if (result.equals("kill")) {
                battleShipList.remove(x);
                // this guy's dead, so take him out of the DotComs list then get out of the loop
                // break;
            }
        } // close for
        System.out.println(result); // print the result for the user
    } // close method

    private void finishGame() {
        // print a message telling the user how he/she did in the game
        System.out.println("All Dot Coms are dead! Your stock is now worthless.");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println(" You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }// close method

    public static void main(String[] args) {
        BattleShipRunner game = new BattleShipRunner();
        //create the game object
        game.setUpGame();

//        for (BattleShip battleShip : game.battleShipList) {
//            System.out.println(battleShip.locationCells);
//        }

        // tell the game object to set up the game
        game.startPlaying();
        // tell the game object to start the main game play loop (keeps asking for user input and checking the guess)
    }
}