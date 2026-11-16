import java.util.Random;

// ---- apply strategy pattern: defined the rival's decision making behavior ----
// WHAT CHANGED? created a common interface for the rival's different difficult strategies (easy and hard)
// allows Battle.java to use different strategies without depending on their specific implementations

public interface RivalStrategy{
    // defines method that each rival strategy must implement
    // returns action chosen by the rival
    int chooseAction(Creature rival, Creature player, Random random);
}