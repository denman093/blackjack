import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	private Player player = new Player("Player");
	private Player bot = new Player("Bot");
	private Player dealer = new Player("Dealer");
	private Deck deck = new Deck();
	private final int initialCards = 2;
	private int blackJack = 21;
	
	//determine the winner from those who didn't bust
	ArrayList<Player> players = new ArrayList<>();
	
	public BlackJack() {
		
	}
	
	public void dealInitialCards() {
		for(int i = 0; i < initialCards; i++) {
			player.hit(deck.getCard());
			bot.hit(deck.getCard());
			dealer.hit(deck.getCard());
		}
	}
	
	public void checkForBlackJack() {
		if(isDealerBlackJack()) {
			System.out.println("Dealer has BlackJack.");
			dealerGameStatus();
			gameOver();
		}
		else if(isPlayerBlackJack()) {
			System.out.println("Player has BlackJack! Player wins!");
			gameStatus();
			gameOver();
		}
		else if(isBotBlackJack()) {
			System.out.println("Bot has BlackJack! Bot wins!");
			gameStatus();
			gameOver();
		}
		else if(isPlayerBlackJack() && isBotBlackJack()) {
			System.out.println("Both Player and Bot have BlackJack! It's a tie.");
			gameStatus();
			gameOver();
		}
		else if(isPlayerBlackJack() && isBotBlackJack() && isDealerBlackJack()) {
			System.out.println("All Players have BlackJack!");
			gameStatus();
			gameOver();
		}
	}
	
	public void gameStatus() {
		System.out.println("______________________________________");
		System.out.println();
		System.out.println("Player Score: " + player.getScore());
		System.out.println("Player Hand:\n" + player.getHand());
		System.out.println("Bot Score: " + bot.getScore());
		System.out.println("Bot Hand:\n" + bot.getHand());
		System.out.println("Dealer Score:" +dealer.getFirstCardScore());
		System.out.println("Dealer Hand:\n" +dealer.showFirstCard());
		System.out.println("______________________________________");
		System.out.println();
	}
	
	public void playerStart() {
		
		gameStatus();
		
		if(isPlayerBlackJack())
			return;
		else {
			Scanner input = new Scanner(System.in);
			int choice = 0; 	//1 for hit, 2 for stay
			
			System.out.println("\nPlayer: Hit or stay (1 or 2): ");
			choice = input.nextInt();
			input.nextLine();
	        
			while(player.getScore() <= blackJack && choice == 1) {
				player.hit(deck.getCard());
				gameStatus();
				
				if(player.getScore() <= 21) {
					System.out.println("\nPlayer: Hit or stay (1 or 2): ");
					choice = input.nextInt();
					input.nextLine();
				}
				else 
					System.out.println("\nPlayer Busts.");
			}
			input.close();
			
			if(player.getScore() <= 21)
				players.add(player);
			
			System.out.println();
		}
	}
	
	public void botStart() {
		gameStatus();
		
		if(isBotBlackJack())
			return;
		else {
			while(bot.getScore() <= 21) {
				
				if(dealer.getFirstCardScore() >= 7 && bot.getScore() >= 17) {
					System.out.println("Bot stays.");
					break;
				}
				else if(dealer.getFirstCardScore() >= 7 && bot.getScore() < 17) {
					//gameStatus();
					bot.hit(deck.getCard());
				}
				else if(dealer.getFirstCardScore() < 7 && bot.getScore() > 11) {
					//gameStatus();
					System.out.println("Bot stays.");
					System.out.println();
					break;
				}
				else if(dealer.getFirstCardScore() < 7 && bot.getScore() <= 11) {
					//gameStatus();
					bot.hit(deck.getCard());
				}
				gameStatus();
			}
		}
		if(bot.getScore() > 21) 
			System.out.println("Bot busts.");
		
		if(bot.getScore() <= 21)
			players.add(bot);
	}
	
	public void dealerGameStatus() {
		System.out.println("______________________________________");
		System.out.println();
		System.out.println("Player Score: " + player.getScore());
		System.out.println("Player Hand:\n" + player.getHand());
		System.out.println("Bot Score: " + bot.getScore());
		System.out.println("Bot Hand:\n" + bot.getHand());
		System.out.println("Dealer Score:\n" +dealer.getScore());
		System.out.println("Dealer Hand:\n" +dealer.getHand());
		System.out.println("______________________________________");
		System.out.println();
	}
	
	public void dealerStart() {
		dealerGameStatus();
		
		while(dealer.getScore() < 17) {
			System.out.println("Dealer Score:\n" +dealer.getScore());
			System.out.println("Dealer Hand:\n" +dealer.showFirstCard());
			dealer.hit(deck.getCard());
			
			dealerGameStatus();
		}
		
		if(dealer.getScore() > 21) 
			System.out.println("Dealer busts.");
		
		if(dealer.getScore() <= 21) {
			System.out.println("Dealer Stays\n");
			players.add(dealer);
		}
	}
	
	public boolean isPlayerBlackJack() {
		return player.getScore() == blackJack;	
	}
	
	public boolean isDealerBlackJack() {
		return dealer.getScore() == blackJack;	
	}
	
	public boolean isBotBlackJack() {
		return bot.getScore() == blackJack;	
	}
	
	public Player determineHighScore() {
		int highScore = players.get(0).getScore();
		int highScoreIndex = 0;
		int duplicateScores = 0;
		int duplicateScore = 0;
		
		if(players.size() == 1)
			return players.get(0);
		else {
			for(int i = 1; i < players.size(); i++) { 
				//check for tie scores
				if(players.get(i).getScore() == highScore) {
					duplicateScore = highScore;
					duplicateScores++;
				}
				
				if(players.get(i).getScore() > highScore) {
					highScore = players.get(i).getScore();
					highScoreIndex = i;
				}
			}
		}
		
		//tie game if two highest score are the same
		if(duplicateScores > 0 && duplicateScore > highScore) {
			System.out.println("Tie Game");
			gameOver();
		}
		
		return players.get(highScoreIndex);
	}
	
	public void determineWinner() {
		if(players.size() == 0) 
			System.out.println("No one wins.");
		else {
			Player winner = determineHighScore();
			System.out.println(winner.getName() + " wins!");
		}
	}
	
	public void gameOver() {
		System.out.println("Game is over. Thanks For Playing.");
		System.exit(0);
	}
	
	@Override
	public String toString() {
		return player.getHand() + " " + player.getScore() + "\n" +
				bot.getHand() + " " + bot.getScore();
	}
}
