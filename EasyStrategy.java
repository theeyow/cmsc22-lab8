import java.util.Random;

// ---- applies strategy pattern: easy difficulty behavior ----
// WHAT CHANGED? separated the rival's random move selection from Battle.java into its own strategy class
// allows Battle to use different decision making strategies without containing difficulty specific logic

public class EasyStrategy implements RivalStrategy{
    @Override
    public int chooseAction(Creature rival, Creature player, Random random){
        // randomly selects one of the rival's available moves
        // nextInt() generates an index from 0 up to (but not including) the number of moves
        return random.nextInt(rival.getMoves().length);

    }

}