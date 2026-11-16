import java.util.Random;
import java.util.Scanner;

public class Main {
    // used to read the users input throughout the game
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // creates a Random object
        // a number after "java Main" repeats the same random choices (see README how to run with fixed seed)
        Random random = (args.length > 0) ? new Random(Long.parseLong(args[0])) : new Random();
        System.out.println("=== MYTHLINGS ===");

        // stores the names of all available creatures
        String[] names = {"Sarimanok", "Sirena", "Kapre"};
        System.out.println("Choose your creature:");
        // displays the available creatures and their basic information
        for (int i = 0; i < names.length; i++) {
            Creature c = CreatureFactory.create(names[i]);
            System.out.println("  " + (i + 1) + ". " + c.getName() + " [" + c.getType() + "] HP " + c.getMaxHp()
                    + ", Attack " + c.getAttack() + ", Speed " + c.getSpeed());
        }
        // creates the player's creature based on the users choice 
        Creature player = CreatureFactory.create(names[askNumber("Choose >> ", names.length) - 1]);

        // randomly selects a rival that is different from the players creature
        Creature rival;
        do {
            rival = CreatureFactory.create(names[random.nextInt(names.length)]);
        } while (rival.getName().equals(player.getName()));

        // stores the available difficulty levels
        String[] levels = {"EASY", "HARD"};
        System.out.println("Choose the rival's difficulty:");
        System.out.println("  1. Easy (picks moves at random)");
        System.out.println("  2. Hard (picks the best move)");
        // gets the difficulty selected by the user
        String difficulty = levels[askNumber("Choose >> ", levels.length) - 1];

        //select the strategy based on the chosen difficulty
        // WHAT CHANGED? applied strategy pattern 
        RivalStrategy rivalStrategy;
        if(difficulty.equals("EASY")){
            rivalStrategy = new EasyStrategy();
        } else {
            rivalStrategy = new HardStrategy();
        }

        // displays the selected creature and its rival
        System.out.println("\nYou chose " + player.getName() + ". A wild " + rival.getName() + " appears!");
        // creates the Battle object and passes the selected strategy and Random object
        Battle battle = new Battle(player, rival, rivalStrategy, random);

        // continuous the battle until either creature faints
        while (!battle.isOver()) {
            battle.showStatus();
            battle.showMoveMenu();
            // gets the player's chosen move or potion
            int choice = askNumber("Choose >> ", 4);
            System.out.println();
            // plays one round using the player's selected action
            battle.playRound(choice - 1);
        }
        // displays the final battle result
        battle.printResult();
    }

    // asks the user for a valid number within the given range
    static int askNumber(String prompt, int max) {
        while (true) {
            System.out.print(prompt);
            // exits the program if there is no more input
            if (!scanner.hasNextLine()) {
                System.out.println("\nGoodbye!");
                System.exit(0);
            }
            try {
                // converts the users input input into an integer
                int number = Integer.parseInt(scanner.nextLine().trim());
                // returns the number if it is within the allowed range
                if (number >= 1 && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // handles invalid input that cannot be converted into an integer
                // prevents the program from crashing if the user enters letters or symbols
                System.out.print("");
            }
            // displays an error message if the input is not a valid number or is outside the allowed range
            System.out.println("Please enter a number from 1 to " + max + ".");
        }
    }
}
