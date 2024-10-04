Feature: Tic Tac Toe game logic

  Scenario: Noughts can win
    Given the game board contains two noughts in a row and one empty cell
    When the getEmptyFieldIndex method is called
    Then the index of the empty cell for winning with noughts is returned

  Scenario: Crosses can win, and they need to be blocked
    Given a playing field contains two consecutive crosses and one empty cell in a winning combination
    When the getEmptyFieldIndex method is called
    Then the index of an empty cell is returned to block the winning of crosses

  Scenario: There is no win, the first empty field is selected
    Given the playing field does not contain winning combinations
    When the getEmptyFieldIndex method is called
    Then the index of the first empty cell in the field is returned

  Scenario: All fields are filled in
    Given all the cells in the field are filled with crosses and noughts
    When the getEmptyFieldIndex method is called
    Then there are no empty cells and return