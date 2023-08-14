
# Command Line Interface Scrabble Game

This project was made as part of the coursework in the Programming in Java module at BBK, University of London.

Technologies Used:
- Java 17

## Gameplay
The first move is done by the human player, by combining two or more of their letters to a word. The word is placed on the board to read either to the right or downwards, with one letter on the centre square. Like in Scrabble, diagonal words are not possible in ScraBBKle. Whenever a tile is placed on a square, the letter and the value of the tile replace the square on the board. If the value S from the intro is an even number, there are four candidates for the role of "centre square". In ScraBBKle, the top left of these four squares (i.e., the one with minimal column and row) is the centre square for the purposes of the first move.

The game computes the score resulting from the move. As long as the tile bag is not empty, the player who just made a move will have their tile rack topped up with tiles taken from the tile bag so that it has 7 tiles again.

The next move is the computer player's. From now on, the computer player and the human player take turns with their moves until one player has no more tiles on their rack or no more moves are possible.

The player whose turn it is adds one or more letters to the letters already on the board to form a new word. All letters played in a move must be placed in one row to the right or downwards and contribute to a new word. It is allowed to skip occupied positions (for example, one may extend NO to SNOW by adding a S at the beginning and at the same time a W at the end). In ScraBBKle, every move may lead to only one occurrence of a new or changed word on the board. In contrast to the original Scrabble game, it is not allowed to place a word at right angles to a word already on the board without an overlap, nor to place a complete word parallel immediately next to a word already played.

Once a tile has been used in a move, its position on the board stays unchanged.

There are special tiles, called wildcards or blanks, where the letter is initially not given, but can be chosen by the player as needed. In our game, the choice is entered as a small letter. As soon as the choice has been made for a given wildcard, it stays fixed for the rest of the game. The value of a wildcard in ScraBBKle is 3. When a wildcard has been played, its letter used for display on the board is the chosen small letter - the choice lasts for the whole game.

A player is allowed to pass the current turn (i.e., not make a move and allow the next player to continue).

In ScraBBKle, it is not allowed to exchange tiles from the tile rack with tiles from the tile bag.

Moves must always lead to words that are in the game's word list. (This is checked by the game, and invalid moves are rejected by the game.)

The game ends when the tile bag is empty and one of the player has an empty tile rack. The game also ends if both players pass twice in a row.

## Scoring
The score for each move is calculated as the sum of the letter values in the word created or modified in the move, plus the extra points obtained (or lost!) from tiles placed on premium squares.

Premium letter squares have an integer number of at least -9 and at most 99 as a factor (specifically, 0 or negative values are possible). When a tile is placed on a premium letter square, the score for the tile is its value multiplied by the factor of the premium letter square. A premium letter square has the shape (x) if the factor x is a single character and the shape (xy if the factor xy has two characters.

Premium word squares also have an integer number of at least -9 and at most 99 as a factor (specifically, 0 or negative values are possible). When a move places a tile on a premium word square, the factor of the premium word square will be multiplied with the score obtained for the word otherwise. If a move uses several premium letter squares, the effect is cumulative (for example, when we use a premium word square with factor 4 and a second premium word square with factor 5 in the same move, the resulting factor for the word score would be 4*5 = 20). Premium word squares are applied only after premium letter squares. A premium word square has the shape {x} if the factor x is a single character and the shape {xy if the factor xy has two characters.

A square cannot be at the same time both a premium letter square and a premium word square. There can also be squares that are not premium letter squares or premium word squares. Such squares are displayed as . (i.e., a space, then a dot, then a space).

Letter and word premium squares are applied only in a single move. As soon as they have been covered by a tile, in later moves this tile will count at its face value (i.e., the score will not be affected by the premium formerly obtained from covering the tile).

Woo-hoo! If a player manages to play all 7 tiles in one move, they are awarded an extra score of 70 points in ScraBBKle. This extra score is added only after all the other calculations for the current move are done (so also with a premium word square involved in the move, the player would still get only 70 extra score points).

At the end of the game, at least one player will have unplayed tiles. Each player's score is reduced by the sum of the values of their own unplayed tiles.

Winning player
The player who has a higher score at the end of the game wins. If the scores are equal, the game is declared a draw.

Example play
For example, after starting the game, the human player may see the following board (here the default board was chosen) and tiles:

    a  b  c  d  e  f  g  h  i  j  k  l  m  n  o 
 1 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}
 2  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . 
 3  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . 
 4 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)
 5  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . 
 6  . (3) .  .  . (3) .  .  . (3) .  .  . (3) . 
 7  .  . (2) .  .  . (2) . (2) .  .  . (2) .  . 
 8 {3} .  . (2) .  .  . {2} .  .  . (2) .  . {3}
 9  .  . (2) .  .  . (2) . (2) .  .  . (2) .  . 
10  . (3) .  .  . (3) .  .  . (3) .  .  . (3) . 
11  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . 
12 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)
13  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . 
14  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . 
15 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}

It's your turn! Your tiles:
[T1], [I1], [U1], [M3], [G2], [R1], [L1]
The human player makes the first move in ScraBBKle. The game asks the human player to enter their move by indicating the word, the position, and the direction of the word, separated by commas. In the above scenario, the human player may type

GIT,f8,r

to indicate that they want to play the word GIT, starting at position f8, and going to the right (here r means right and d means down). If a move is not possible, the game tells the human player about this (ideally with an explanation) and requests another input.

However, the move entered above is possible - the human player has the necessary tiles on the rack, the position and direction are possible as well, and one word from the word list will be created on the board.

As a result, both the scores and the board are updated and displayed:

The move is:    Word: GIT at position f8, direction: right

Human player score:    8
Computer player score: 0


    a  b  c  d  e  f  g  h  i  j  k  l  m  n  o 
 1 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}
 2  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . 
 3  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . 
 4 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)
 5  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . 
 6  . (3) .  .  . (3) .  .  . (3) .  .  . (3) . 
 7  .  . (2) .  .  . (2) . (2) .  .  . (2) .  . 
 8 {3} .  . (2) . G2 I1 T1  .  .  . (2) .  . {3}
 9  .  . (2) .  .  . (2) . (2) .  .  . (2) .  . 
10  . (3) .  .  . (3) .  .  . (3) .  .  . (3) . 
11  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . 
12 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)
13  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . 
14  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . 
15 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}
The human player also receives new tiles from the tile bag.

Now it is the computer player's turn. The computer player's tiles are not visible to the human player. The computer player responds with its own move, which again leads to a valid word from the word list on the board (STAR).

The move is:    Word: SAR at position h7, direction: down
The result is:

Human player score:    8
Computer player score: 4


    a  b  c  d  e  f  g  h  i  j  k  l  m  n  o 
 1 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}
 2  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . 
 3  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . 
 4 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)
 5  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . 
 6  . (3) .  .  . (3) .  .  . (3) .  .  . (3) . 
 7  .  . (2) .  .  . (2)S1 (2) .  .  . (2) .  . 
 8 {3} .  . (2) . G2 I1 T1  .  .  . (2) .  . {3}
 9  .  . (2) .  .  . (2)A1 (2) .  .  . (2) .  . 
10  . (3) .  .  . (3) . R1  . (3) .  .  . (3) . 
11  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . 
12 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)
13  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . 
14  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . 
15 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}

It's your turn! Your tiles:
[T1], [I1], [U1], [M3], [G2], [R1], [L1]
Now it is the human player's turn again to extend the crossword pattern.

The human player, who may be familiar with Scrabble, may be tempted to play

TG,g9,r

as their next move. However, doing so would lead to two words appearing on the board: TAG going to the right from position g9, and IT going down from position g8. In ScraBBKle, this is not allowed, and the game would reject the move.

In ScraBBKle, a move may never lead to more than one word of 2 or more letters being created on the board. (This is different from Scrabble.) The created word must always be in the same direction as the player's move and use all tiles of the move.

## Board files
We need to define a file format for ScraBBKle boards that a user may write with a text editor. We use a plain-text format.

The first line of a file for a ScraBBKle board stores a integer number S between 12 and 26. This number indicates the size of our S x S ScraBBKle board.

After the first line, there are S further lines. Each of these lines consists of exactly S of the following "tokens", each of which represents a square:

. represents a standard square without premium.
(n) represents a letter premium square, where n is an integer between -9 and 99.
{n} represents a word premium square, where n is an integer between -9 and 99.
Note that in our file format, a premium square may need either 3 or 4 characters. Our file format does not allow for spaces.

See the file defaultBoard.txt for an example. A file is valid if it is syntactically correct as specified above.

## Moves
As indicated above, you will need to read moves for the human player.

The pass move is indicated by writing a line that consists of two commas: ,,

A move to play tiles is indicated by writing the wold spelled by the tiles that are played (excluding the tiles already on the board), then a comma, then the position in format cr, then a comma, then the direction.

Here cr indicates the column and row of the "origin" of the move. For example, OND,f8,d says that from the position in column f and row 8 on the board, going down, a tile sequence corresponding to the word OND should be played, where occupied squares are skipped. Another example is VLuE,b12,r, which says that from position b12, going right, a tile sequence corresponding to the word VLUE should be played, where a wildcard should be used for the U on the board (indicated by the use of the lower-case letter u).
