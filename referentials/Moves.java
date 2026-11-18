package referentials;

import static referentials.Types.*;

public enum Moves implements Mover {
    /**
     * Move Format:
     * CONSTANT_NAME("Display Name", TYPE, Power)
     */
    // Fire Moves
    EMBER("Ember", FIRE, 40),
    SUN_FLARE("Sun Flare", FIRE, 60),

    // Water Moves
    WATER_GUN("Water Gun", WATER, 40),

    // Grass Moves
    VINE_WHIP("Vine Whip", GRASS, 40),
    SPORE_CLOUD("Spore Cloud", GRASS, 0), // Placeholder

    // Normal Moves
    WING_SLASH("Wing Slash", NORMAL, 35),
    TAIL_SLAP("Tail Slap", NORMAL, 30),
    SMASH("Smash", NORMAL, 45),
    LULLABY("Lullaby", NORMAL, 0), // Placeholder
    ;

    // Usual field declaration and constructor 
    private final String name;
    private final Types type;
    private final int power;

    // Private since no (good) reason to instantiate in runtime
    private Moves(String name, Types type, int power){
        this.name = name;
        this.type = type;
        this.power = power;
    }

    public String getName(){ return name; }
    public Types getType(){ return type; }
    public int getPower(){ return power; }
}
