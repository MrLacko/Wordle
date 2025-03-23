# Wordle Game

## Installation

To run the Wordle game, you will need to have Java installed on your system. You can download the latest version of Java from the official website: [https://www.java.com/en/download/](https://www.java.com/en/download/).

Once you have Java installed, you can download the project files from the provided source code. You can either clone the repository or download the ZIP file and extract it to your desired location.

## Dependencies

This project requires the following libraries, which are included in the `./libraries` folder:
- **MigLayout-Swing 5.2**: A powerful layout manager for Java Swing, used to simplify GUI development.
- **MigLayout-Core 5.2**: The core library for MigLayout, providing advanced layout management features.
- **Gson**: A library for converting Java objects to JSON and back, ideal for handling data serialization.

Make sure to include these libraries in your project's classpath when running the 

## Usage

To start the game, navigate to the project directory in your terminal and start from /src/wordle/Main.java

## Classes

The project consists of several Java classes that work together to provide the Wordle game functionality:

- `CheckWord`: This class is responsible for checking the correctness of the user's guess and determining the number of correct and incorrect letters.
- `FileWord`: This class is used to read and write word files for the game.
- `FinalWindow`: This class creates the final window that displays the game's outcome (win or lose).
- `GuessWord`: This class is responsible for generating a random word from a file to be used as the target word in the game.
- `Main`: This is the main entry point of the application, where the game logic is implemented.
- `NumCharChoice`: This class creates a window that allows the user to choose the number of characters for the target word.
- `WordExceptions`: This class defines custom exceptions for handling invalid words.
- `WordleUI`: This class creates the main game window and handles the user interface.

## Contributing

If you would like to contribute to the Wordle game project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your changes to your forked repository.
5. Submit a pull request to the original repository.