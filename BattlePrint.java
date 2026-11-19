import referentials.Moves;

public class BattlePrint {
    // displays current round and both creatures status
    public void showStatus(Creature player, Creature rival, int round) {
        System.out.println("\n--- Round " + (round + 1) + " ---");
        printCreature(rival);
        printCreature(player);
    }

    // displays a creatures information and HP bar
    public void printCreature(Creature creature) {
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

        // checks the active StatusState object using instanceof instead of comparing status Strings
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
    public void showMoveMenu(Creature player) {
        System.out.println("What will " + player.getName() + " do?");
        // iterates through moveset defined in creature creation. and potions
        for (int i = 0; i < player.getMoves().length; i++) {
            Moves move = player.getMoves()[i];

            System.out.println("  " + (i + 1) + ". " + move.getName()
                + " (" + move.getType() + ", power " + move.getPower() + ")");
        }
        System.out.println("  4. Use potion (" + player.getPotions() + " left)");
    }

    public void printResult(Creature player, Creature rival) {
            System.out.println();
            printCreature(rival);
            printCreature(player);

            if (player.isFainted()) {
                System.out.println("\n" + player.getName() + " fainted. You lost the battle!");
            } else {
                System.out.println("\n" + rival.getName() + " fainted. You won the battle!");
            }
    }
}