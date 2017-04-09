package ija.ija2016.project.model.board;

import ija.ija2016.project.model.cards.Card;
import ija.ija2016.project.model.cards.CardDeck;
import ija.ija2016.project.model.cards.CardStack;

public abstract class AbstractFactorySolitaire {

    /**
     * Vytvoří objekt reprezentující kartu.
     *
     * @param color
     * @param value
     * @return
     */
    public abstract Card createCard(Card.Color color, int value);

    /**
     * Vytváří objekt reprezentující balíček karet.
     *
     * @return
     */
    public abstract CardDeck createCardDeck();

    /**
     * Vytváří objekt reprezentující cílový balíček. Cílem hráče je vložit všechny karty zadané barvy do cílového balíčku.
     *
     * @param color
     * @return
     */
    public abstract CardDeck createTargetPack(Card.Color color);

    /**
     * Vytváří objekt reprezentující pracovní pole pro karty.
     *
     * @return
     */
    public abstract CardStack createWorkingPack();

}
