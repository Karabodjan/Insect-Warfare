package fr.celso.insectwarfare.main;

import java.util.Random;

public class Combat {

    // Constants to represent rock, paper, and scissors choices
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;

    private int playerChoice;
    private int opponentChoice;

    public Combat() {

    }

    // Method to set the player's choice
    public void setPlayerChoice(int choice) {
        this.playerChoice = choice;
        generateOpponentChoice();
    }

    // Private method to generate a random choice for the opponent
    private void generateOpponentChoice() {
        Random random = new Random();
        this.opponentChoice = random.nextInt(3) + 1;
    }

    // Method to determine the result of the combat
    public String getResult() {
        if (playerChoice == opponentChoice) {
            return "Draw!";
        } else if ((playerChoice == ROCK && opponentChoice == SCISSORS) ||
                (playerChoice == PAPER && opponentChoice == ROCK) ||
                (playerChoice == SCISSORS && opponentChoice == PAPER)) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }
}
