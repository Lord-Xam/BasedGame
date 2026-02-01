#How to download

## Quick method
- download and unzip file
- open in terminal or cmd
- run `cd build` to enter the `build` directory
- run `java -jar VanHelsing.jar` to run the jar file


## Quick method 2
- download and unzip file
- open in terminal or cmd
- run `cd src` to enter the `src` directory
- run `java SimpleGame.java`

## Build from source
- delete everything in the `build` directory
- run `javac -d ./build src/*` to compile the java files
- `cd build`
- compile to jar `jar cfv VanHelsing.jar *`
- run the jar `java -jar VanHelsing.jar`
