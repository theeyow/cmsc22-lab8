// creates a Creature from its name, with its stats and moves already set
import java.util.Arrays;
import java.util.List;

// creates a Creature from its name, with its stats and moves already set
// uses Factory Pattern to centralize the creation of Creature objects in one class
class CreatureFactory{
    // creates a Creature based on given name
    static Creature create(String name){
        if(name.equals("Sarimanok")){
            // creates Sarimanok's moves with their corresponding power and type
            List<Move> moves = Arrays.asList(
                new Move("Ember", 40, "FIRE"),
                new Move("Wing Slash", 35, "NORMAL"),
                new Move("Sun Flare", 60, "FIRE")
            );
            // creates Sarimanok with its specific stats and moves
            return new Creature("Sarimanok", "FIRE", 90, 15, 14, moves, 2);

        } else if(name.equals("Sirena")){
            // creates Sirena's moves with their corresponding power and type
            List<Move> moves = Arrays.asList(
                new Move("Water Gun", 40, "WATER"),
                new Move("Lullaby", 0, "WATER"), 
                new Move("Tail Slap", 30, "NORMAL")
            );
            // creates Sirena with its specific stats and moves
            return new Creature("Sirena", "WATER", 100, 13, 11, moves, 2);

        } else if(name.equals("Kapre")){
            // creates Kapre's moves with their corresponding power and type
            List<Move> moves = Arrays.asList(
                new Move("Vine Whip", 40, "GRASS"),
                new Move("Spore Cloud", 0, "GRASS"),
                new Move("Smash", 45, "NORMAL")
            );
            // creates Kapre with its specific stats and moves
            return new Creature("Kapre", "GRASS", 120, 12, 8, moves, 2);

        }

        // returns null is the given name does not match any available creature
        return null;
    }
}
