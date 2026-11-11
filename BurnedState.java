import java.util.Random;

public class BurnedState implements StatusState{
    @Override
    public int getModifiedSpeed(int speed){
        return speed;
    }

    @Override
    public int getModifiedAttack(int attack){
        return attack / 2;
    }

    @Override
    public boolean canAct(Creature creature, Random random){
        return true;
    }

    @Override
    public void endOfTurn(Creature creature){
        creature.takeDamage(6);
        System.out.println(creature.getName() + " is hurt by its burn. (-6 HP)");
        if(creature.isFainted()){
            System.out.println(creature.getName() + " fainted!");
        }
    }
}