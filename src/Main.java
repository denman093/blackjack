//Dennis Pillar
//3-4-2021
//Best Buy Coding Challenge
//Blackjack Bot

public class Main {
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		
		game.dealInitialCards();
		game.checkForBlackJack();
		game.playerStart();
		game.botStart();
		game.dealerStart();
		game.determineWinner();
	}
}
