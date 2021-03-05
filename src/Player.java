
public class Player {
	private Hand hand = new Hand();
	private String name;

	public Player(String name) {
		setName(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public void hit(Card card) {
		hand.addCard(card);
	}
	
	public int getScore() {
		return hand.getValue();
	}
	
	public Card showFirstCard() {
		return hand.getCard();
	}
	
	public int getFirstCardScore() {
		return hand.getDealerCardValue();
	}
	
	@Override
	public String toString() {
		return hand.toString();
	}
}
