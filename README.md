# Tic-Tac-Toe
A modular tic tac toe game.

# How to run
The Application class contains the main method that one day will work. It relies on the UI, which is non-functional AND works on a small library that's not included in this repository.
The Test class (package: com.brandon.tictactoe.test) contains a main method and helper methods to get started.

# Players
The player interface is used to seperate the move-generation code from everything else. 
* LocalPlayer is not fully implemented, and will let a user play a game by interfacing with a ui screen
* AIPlayer currently makes random moves.
* RemotePlayer writes data to, and reads data from, a RemoteServer on another machine.
* TestPlayer is a temporary console io interface, and will let a user play a game via the standard io stream.

# Servers
One server object is required to play a session of tic tac toe. Servers use Players.
* LocalServer uses two Players and simulates a game of tic tac toe.
* RemoteServer uses a player and connects to a RemotePlayer on another machine.
