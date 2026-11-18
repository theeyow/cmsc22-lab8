// creates a Creature from its name, with its stats and moves already set
import referentials.Moves;
import static referentials.Moves.*;
import static referentials.Types.*;

// creates a Creature from its name, with its stats and moves already set
// uses Factory Pattern to centralize the creation of Creature objects in one class
class CreatureFactory{
    // creates a Creature based on given name
    static Creature create(String name){
        if(name.equals("Sarimanok")){
            // creates Sarimanok's moves with their corresponding power and type
            // EMBER, WING_SLASH, SUN_FLARE
            Moves[] moves = {EMBER, WING_SLASH, SUN_FLARE};
            // creates Sarimanok with its specific stats and moves
            return new Creature(name, FIRE, 90, 15, 14, moves, 2);

        } else if(name.equals("Sirena")){
            // creates Sirena's moves with their corresponding power and type
            Moves[] moves = {WATER_GUN, LULLABY, TAIL_SLAP};
            // creates Sirena with its specific stats and moves
            return new Creature(name, WATER, 100, 13, 11, moves, 2);

        } else if(name.equals("Kapre")){
            // creates Kapre's moves with their corresponding power and type
            Moves[] moves = {VINE_WHIP, SPORE_CLOUD, SMASH};
            // creates Kapre with its specific stats and moves
            return new Creature(name, GRASS, 120, 12, 8, moves, 2);

        }

        // returns null is the given name does not match any available creature
        return null;
    }
}
