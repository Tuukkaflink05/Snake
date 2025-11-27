# Snake
A classic terminal-style Snake game made by **Tuukka Flink**.

![Gameplay](https://github.com/Tuukkaflink05/Snake/blob/main/pics/snake-gameplay-ezgif.com-resize.gif?raw=true)

## Features

### Smooth, responsive gameplay
- Uses multithreading so **input never blocks gameplay**
- Real-time movement with consistent timing

###  ASCII-styled start screen

![Start Screen](https://github.com/Tuukkaflink05/Snake/blob/main/pics/snake-start-scree.png?raw=true)

###  Highscore system
- Scores persist between runs
- Separate name + score storage
- Automatically updates when a new highscore is reached
- **Can you beat mine?**

![Highscore](https://github.com/Tuukkaflink05/Snake/blob/main/pics/Screenshot%202025-11-27%20191830.png?raw=true)

###  Sound effects + background music
- Gameplay sound effects created by me
- Includes two background songs:
  - Start screen music: [*Waiting Time* by Lesiakower](https://pixabay.com/music/video-games-waiting-time-175800/)

  - “Power-up” theme at score 10: [*Item Obtained* by Lesiakower](https://pixabay.com/music/video-games-item-obtained-123644/)
  - Both from Pixabay

###  Javadoc documentation
Hosted [here](https://tuukkaflink05.github.io/Snake/)
(*still in progress*)

---

# How to Play

### 1. Clone the repository
 `` git clone https://github.com/Tuukkaflink05/Snake.git ``

### 2. change in to the Snake directory

### 3. compile and run
`` javac Main.java && java Main ``

## requirements
- java version **21+**
- sound to play the amazing sound effects


# Note! / Issues
- to change direction you need to press enter after having input a direction input: **W** , **A** , **S** , **D**

- input only one direction change per frame to prevent unintentional death situations



# File Structure
```
Snake/
├── Main.java
├── gameUtils/
│   ├── Game.java
│   ├── Snake.java
│   └── Apple.java
├── utils/
│   ├── highScore.java
│   ├── Sound.java
│   ├── Title.java
│   └── input/
│       ├── InputHandler.java
│       └── StringInputHandler.java
├── sounds/
│   └── *.wav
└── texts/
    ├── title.txt
    └── highScores/
        ├── highScoresName.txt
        ├── highScoresNum.txt
        └── highScoresTitle.txt
```

## to do
- GUI at some point??
    - propalby not

![alt text](https://github.com/Tuukkaflink05/Snake/blob/main/pics/snake-dead.png?raw=true)