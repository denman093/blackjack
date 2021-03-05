import java.util.ArrayList;

//representation of a blackjack hand

public class Hand {
	ArrayList<Card> hand = new ArrayList<>();
	private int faceCardRanks = 10;
	private int aceRank = 1;
	private int lowAceValue = 1;
	private int highAceValue = 11;
	
	public Hand() {
		
	}

	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int getValue() {
		int total = 0;
		
		for (Card card : hand) {
			int cardValue = card.getRank();
			
			if(cardValue > faceCardRanks) {
				cardValue = faceCardRanks;
				total += cardValue;
			}
			else if(cardValue == aceRank) {
	            if(total > 10)
	                total += lowAceValue;
	            else
	                total += highAceValue;
			}
	        else
	            total += cardValue;
		}
		
		return total;
	}
	
	public int getDealerCardValue() {
		int cardValue = hand.get(0).getRank();

		if(cardValue > faceCardRanks)
			return faceCardRanks;
		else if(cardValue == aceRank)
			return highAceValue;
		else
			return cardValue;
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public Card getCard() {
		return hand.get(0);
	}
	
	public int getSize() { return hand.size(); }
	
	public boolean isEmpty() { return getSize() == 0; }
	
	public String toString() {
		String str = new String();
        
        for(Card card : hand) 
            str += card + "\n";
        
		return str;
	}
}
