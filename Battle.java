import java.util.Random;
import referentials.Moves;

// plays the rounds, applies moves, types and statuses, and prints the result.
class Battle {
    // stores the two creatures participating in the battle
    private Creature player;
    private Creature rival;

    // WHAT CHANGED? - replaced String difficulty with RivalStrategy to implement the strategy pattern
                  // - allows Battle to use different rival decision making strategies without knowing specific difficulty implementation 
    private RivalStrategy rivalStrategy; 

    private Random random;
    private int round = 0;

    // constructor for initializing the battle properties
    Battle(Creature player, Creature rival, RivalStrategy rivalStrategy, Random random) {
        this.player = player;
        this.rival = rival;
        this.rivalStrategy = rivalStrategy;
        this.random = random;
    }

    // checks whether either creature has fainted
    public boolean isOver() {
        return player.isFainted() || rival.isFainted();
    }

    // displays current round and both creatures status
    public void showStatus() {
        System.out.println("\n--- Round " + (round + 1) + " ---");
        printCreature(rival);
        printCreature(player);
    }

    // displays a creatures information and HP bar
    private void printCreature(Creature creature) {
        // WHAT CHANGED? uses getters instead of directly accessing creature fields because they are now private
        int filled = creature.getHp() * 10 / creature.getMaxHp();

        if (creature.getHp() > 0 && filled == 0) {
            filled = 1;
        }
        String bar = "";
        for (int i = 0; i < 10; i++) {
            bar += (i < filled) ? "#" : "-";
        }

        String statusText = "";

        // WHAT CHANGED?? checks the active StatusState object using instanceof instead of comparing status Strings
        if (creature.getStatus() instanceof BurnedState) {
            statusText = " (burned)";
        } else if (creature.getStatus() instanceof AsleepState) {
            statusText = " (asleep)";
        } else if (creature.getStatus() instanceof ParalyzedState) {
            statusText = " (paralyzed)";
        }
        System.out.println(creature.getName() + " [" + creature.getType() + "] HP " + creature.getHp() + "/" + creature.getMaxHp()
                + " [" + bar + "]" + statusText);
    }

    // displays the players available moves and potion count
    public void showMoveMenu() {
        System.out.println("What will " + player.getName() + " do?");
        // WHAT CHANGED?? uses a list of Move objects instead of a String array
        // each Move object stores its own name, type, and power
        for (int i = 0; i < player.getMoves().length; i++) {
            Moves move = player.getMoves()[i];

            System.out.println("  " + (i + 1) + ". " + move.getName()
                + " (" + move.getType() + ", power " + move.getPower() + ")");
        }
        System.out.println("  4. Use potion (" + player.getPotions() + " left)");
    }

    // choice: 0 to 2 is one of the player's moves, 3 is a potion.
    public void playRound(int choice) {
        round++;

        // gets the rival's action through its assigned strategy
        int rivalChoice = chooseRivalAction();

        // determines which creature acts first based on effective speed
        if (getSpeed(player) >= getSpeed(rival)) {
            takeAction(player, rival, choice);
            if (!isOver()) {
                takeAction(rival, player, rivalChoice);
            }
        } else {
            takeAction(rival, player, rivalChoice);
            if (!isOver()) {
                takeAction(player, rival, choice);
            }
        }
        // applies each creature's end-of-turn status effect
        if (!isOver()) {
            endOfTurn(player);
            endOfTurn(rival);
        }
    }

    // displays the final battle result
    public void printResult() {
        System.out.println();
        printCreature(rival);
        printCreature(player);

        if (player.isFainted()) {
            System.out.println("\n" + player.getName() + " fainted. You lost the battle!");
        } else {
            System.out.println("\n" + rival.getName() + " fainted. You won the battle!");
        }
    }

    // handles the action selected by a creature
    private void takeAction(Creature actor, Creature target, int choice) {
        // WHAT CHANGED? delegates the canAct check to the active StatusState through Creature instead of checking each status in Battle
        if (!actor.canAct(random)) {
            return;
        }
        if (choice == 3) {
            usePotion(actor);
        } else {
            useMove(actor, target, actor.getMoves()[choice]);
        }
    }

    // handles potion usage
    private void usePotion(Creature creature) {
        // WHAT CHANGED? potion usage is the handled by Creature's usePotion() method instead of directly 
            // modifying its HP and potion count here
        if (!creature.usePotion()) {
            System.out.println(creature.getName() + " has no potions left!");
            return;
        }

        System.out.println(creature.getName() + " used a potion and recovered HP. (" + creature.getPotions() + " left)");
    }

    // handles move execution and damage calculation
    private void useMove(Creature attacker, Creature target, Moves move) {
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
        applyEffect(move, target);
        if (target.isFainted()) {
            System.out.println(target.getName() + " fainted!");
        }
    }

    // apply the status effect of a move
    private void applyEffect(Moves move, Creature target) {
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

    // WHAT CHANGED? delegates end of turn behavior to the active StatusState through Creature instead of handling each status on Battle
    private void endOfTurn(Creature creature) {
        creature.endOfTurn();
    }

    // gets the creature's effective speed after applying its current status
    private int getSpeed(Creature creature) {
        return creature.getEffectiveSpeed();
    }

    // WHAT CHANGED? removed the difficulty based if else logic from Battle
    // the selected RivalStategy now decides which action the rival performs
    // follows strategy pattern
    private int chooseRivalAction() {
        return rivalStrategy.chooseAction(rival, player, random);
    }

}
