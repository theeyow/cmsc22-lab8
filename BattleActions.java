import java.util.Random;

import referentials.Moves;

public class BattleActions {
    protected void usePotion(Creature creature) {
        // WHAT CHANGED? potion usage is the handled by Creature's usePotion() method instead of directly 
            // modifying its HP and potion count here
        if (!creature.usePotion()) {
            System.out.println(creature.getName() + " has no potions left!");
            return;
        }

        System.out.println(creature.getName() + " used a potion and recovered HP. (" + creature.getPotions() + " left)");
    }

    protected void useMove(Creature attacker, Creature target, Moves move, Random random) {
        System.out.println(attacker.getName() + " used " + move.getName() + "!");
        int power = move.getPower();
        if (power > 0) {
            // WHAT CHANGED? gets the move's power and type directly from the Move object instead of using separate helper method inside the Battle
            double multiplier = TypeEffectiveness.getMultiplier(move.getType(), target.getType());
            // gets the attack value after applying the current status effect
            int attackStat = attacker.getEffectiveAttack();

            //damage = power x attack / 30, adjusted by type effectiveness
            int damage = (int)(power * (double)attackStat / 30 * multiplier);

            target.takeDamage(damage);

            System.out.println("  It dealt " + damage + " damage.");
                
            if (multiplier > 1) {
                System.out.println("  It's super effective!");
            } else if (multiplier < 1) {
                System.out.println("  It's not very effective...");
            }
        }
        applyEffect(move, target, random);
        if (target.isFainted()) {
            System.out.println(target.getName() + " fainted!");
        }
    }
    
    private void applyEffect(Moves move, Creature target, Random random) {
        // only applies an effect if the target has not fainted and is currently in its NormalState
        if (target.isFainted() || !(target.getStatus() instanceof NormalState)) {
            return;
        }
        // ember has a 30% chance to burn. Lullaby and Spore Cloud always work on a creature with no status.
        if (move.getName().equals("Ember") && random.nextInt(100) < 30) {
            target.setStatus(new BurnedState());
            System.out.println("  " + target.getName() + " was burned!");
        } else if (move.getName().equals("Lullaby")) {
            target.setStatus(new AsleepState());
            System.out.println("  " + target.getName() + " fell asleep!");
        } else if (move.getName().equals("Spore Cloud")) {
            target.setStatus(new ParalyzedState());
            System.out.println("  " + target.getName() + " is paralyzed!");
        }
    }
}
