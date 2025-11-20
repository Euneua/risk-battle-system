A Copy of my post on [dev.to](https://dev.to/euneua/building-a-dice-battle-simulator-when-board-games-meet-monte-carlo-56bl)

## Building a Dice Battle Simulator with Monte Carlo Analysis in Java

Ever wondered what your actual chances are in dice-based combat systems? I built a simple Java program to find out, and the results were a big surprise.

> **Note:** This battle system is similar to the dice comparison mechanics used in the board game Risk (a trademark of Hasbro). This is purely an independent interpretation and not an official use of the trademark.

## The Problem

In this dice battle system, battles are decided by dice rolls with some interesting rules:
- **One attacker must always stay behind** - if you have 5 troops, only 4 actually attack
- Attacker rolls 1-3 dice (based on attacking troop count)
- Defender rolls 1-2 dice (based on troop count)
- Highest dice are compared
- **Defender wins ties** (this is crucial!)

Simple question: If I have f.e. 5 attackers (4 real Attackers) vs 3 defenders, what are my actual chances of winning? 
Answer: ~65%

Why this is the probability, I'll explain in this post.

## Why Monte Carlo?

Instead of calculating exact probabilities (which gets complex fast), I used Monte Carlo simulation: just run the battle thousands of times and count how often you win. Simple, effective, and surprisingly accurate with enough iterations.

The approach:
1. Simulate one complete battle
2. Repeat 10,000 times
3. Calculate win percentage

## The Code

### Rolling Dice
```java
public static int rollDice() {
    Random random = new Random();
    return random.nextInt(6) + 1;
}
```
Nothing fancy - just a standard 6-sided die.

**Monte Carlo Simulation**
```java
public static double calculateWinProbability(int attacker, int defender) {
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
```

This runs 10,000 complete battles and returns the win percentage. Why 10,000? It's a good balance between accuracy and speed - results stabilize around this number.

### Simulating One Battle
```java
public static boolean simulateBattle(int attacker, int defender) {
    while (attacker > 1 && defender > 0) {
        int aRoll1 = 0, aRoll2 = 0, aRoll3 = 0;
        int dRoll1 = 0, dRoll2 = 0;
        
        // Attacker rolls based on troop count
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
        
        // Defender rolls based on troop count
        if (defender == 1) {
            dRoll1 = rollDice();
        } else if (defender >= 2) {
            dRoll1 = rollDice();
            dRoll2 = rollDice();
        }
        
        // Compare and resolve...
    }
    
    return defender == 0;
}
```

The key insight: **number of dice depends on troop count**. Remember, one attacker must stay behind, so with 2 total attackers only 1 actually attacks (rolling 1 die). With 4+ attackers, 3 attack (rolling 3 dice). This limitation matters more than you'd think.

### The Comparison Logic

This is where it gets interesting. When both sides roll multiple dice, you compare the highest rolls:

```java
if (attacker >= 3 && defender >= 2) {
    // Sort attacker's dice to find highest
    int[] aRolls = {aRoll1, aRoll2, aRoll3};
    java.util.Arrays.sort(aRolls);
    int aHighest = aRolls[2];
    int aSecondHighest = aRolls[1];
    
    // Find defender's highest and lowest
    int dHighest = Math.max(dRoll1, dRoll2);
    int dLowest = Math.min(dRoll1, dRoll2);
    
    // First comparison
    if (aHighest > dHighest) {
        defender--;
    } else {
        attacker--;  // Defender wins ties!
    }
    
    // Second comparison
    if (aSecondHighest > dLowest) {
        defender--;
    } else {
        attacker--;
    }
}
```

The **"defender wins ties"** rule has a massive impact. Even rolling the same number means the attacker loses a troop.

## What I Learned

1. **Monte Carlo simulation is surprisingly simple to implement** - Just 15 lines of code for the probability calculator, yet it delivers reliable results. No complex math formulas needed.

2. **The defender advantage is stronger than it looks** - That "defender wins ties" rule compounds quickly. Even with a 2:1 numerical advantage (3 attackers (2 real attackers) vs 1 defender), you only win about 76% of the time. With closer numbers like 3 vs 2, your odds drop to around 37%. Most people underestimate this.

3. **Clean code matters more than comments** - Self-documenting code with clear variable names (like `aHighest` and `dLowest`) beats excessive comments. Over-commenting can actually make code look less professional.

4. **Small projects can teach big concepts** - This is only ~230 lines, but it covers probability theory, simulation techniques, game mechanics analysis, array manipulation, and sorting algorithms. Perfect learning project.

## The Results

Some interesting probabilities I discovered:
- 2 vs 1: ~42%
- 3 vs 1: ~76%
- 3 vs 2: ~37%
- 5 vs 3: ~65%
- 10 vs 10: ~48%

Never attack with equal numbers. You need a significant numerical advantage to have good odds.

## What's Next?

Some ideas for enhancements:
- Visualize probability distributions with graphs
- Compare different battle systems from other games
- Build a web interface instead of console
- Calculate exact probabilities mathematically and compare with Monte Carlo results

## Check It Out

The full code is on GitHub: [risk-battle-system](https://github.com/Euneua/dice-battle-system)

Pure Java, no dependencies, ready to run. Clone it, test different scenarios, and see for yourself why defending is so powerful in dice battle systems.

---

What do you think? Have you ever been surprised by battle outcomes in dice-based games? Got ideas for improvements or other games to analyze? Drop a comment below - I'd love to hear your thoughts and feedback!
