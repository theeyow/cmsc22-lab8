import java.util.Random;

// ---- applied strategy pattern: hard difficulty behavior ----
// WHAT CHANGED? separated rival's decision making logic from Battle.java into its own strategy class
// allows Battle to use different decision making strategies without containing difficulty specific logic

public class HardStrategy implements RivalStrategy{
    @Override
    public int chooseAction(Creature rival, Creature player, Random random){
        // use a potion if the rival's HP is below 30 and it still has potions
        if (rival.getHp() < 30 && rival.getPotions() > 0){
            return 3;       // action 3 represents using a potion
        }

        int bestMove = 0;
        double highestScore = -1;

        // examine all available moves to find the one with the highest score
        for(int i = 0; i < rival.getMoves().size(); i++){
            Move move = rival.getMoves().get(i);

            //calculate move's score based on its power and type effectiveness
            double score = move.getPower() * TypeEffectiveness.getMultiplier(move.getType(), player.getType());

            //give status moves a score of 25 if the player has no active status effect
            if (move.getPower() == 0){
                if(player.getStatus() instanceof NormalState){
                    score = 25;
                } else {
                    score = 0;
                }
            }

            // update the best move if the current move has a higher score
            if (score > highestScore){
                highestScore = score;
                bestMove = i;
            }
        }

        // return the index of the move with the highest score
        return bestMove;

    }
}

    