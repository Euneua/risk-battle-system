import java.util.Scanner;
import java.util.Random;

public class Main {
    
    public static int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;  // Number between 1 and 6
    }
    
    public static double calculateWinProbability(int attacker, int defender) {
        // Monte-Carlo simulation
        if (attacker <= 1) return 0.0;
        if (defender == 0) return 100.0;
        
        int simulations = 10000;
        int wins = 0;
        
        for (int i = 0; i < simulations; i++) {
            if (simulateBattle(attacker, defender)) {
                wins++;
            }
        }
        
        return (double) wins / simulations * 100.0;
    }
    
    public static boolean simulateBattle(int attacker, int defender) {
        // Simulate a complete battle
        while (attacker > 1 && defender > 0) {
            int aRoll1 = 0, aRoll2 = 0, aRoll3 = 0;
            int dRoll1 = 0, dRoll2 = 0;
            
            // Attacker rolls
            if (attacker == 2) {
                aRoll1 = rollDice();
            } else if (attacker == 3) {
                aRoll1 = rollDice();
                aRoll2 = rollDice();
            } else if (attacker >= 4) {
                aRoll1 = rollDice();
                aRoll2 = rollDice();
                aRoll3 = rollDice();
            }
            
            // Defender rolls
            if (defender == 1) {
                dRoll1 = rollDice();
            } else if (defender >= 2) {
                dRoll1 = rollDice();
                dRoll2 = rollDice();
            }
            
            // Comparison
            if (attacker >= 3 && defender >= 2) {
                // Both have 2+ dice
                int[] aRolls = {aRoll1, aRoll2, aRoll3};
                java.util.Arrays.sort(aRolls);
                int aHighest = aRolls[2];
                int aSecondHighest = aRolls[1];
                
                int dHighest = Math.max(dRoll1, dRoll2);
                int dLowest = Math.min(dRoll1, dRoll2);
                
                // Comparison 1
                if (aHighest > dHighest) {
                    defender--;
                } else {
                    attacker--;
                }
                
                // Comparison 2
                if (aSecondHighest > dLowest) {
                    defender--;
                } else {
                    attacker--;
                }
            } else {
                // Only one comparison
                int aHighest = Math.max(Math.max(aRoll1, aRoll2), aRoll3);
                int dHighest = Math.max(dRoll1, dRoll2);
                
                if (aHighest > dHighest) {
                    defender--;
                } else {
                    attacker--;
                }
            }
        }
        
        return defender == 0;
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("How many troops attack? ");
            int attacker = scanner.nextInt();
            
            if (attacker < 2) {
                System.out.println("Attack is not possible.");
                return;
            }
            
            System.out.print("How many troops defend? ");
            int defender = scanner.nextInt();
            
            if (defender < 1) {
                System.out.println("Defender must be at least 1.");
                return;
            }
            
            System.out.println("\n" + attacker + " Attacker vs " + defender + " Defender");
            
            // Calculate win probability
            double winProbability = calculateWinProbability(attacker, defender);
            System.out.printf("Win probability: %.1f%%\n", winProbability);
            
            System.out.print("Should the attack be executed? (y for yes, n for abort): ");
            String answer = scanner.next();
            
            if (answer.equals("n")) {
                System.out.println("Attack aborted.");
                return;
            }
            
            // Battle loop
            while (attacker > 1 && defender > 0) {
                int aRoll1 = 0;
                int aRoll2 = 0;
                int aRoll3 = 0;
                int dRoll1 = 0;
                int dRoll2 = 0;
                
                // Attacker rolls
                if (attacker == 2) {
                    aRoll1 = rollDice();
                } else if (attacker == 3) {
                    aRoll1 = rollDice();
                    aRoll2 = rollDice();
                } else if (attacker >= 4) {
                    aRoll1 = rollDice();
                    aRoll2 = rollDice();
                    aRoll3 = rollDice();
                }
                
                // Defender rolls
                if (defender == 1) {
                    dRoll1 = rollDice();
                } else if (defender >= 2) {
                    dRoll1 = rollDice();
                    dRoll2 = rollDice();
                }
                
                System.out.println("\nA-Roll1: " + aRoll1);
                System.out.println("A-Roll2: " + aRoll2);
                System.out.println("A-Roll3: " + aRoll3);
                System.out.println("D-Roll1: " + dRoll1);
                System.out.println("D-Roll2: " + dRoll2);
                
                // Comparison
                if (attacker >= 3 && defender >= 2) {
                    int[] aRolls = {aRoll1, aRoll2, aRoll3};
                    java.util.Arrays.sort(aRolls);
                    int aHighest = aRolls[2];
                    int aSecondHighest = aRolls[1];
                    
                    int dHighest = Math.max(dRoll1, dRoll2);
                    int dLowest = Math.min(dRoll1, dRoll2);
                    
                    System.out.println("\nComparison 1 (Highest): Attacker (" + aHighest + ") vs Defender (" + dHighest + ")");
                    if (aHighest > dHighest) {
                        defender--;
                        System.out.println("Attacker wins! Defender loses 1 troop.");
                    } else {
                        attacker--;
                        System.out.println("Defender wins! Attacker loses 1 troop.");
                    }
                    
                    System.out.println("\nComparison 2 (Second highest): Attacker (" + aSecondHighest + ") vs Defender (" + dLowest + ")");
                    if (aSecondHighest > dLowest) {
                        defender--;
                        System.out.println("Attacker wins! Defender loses 1 troop.");
                    } else {
                        attacker--;
                        System.out.println("Defender wins! Attacker loses 1 troop.");
                    }
                    
                    System.out.println("\nAttacker: " + attacker);
                    System.out.println("Defender: " + defender);
                } else {
                    int aHighest = Math.max(Math.max(aRoll1, aRoll2), aRoll3);
                    int dHighest = Math.max(dRoll1, dRoll2);
                    
                    System.out.println("\nComparison: Attacker (" + aHighest + ") vs Defender (" + dHighest + ")");
                    
                    if (aHighest > dHighest) {
                        defender--;
                        System.out.println("Attacker wins! Defender loses 1 troop.");
                    } else {
                        attacker--;
                        System.out.println("Defender wins! Attacker loses 1 troop.");
                    }
                    
                    System.out.println("\nAttacker: " + attacker);
                    System.out.println("Defender: " + defender);
                }
            }
            
            // Final result
            System.out.println("\n=== BATTLE ENDED ===");
            if (defender == 0) {
                System.out.println("Attacker wins and takes over the territory!");
                System.out.println((attacker - 1) + " troops move to the defender's territory.");
                System.out.println("1 troop remains on the original territory.");
            } else {
                System.out.println("The attack has failed!");
                System.out.println("Remaining defenders: " + defender);
            }
        } // scanner wird automatisch geschlossen
    }
}
