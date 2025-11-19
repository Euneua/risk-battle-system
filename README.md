# Risk Battle System / Risiko Kampfsystem

A Java implementation of the Risk board game battle system with dice rolling mechanics and win probability calculations.

Ein Java-Programm für das Risiko-Brettspiel Kampfsystem mit Würfelmechanik und Siegwahrscheinlichkeitsberechnung.

---

## Features / Funktionen

### English
- **Standard Risk Battle Rules**: Implements the official Risk game combat rules
- **Variable Dice Rolling**: 
  - Attacker rolls 1-3 dice depending on troop count (2 troops = 1 die, 3 troops = 2 dice, 4+ troops = 3 dice)
  - Defender rolls 1-2 dice depending on troop count (1 troop = 1 die, 2+ troops = 2 dice)
- **Dice Comparison**: Highest vs highest, second-highest vs second-highest
- **Win Probability Calculator**: Monte-Carlo simulation with 10,000 iterations to calculate attack success probability
- **Battle Loop**: Continues until defender reaches 0 troops (attacker wins) or attacker reaches 1 troop (attack fails)
- **Real-time Battle Display**: Shows all dice rolls and comparison results for each round
- **Victory Conditions**: 
  - Attacker wins: All troops except 1 move to conquered territory
  - Defender wins: Attack fails, remaining defenders stay

### Deutsch
- **Standard Risiko-Kampfregeln**: Implementiert die offiziellen Risiko-Kampfregeln
- **Variable Würfelanzahl**:
  - Angreifer würfelt 1-3 Würfel je nach Truppenstärke (2 Truppen = 1 Würfel, 3 Truppen = 2 Würfel, 4+ Truppen = 3 Würfel)
  - Verteidiger würfelt 1-2 Würfel je nach Truppenstärke (1 Truppe = 1 Würfel, 2+ Truppen = 2 Würfel)
- **Würfelvergleich**: Höchster gegen höchsten, zweithöchster gegen zweithöchsten
- **Siegwahrscheinlichkeitsrechner**: Monte-Carlo-Simulation mit 10.000 Iterationen zur Berechnung der Angriffs-Erfolgswahrscheinlichkeit
- **Kampfschleife**: Läuft bis Verteidiger 0 Truppen hat (Angreifer gewinnt) oder Angreifer 1 Truppe hat (Angriff scheitert)
- **Echtzeit-Kampfanzeige**: Zeigt alle Würfelwürfe und Vergleichsergebnisse für jede Runde
- **Siegbedingungen**:
  - Angreifer gewinnt: Alle Truppen bis auf 1 rücken ins eroberte Gebiet vor
  - Verteidiger gewinnt: Angriff scheitert, übrige Verteidiger bleiben

---

## Installation / Installation

### Prerequisites / Voraussetzungen
- Java Development Kit (JDK) 8 or higher / Java Development Kit (JDK) 8 oder höher
- Terminal/Command Line access / Terminal/Kommandozeilen-Zugriff

### Compile / Kompilieren
```bash
javac -d bin src/*.java
```

### Run / Ausführen
```bash
java -cp bin Main
```

---

## Usage / Verwendung

### English
1. **Start the program**: Run `java -cp bin Main`
2. **Enter attacker troops**: Minimum 2 troops required
3. **Enter defender troops**: Minimum 1 troop required
4. **View win probability**: The program calculates and displays your chances
5. **Confirm attack**: Type `y` to proceed or `n` to abort
6. **Watch the battle**: Each round shows:
   - All dice rolls (A-Roll1, A-Roll2, A-Roll3, D-Roll1, D-Roll2)
   - Comparison results
   - Updated troop counts
7. **Battle ends when**:
   - Defender reaches 0 troops → Attacker conquers territory
   - Attacker reaches 1 troop → Attack fails

### Deutsch
1. **Programm starten**: Führe `java -cp bin Main` aus
2. **Angreifer-Truppen eingeben**: Minimum 2 Truppen erforderlich
3. **Verteidiger-Truppen eingeben**: Minimum 1 Truppe erforderlich
4. **Siegwahrscheinlichkeit ansehen**: Das Programm berechnet und zeigt deine Chancen
5. **Angriff bestätigen**: Tippe `y` zum Fortfahren oder `n` zum Abbrechen
6. **Kampf beobachten**: Jede Runde zeigt:
   - Alle Würfelwürfe (A-Roll1, A-Roll2, A-Roll3, D-Roll1, D-Roll2)
   - Vergleichsergebnisse
   - Aktualisierte Truppenzahlen
7. **Kampf endet wenn**:
   - Verteidiger 0 Truppen erreicht → Angreifer erobert Gebiet
   - Angreifer 1 Truppe erreicht → Angriff scheitert

---

## Game Rules / Spielregeln

### English
**Dice Rules:**
- Attacker with 2 troops: rolls 1 die
- Attacker with 3 troops: rolls 2 dice
- Attacker with 4+ troops: rolls 3 dice
- Defender with 1 troop: rolls 1 die
- Defender with 2+ troops: rolls 2 dice

**Comparison Rules:**
- When both have 2+ dice: Compare highest vs highest, then second-highest vs second-highest
- When only one comparison: Compare highest dice
- Defender wins ties (equal dice values)
- Each lost comparison = -1 troop

**Victory:**
- Attacker must reduce defender to 0 troops
- Attacker must maintain at least 2 troops (1 stays behind)

### Deutsch
**Würfelregeln:**
- Angreifer mit 2 Truppen: würfelt 1 Würfel
- Angreifer mit 3 Truppen: würfelt 2 Würfel
- Angreifer mit 4+ Truppen: würfelt 3 Würfel
- Verteidiger mit 1 Truppe: würfelt 1 Würfel
- Verteidiger mit 2+ Truppen: würfelt 2 Würfel

**Vergleichsregeln:**
- Bei beiden 2+ Würfeln: Vergleiche höchsten gegen höchsten, dann zweithöchsten gegen zweithöchsten
- Bei nur einem Vergleich: Vergleiche höchste Würfel
- Verteidiger gewinnt bei Gleichstand (gleiche Würfelwerte)
- Jeder verlorene Vergleich = -1 Truppe

**Sieg:**
- Angreifer muss Verteidiger auf 0 Truppen reduzieren
- Angreifer muss mindestens 2 Truppen behalten (1 bleibt zurück)

---

## Example / Beispiel

```
How many troops attack? 10
How many troops defend? 5

10 Attacker vs 5 Defender
Win probability: 87.3%
Should the attack be executed? (y for yes, n for abort): y

A-Roll1: 4
A-Roll2: 6
A-Roll3: 2
D-Roll1: 3
D-Roll2: 5

Comparison 1 (Highest): Attacker (6) vs Defender (5)
Attacker wins! Defender loses 1 troop.

Comparison 2 (Second highest): Attacker (4) vs Defender (3)
Attacker wins! Defender loses 1 troop.

Attacker: 10
Defender: 3

...

=== BATTLE ENDED ===
Attacker wins and takes over the territory!
9 troops move to the defender's territory.
1 troop remains on the original territory.
```

---

## Technical Details / Technische Details

### English
- **Language**: Java
- **Probability Algorithm**: Monte-Carlo simulation (10,000 iterations)
- **Randomization**: `java.util.Random` for dice rolls
- **Input/Output**: `java.util.Scanner` for console interaction

### Deutsch
- **Sprache**: Java
- **Wahrscheinlichkeitsalgorithmus**: Monte-Carlo-Simulation (10.000 Iterationen)
- **Zufallsgenerierung**: `java.util.Random` für Würfelwürfe
- **Ein-/Ausgabe**: `java.util.Scanner` für Konsoleninteraktion

---

## Project Structure / Projektstruktur

```
Risiko Kampfsystem/
├── src/
│   └── Main.java          # Main program / Hauptprogramm
├── bin/                   # Compiled classes / Kompilierte Klassen
├── .vscode/
│   └── settings.json      # VS Code settings / VS Code Einstellungen
└── README.md              # This file / Diese Datei
```

---

## License / Lizenz

This project is for educational purposes. Risk is a trademark of Hasbro.

Dieses Projekt dient Bildungszwecken. Risiko ist eine Marke von Hasbro.
