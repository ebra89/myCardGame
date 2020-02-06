import java.util.Collections;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.IntStream;

public class Deck {

    private final Stack<Card> deckCards;
                                                              //in mazzo di carte
    private Deck(){

        this.deckCards = initDeck();
    }

    private Stack<Card> initDeck(){

        final Stack<Card> deckCards = new Stack<>();
        for(final Card.Suit suit : Card.Suit.values()){
            for(final Card.Rank rank : Card.Rank.values()){
                deckCards.push(Card.getCard(rank, suit));
            }
        }
        //Collections.shuffle(deckCards);                         // le carte mischiate
        Collections.sort(deckCards);                              // le carte in ordine
        return deckCards;
    }

    public Optional<Card> deal(){         //pop prende elementi di uno stack

        return this.deckCards.empty() ? Optional.empty() : Optional.of(deckCards.pop());
    }


    public static void main(String[] args) {

        final Deck deck = new Deck();
        final int numCarte = 52;
        IntStream.range(0,numCarte).forEach(Value -> System.out.println(deck.deal()));
    }

}
