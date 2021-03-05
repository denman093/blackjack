import java.util.ArrayList;
import java.util.Collections;

//representation of a 52 card deck

public class Deck {
	private ArrayList<Card> deck= new ArrayList<>();
    private final int deckSize = 52;
    private final int cardsPerSuit = 13;
    private final int cardRankStart = 1;
    private final int topCardOnDeck = 0;
    
    public Deck() { 
        setDeck();
        shuffle();
    }
    
    public void setDeck() {
        for(int i = 0; i < deckSize; i++) 
            deck.add(new Card(cardRankStart + (i % cardsPerSuit), i / cardsPerSuit));
    }
    
    public void shuffle() {
    	Collections.shuffle(deck);
    }
    
    public Card getCard() { return deck.remove(topCardOnDeck); }
    
    public ArrayList<Card> getDeck() { return deck; }
    
    public boolean isEmpty() { return getSize() == 0; }

    public int getSize() { return deck.size(); }
    
    public String toString() {
        String str = new String();
        
        for(Card card : deck) 
            str += card + "\n";
        
        return str;
    }
}
