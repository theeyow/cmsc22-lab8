import referentials.Types;
import static referentials.Types.*;

public class TypeEffectiveness{

    // ---- helper class: calculates the effectiveness of a move against a target ----
    // WHAT CHANGED? separated the type effectiveness calculations from Battle.java into its own class 
                    // to keep the damage calculation logic organized and avoid repeating the same conditions

    // determines the damage multiplier based on the move's type and target's type
    public static double getMultiplier(Types moveType, Types targetType){
        // FIRE moves deal double damage against GRASS
        if (moveType == FIRE && targetType == GRASS){
            return 2.0;
        } 
        // FIRE moves deal have damage against WATER
        else if (moveType == FIRE && targetType == WATER){
            return 0.5;
        } 
        // WATER moves deal double damage against FIRE
        else if (moveType == WATER && targetType == FIRE){
            return 2.0;
        } 
        // WATER moves deal half damage against GRASS
        else if (moveType == WATER && targetType == GRASS){
            return 0.5;
        } 
        // GRASS moves deal double damage against WATER
        else if (moveType == GRASS && targetType == WATER){
            return 2.0;
        } 
        // GRASS moves deal half damage against FIRE
        else if (moveType == GRASS && targetType == FIRE){
            return 0.5;
        }
        // return normal damage if there is no type advantage or disadvantage
        return 1.0;

    }
}