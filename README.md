# Ping-Pong-Game-JavaFX-Desktop-Application

A two-player Pong game built with JavaFX, featuring multithreaded ball physics, racket collision detection, configurable game settings via menus and a clean MVC package structure.



The ball moves on its own separate thread, so it keeps running smoothly while the rackets respond to keyboard input at the same time. When the ball hits a racket or bounces off the top or bottom wall, collision detection handles the direction change, and when it reaches the left or right edge, a goal is recorded and a message is shown before the ball resets to the centre. Menu options let you set player names, ball speed, racket size, how often the ball speeds up, and the winning score before a match starts. The codebase is organised using an MVC structure, keeping the game state, the rendering, and the input handling in separate packages.

