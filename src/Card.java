//representation of a single card

public class Card implements Comparable<Card>{
	private int rank;
    private int suit;

    public Card(int rank, int suit) {
    	setRank(rank);
        setSuit(suit);
    }
    
    public int getRank() { return rank; }

    public int getSuit() { return suit; }
    
    public void setRank(int rank) { this.rank = rank; }
    
    public void setSuit(int suit) { this.suit = suit; }
    
    public int compareTo(Card card) {
        if(rank < card.getRank()) 
            return -1;
        else if(rank > card.getRank())
            return 1;
        else 
            return 0;
    }
    
    public String toString() {
        String rankStr = String.valueOf(rank);
        String suitStr = String.valueOf(suit);
        
        if(rank == 11) rankStr = "Jack";
        if(rank == 12) rankStr = "Queen";
        if(rank == 13) rankStr = "King";
        if(rank == 1 || rank == 14) rankStr = "Ace";
        
        //removed from UI due to irrevelence
        if(suit == 0) suitStr = "Clubs";
        if(suit == 1) suitStr = "Diamonds";
        if(suit == 2) suitStr = "Hearts";
        if(suit == 3) suitStr = "Spades";
        
        return rankStr;
    }
}
