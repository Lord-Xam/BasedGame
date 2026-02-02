# How to Install and Run

## Quick method
- download and unzip file
- open terminal or cmd
- run `cd src` to enter the `src` directory
- run `java SimpleGame.java`

## Quick method 2 (unreliable)
- download and unzip file
- open terminal or cmd
- run `cd build` to enter the `build` directory
- run `java -jar VanHelsing.jar` to run the jar file

## Build from source (most likely to work properly)
- download and unzip file
- delete the `.jar` file in the `build` directory
- `javac -d ./build src/*` to compile the java files and place them in `build`
- `cd build` to change to the `build` directory
- `jar cmvf META-INF/MANIFEST.MF VanHelsing.jar *` to compile the jar (**note the asterix**)
- `java -jar VanHelsing.jar` to run the jar
