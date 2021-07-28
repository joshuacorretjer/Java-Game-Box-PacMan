# Java Game Box: Pac-Man

## Objective

The objective of this project is to develop Java Game Box: PacMan a simplified version of
the game of the century Pacman. This project will be worked in teams of two students. Your
partner must be different for each of the three projects.  You should start by studying the
application’s architecture in the base code and learn how to efficiently modify the project to
comply with the minimal requirements. For full credit your team must develop these new
features reusing as little code as possible, avoiding code redundancy and duplication, following
the project’s structure and making the least changes possible.

## Minimal Requirements

Your application must comply with the following requirements:

• Phase 1 (WarmUp): Implement additional minor features.
- Change the “logo” of Pac-Man in the game selection screen for the actual game’s
logo.
- Make the Big Dot “blink” (appear on and off).
- In the MapBuilder change the “Ghost” spawn (under the ghost color condition) for a
new “GhostSpawner” Class which you will create and leave empty for now

• Phase 2: Debugging Commands:
- Implement the keyboard button ‘N’ that gives Pac-Man one extra life.
- Implement the keyboard button ‘P’ that ends Pac-Man’s life.
- Implement the keyboard button ‘C’ that makes the “GhostSpawner” class spawn a
new ghost in its location.

• Phase 3: Implement additional Features:
- Make the Ghosts walk outside the cage, then move in a single direction, and if they
clash against a wall they should change direction to one of the available directions
(paths).
- Make the Ghosts kill Pac-Man on collision.
- If Pac-Man eats a BigDot, the Ghosts should flash between Dark Blue and Cyan,
and should be edible by Pac-Man for x (you define x) amount of seconds. On being
eaten (on collision), the Ghosts should disappear.
- Once Pac-Man loses 3 lives, change to a EndGame State in which the game will
store the score if it’s higher than the current high score.
- Make the spawner spawn 4 ghosts ( with different colors, and speed, on the map, on
top of himself, at the beginning of the game, if one were to die and be teleported to
the spawner, the player will get 500 points, then, the spawner will pick a random
amount of tick to wait for before resending it out.
- Every dot has a 1/30 chance to be a fruit rather than a dot. This fruit will give the
player 120 points instead of the base dot value. (You must have a minimum of 2
randomly selected sprites for the fruit).
*Note as of now the ghost is a duplicate of the player, you'll have to erase things, and
edit others in the ghost class*

### Creative Directions (Optional Ideas):
Each team’s Java Game Box: Pac-Man must be personalized, which means it must be different
from each other’s. Be as creative as possible. Remember that this project will become part of
your student project portfolio. 
