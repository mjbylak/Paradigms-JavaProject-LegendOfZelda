::@echo off
javac Game.java View.java Controller.java Model.java Json.java Link.java Brick.java Pot.java Sprite.java Boomerang.java
if %errorlevel% neq 0 (
	echo There was an error; exiting now.
	pause
	
) else (ds
	echo Compiled correctly!  Running Game...
	java Game	
)

