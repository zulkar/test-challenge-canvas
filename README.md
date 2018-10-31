# Canvas Challenge
This project is a test task for credit-suisse canvas challenge.

# The Problem as it is decribed

## Description

You're given the task of writing a simple console version of a drawing program. 
At this time, the functionality of the program is quire limited but this might change in the future. 
In a nutshell, the program should work as follows:
 1. Create a new canvas
 2. Start drawing on the canvas by issuing various commands
 3. Quit


Command 		Description
C w h           Should create a new canvas of width w and height h.
L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only
                horizontal or vertical lines are supported. Horizontal and vertical lines
                will be drawn using the 'x' character.
R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and
                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
                using the 'x' character.
B x y c         Should fill the entire area connected to (x,y) with "colour" c. The
                behaviour of this is the same as that of the "bucket fill" tool in paint
                programs.
Q               Should quit the program.

## Sample I/O
```
Below is a sample run of the program. User input is prefixed with enter command:

enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------

enter command: Q
```



# My solution

## Prerequisites
This project use gradle as build tool.
JDK 1.8 is required to build and run the tool

## How to build

###run tests
linux:    `./gradlew clean test`
windows:  `gradlew.bat clean test`

###build fat jar
linux:    `./gradlew clean fatJar`
windows:  `gradlew.bat clean fatJar`

### distribute

linux:    `./gradlew clean distZip`

windows:  `gradlew.bat clean distZip`

## Running tool

compiled tool with all dependencies located in build/distributions/canvas-challenge-1.0-SNAPSHOT.zip
unpack zip to any folder, navigate to `bin/` directory and run `./canvas-challenge` (`canvas-challenge.bat` for windows)

you can run fat jar directly, use `java -jar build/libs/canvas-challenge-all-1.0-SNAPSHOT.jar` to run tool

### Parameters
`-vt100` - I've added some support of VT100 terminals. It is not required in task, but it was a bit fancy.
Support is not good and can behave differently on different systems. I didn't use curses library, 
there are just simple ESC-sequences; Does not work under windows. May work in Cygwin, but I'm not sure.

`-h` - show help

### Logging
All logs are written to canvas.log. By default they are in debug mode. Apache log4j2 is used for logging 
log4j2.xml is located inside jar, to change log level specify standard `-Dlog4j.configurationFile=path/to/log4j2.xml`


## Implementation
Limitations:

* All commands are case-sensitive
* No terminal size checking. If you specify canvas size wider that current terminal size, it will be displayed incorrectly. However, this affects only rendering, in logs you can observe correct image.

## Known issue:
There are a bit messy error message if wrong command with coordinates on border of canvas (or outside canvas) is entered.
Internal coordinates are started with zero-based coordinates, while user operates with 1-based coordinates. I found this just after submission, I believe it is a minor issue :)






