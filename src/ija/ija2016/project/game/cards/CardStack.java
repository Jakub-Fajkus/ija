package ija.ija2016.project.game.cards;

import ija.ija2016.project.model.cards.Card;

import java.util.Stack;

public class CardStack extends CardDeck implements ija.ija2016.project.model.cards.CardStack {

    public CardStack(int size) {
        super(size);
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    @Override
    public CardStack clone() {
        CardStack cloned = new CardStack(this.maxDeckSize);
        cloned.cards = (Stack<Card>) this.cards.clone();

        return cloned;
    }

    @Override
    public CardStack flip() {
        CardStack cloned = this.clone();

        CardStack resultStack = new CardStack(this.size());

        while (!cloned.cards.empty()) {
            resultStack.put(cloned.cards.pop());
        }

        return resultStack;
    }

    public CardStack takeFrom(Card card) {
        if (!this.cards.contains(card)) {
            return null;
        }

        CardStack flippedResultStack = new CardStack(this.cards.capacity());
        while (!this.cards.empty() && !this.cards.peek().equals(card)) {
            flippedResultStack.put(this.cards.pop());
        }

        flippedResultStack.put(this.cards.pop());

        CardStack resultStack = new CardStack(flippedResultStack.size());

        while (!flippedResultStack.cards.empty()) {
            resultStack.put(flippedResultStack.cards.pop());
        }

        return resultStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardStack cardStack = (CardStack) o;

        return cards != null ? cards.equals(cardStack.cards) : cardStack.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }

    /**
     * Vloží karty ze zásobníku stack na vrchol zásobníku.
     * Karty vkládá ve stejném pořadí, v jakém jsou uvedeny v zásobníku stack.
     *
     * @param stack Zásobník vkládaných karet
     * @return Uspěšnost akce.
     */
    @Override
    public boolean put(ija.ija2016.project.model.cards.CardStack stack) {
        ija.ija2016.project.model.cards.CardStack backupStack = stack.flip();

        while (!backupStack.isEmpty()) {
            if (this.maxDeckSize == this.cards.size()) {
                return false;
            }

            if (!this.put(backupStack.pop())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Metoda odebere ze zásobníku sekvenci karet od zadané karty až po vrchol zásobníku.
     * Pokud je hledaná karta na vrcholu, bude v sekvenci pouze jedna karta.
     *
     * @param card Hledaná karta.
     * @return Zásobník karet obsahující odebranou sekvenci. Pokud hledaná karta v zásobníku není, vrací null.
     */
    @Override
    public CardStack pop(ija.ija2016.project.model.cards.Card card) {
        return this.takeFrom(card);
    }

    /**
     * Metoda odebere kartu z vrcholu zasobniku a vrati ji. Vraci null, pokud na zasobniku nic neni.
     *
     * @return ija.ija2016.project.game.cards.Card nebo null, pokud je zasobnik prazdny
     */
    @Override
    public ija.ija2016.project.model.cards.Card pop() {
        if (this.cards.isEmpty()) {
            return null;
        }

        return this.cards.pop();
    }
}