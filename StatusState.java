import java.util.Random;

public interface StatusState{
    int getModifiedSpeed(int speed);
    int getModifiedAttack(int attack);
    boolean canAct(Creature creature, Random random);
    void endOfTurn(Creature creature);
}