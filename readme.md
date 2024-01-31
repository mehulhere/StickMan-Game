# Project Overview

This JavaFX-based game combines platform travel, token collection, character selection, and theme switching to create an engaging gaming experience. The project includes various classes responsible for different aspects of gameplay, such as tokens, themes, characters, hitpoints, game statistics, and game mechanics.

## How To Play

1. **Entering Gameplay:**
   - Click on the "Play" button to enter the gameplay page.

2. **Gameplay Mechanics:**
   - Press the spacebar to extend a stick and release it to stop the extension.
   - The goal is to extend the stick enough to help the player travel across platforms during rotation.
   - Press the spacebar between travels to invert the character and collect tokens.
   - Landing the stick directly in the middle of a platform grants an extra point.

3. **Reviving Character:**
   - If the player dies, they have the option to revive the character using collected hearts.
   - The number of hearts determines how many times the player can revive and continue the game.

4. **Theme and Character Customization:**
   - Choose various themes and character skins from the menu page.

## Main Classes Used

1. **Token Class:**
   - Represents tokens in the game.
   - Methods for generating, removing, and handling tokens.
   - Used for reviving the character.

2. **ThemesController:**
   - Handles the switching of themes in the game.

3. **CharacterController:**
   - Handles the selection of characters in the game.

4. **SceneController:**
   - Parent controller class for all controllers.
   - Manages the transition between different pages.
   - Utilizes the concept of inheritance.

5. **HitPoint Class:**
   - Represents the middle point of the platform where the player earns extra points.

6. **Game Statistics:**
   - Handles game statistics, including scores, tokens, and revivals.
   - Uses serialization to save statistics.

7. **Game Mechanics:**
   - Manages various game mechanics, such as scene transitions, collision detection, and player actions.

8. **Player Class:**
   - Represents the game player.
   - Contains methods and threads for player movements, animations, falling, and invert actions.
   - Interacts with tokens.

9. **Platform Class:**
   - Represents the platforms in the game.
   - Utilizes the Flyweight design pattern for efficient platform object creation.
   - Platforms are generated with random widths and distances.

10. **Stick Class:**
    - Represents the stick used by the player to


### Contributors
1. Mehul Pahuja
2. Aditya Aggarwal

### GitHub Link:
https://github.com/mehulhere/FirstGame

