import java.util.Random;
/**
 * interface allows different 
 * 
 * 
 * 
 * 
 **/
// allows each states to have its own behavior while Creature interacts with them through the same interface

public interface StatusState{
    // returns the creature's speed after applying the current state's effect
    int getModifiedSpeed(int speed);
    // returns the creature's attack after applying the current state's effect
    double getModifiedAttack(int attack);
    // checks whether the creature is allowed to perform its action
    boolean canAct(Creature creature, Random random);
    // applies any effects that happen at the end of the creature's turn
    void endOfTurn(Creature creature);
}