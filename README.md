
# Flappy Bird 

This project is a simple **Flappy Bird** clone developed using Java Swing. The player controls a bird by pressing the **SPACE** key to make it jump and tries to pass through randomly generated pipes. The game runs in real-time and keeps track of the score.

## Features

- Graphical user interface built with Java Swing
- Real-time animation and physics
- Dynamic pipe generation and collision detection
- Game over and restart functionality
- Score tracking system


## Requirements

- Java Development Kit (JDK) 8 or later
- Java Swing (included with JDK)
- An `assets/` folder containing the following image files:
  - `bird.png`
  - `toppipe.png`
  - `bottompipe.png`
  - `background.png`

## How to Run

1. Make sure Java is installed on your system.
2. Compile and run the program via terminal or any Java IDE:

```bash
javac FlappyBird.java
java FlappyBird
```

3. Game Controls:
   - Press `SPACE` to make the bird jump.
   - If the game is over, press `SPACE` again to restart.

## Folder Structure

```
FlappyBird.java
assets/
├── bird.png
├── toppipe.png
├── bottompipe.png
└── background.png
```

## Notes

- The game window is 800x600 pixels.
- The bird is affected by gravity and continuously falls.
- Pipes are generated at random heights and move from right to left.
- If the bird hits a pipe or touches the ground, the game ends.
