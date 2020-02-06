import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card>{                          //usiamo interfaccia comprable per fare un mazzo ordinate

    private final Rank rank;
    private final Suit suit;

    private Card(final Suit suit,
                 final Rank rank){
        this.rank = rank;
        this.suit = suit;

    }
    private final static  Map<String, Card> CARD_CACHE = initCache();

    private final static  Map<String,Card> initCache() {

        final Map<String, Card> cache = new HashMap<>();

        for(final Suit suit : Suit.values()){
            for(final Rank rank : Rank.values()){
                cache.put(cardKey(rank,suit), new Card(suit, rank));
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    public static Card getCard(final Rank rank,
                               final Suit suit){
        final Card card = CARD_CACHE.get(cardKey(rank,suit));
        if(card != null){
            return card;
        }
        throw new RuntimeException("invalid card!!"+ rank+ " " +suit);
    }

    private static String cardKey(final Rank rank,
                                  final Suit suit) {
        return rank + " di "+ suit;
    }

    @Override
    public String toString(){

        return String.format("%s di %s" , this.rank, this.suit);
    }

    @Override
    public int compareTo(Card o) {
        final int rankComprison = Integer.compare(this.rank.getRankValue(),o.rank.getRankValue());
        if(rankComprison != 0){
            return rankComprison;
        }
        return Integer.compare(this.suit.getSuitValue(), o.suit.getSuitValue());
    }

    enum Suit{

        CUORI(1),
        QADRI(2),
        FIORI(3),
        PICCHE(4);

        private final int suitValue;

        Suit(final int suitValue){
            this.suitValue = suitValue;
        }

        private int getSuitValue(){
            return this.suitValue;
        }

    }

    enum Rank{

        DUE(2),
        TRE(3),
        QUATTRO(4),
        CINQUE(5),
        SEI(6),
        SETTE(7),
        OTTO(8),
        NOVE(9),
        DIECI(10),
        JACK(11),
        QUEEN(11),
        KING(12),
        ASSO(13);

        private final int rankValue;

        Rank(final int rankValue){
            this.rankValue = rankValue;
        }

        private int getRankValue(){
            return this.rankValue;
        }

    }
}
