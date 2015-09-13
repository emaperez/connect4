# Connect4 game for deviget

This is a Connect 4 game created as an assignment for Deviget. 
It was created in Java using Spring MVC and a little bit of javascript. 

It is a 2 player game. Player 1 accesses the game at: http://default-environment-ffw2spmwv6.elasticbeanstalk.com/game/1
and player 2 at: http://default-environment-ffw2spmwv6.elasticbeanstalk.com/game/2 and both can begin playing
Starting player 1, each player makes a move, putting a piece in the board, the other player board is automatically refreshed to receive the move on the opponent. The game continues until one of the two players has connected 4 pieces of the same color in a row, column, diagonal or there are no more empty spaces in the board (draw)
The board is like a matrix/table, so to put a piece on the board the player needs to click on the exact position where the piece is going to be placed. If the player clicks on an invalid position the piece is not placed on the board. 
After the game is finished, no more pieces can be added and one of the players needs to click on new game, so that the board is refreshed.
The game is functional and fully working.

Given the time restrictions a few this were not possible to do. 
So a list of  game improvements should be:

- The UI is very very simple. Not much time was devoted to it. Functional requirements were prioritized. So a lot can be made here.
- Placing pieces requires the player to click on the specific position. May be a better approach can be to click on the column and the piece to "fall" until the correct row. 

Technical improvements"

- The css included is very raw. It can be refactored and embellished.
- The css is contained inside the jsp file. It should be extracted an placed in a separate file. I created a empty connect4.css but didn't manage to finish it. Also, using less instead of css can be a good option. 
- The front end uses plain javascript + jquery. A javascript framework could have been used to structure the code better. 
- Adding comments, javadocs + code refactoring.
- etc


Thanks

