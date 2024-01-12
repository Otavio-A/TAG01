\## POO\_G01 - Mario Bros

## POO_G1 - Mario Bros
The project is a demake of the classic arcade game Mario Bros!
The Mario bros must clean the sewers of monsters! Monsters appear from the upper pipes and move all the down to the bottom pipes.

You can defeat the monsters by jumping on the floor bellow them, tripping the monsters allowing you to run in and finish them! Every monster defeated grants you points!

The level ends when all monsters are defeated.

Controls:<br>
Mario
- Left arrow Move Left
- Right arrow Move Right
- Up arrow Jump

Luigi:
- A move left
- D move right
- W Jump

  <br>
![img](https://github.com/Otavio-A/TAG01/blob/master/Docs/Mocks/Arena.png)
![img](https://github.com/Otavio-A/TAG01/blob/master/Docs/Mocks/IMG_0935.jpeg)


- Members
  - Otávio A. Araújo A042508@umaia.pt
  - Diogo Soares Gonçalves A042244@umaia.pt
  - Gonçalo Caridade Ribeiro A043540@umaia.pt

### IMPLEMENTED FEATURES

- \*\*Jumping\*\* - The game character will jump when the up arrow key is pressed.
- \*\*Gravity\*\* - Both players and monsters will fall if they are not standing on a platform
- \*\*Defeat Monsters\*\* - To defeat a monster you must jump bellow them to trip the monster, allowing you to "kick them" by running at them.
- \*\*POW Block\*\* - You can use the Pow block to help you when you are overwhelmed by monsters! When you jump on the pow block all monsters standing on platforms get knocked down!
- \*\*COOP\*\* - Play as Mario or as Luigi!

### DESIGN


------

#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE

------

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

- Arena | Large Class
	- The arena class ended up with the majority of methods turning it into a very complex class to both test and work in.
	- A possible solution would be the **[Extract Class](https://refactoring.guru/extract-class)** technique, 


- Methods using platform positions (hitPlat, is plat, etc...)| [Long Method](https://refactoring.guru/smells/long-method)
	- Methods that check for positions of platforms ended with an excessive use of ifs and repeated coordinates that would hinder the creation of additional platforms or different levels.
	- [Introduce Parameter Object](https://refactoring.guru/introduce-parameter-object) would be a very reasonable solution for the repeated positions of platforms.
------



### TESTING
![img](https://github.com/Otavio-A/TAG01/blob/master/Docs/Mocks/testCoverage.png)
	

### SELF-EVALUATION
  - Otávio A. Araújo A042508@umaia.pt 34%
  - Diogo Soares Gonçalves A042244@umaia.pt 33%
  - Gonçalo Caridade Ribeiro A043540@umaia.pt 33%
