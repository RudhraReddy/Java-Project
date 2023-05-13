# Number Matching

Just a stupid game of memory matching tiles game just filp the tiles and matchthe numbers behind those.

### File description

- GameView.java has the main menu GUI and few stupid GUI code
- MatchValidator.java has the game code contains the grid code

## Things need to be added

### Highist priority

- When game starts need to add Back to home "Back" button
- Need to change the Tiles GUI (Make it look Pretty do as you like)
- Both of these need to be included in test cases

### If you can not priority but helpful

- Resours has "SLU_Logo.png" image can you make it the top window icon
- In MatchValidaor.java seperate GUI and move it to GameView and MatchValidator.java should only have gamelogic
- On the main menu add a toggole button "Infity Mode" by defult its off when toggled on and a level is started then an the game goes on even when all matches are found

  - When a level ends new match starts with new grid
  - The HighScore for this will be the number of time the level was won rather than the moves sore
  - Add new button which will stop the infinity mode and will go to main menu saving the HighScore

- Change the congratulations screen rather than just text the Confetti come in 🎉 - Overlay screen which will say "congratulations" and gives score and HighScore

![Main Menu](app\src\main\resources\Main_Menu.jpg)

![alt text](app\src\main\resources\lvl_view.jpg)
