package ija.ija2016.project.model.cards;

import java.util.Stack;

public class CardStack extends CardDeck implements CardStackInterface {

    public CardStack(int size) {
        super(size);
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    @Override
    public CardStack clone() {
        CardStack cloned = new CardStack(this.maxDeckSize);
        cloned.cards = (Stack<CardInterface>) this.cards.clone();

        return cloned;
    }

//    @Override
//    public CardStack flip() {
//        CardStack cloned = this.clone();
//
//        CardStack resultStack = new CardStack(this.size());
//
//        while (!cloned.cards.empty()) {
//            resultStack.put(cloned.cards.pop());
//        }
//
//        return resultStack;
//    }

    public CardStack takeFrom(CardInterface card) {
        if (!this.cards.contains(card)) {
            return null;
        }

        CardStack resultStack = new CardStack(this.cards.capacity());
        while (!this.cards.empty() && !this.cards.peek().equals(card)) {
            resultStack.put(this.cards.pop());
            resultStack.get().turnFaceUp();
        }

        resultStack.put(this.cards.pop());
        resultStack.get().turnFaceUp();

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
    public boolean put(CardStackInterface stack) {
//        CardStackInterface backupStack = stack.clone(); todo: add backup?

        while (!stack.isEmpty()) {
            if (this.maxDeckSize == this.cards.size()) {
                return false;
            }

            if (!this.put(stack.pop())) {
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
    public CardStack pop(CardInterface card) {
        return this.takeFrom(card);
    }

    /**
     * Metoda odebere kartu z vrcholu zasobniku a vrati ji. Vraci null, pokud na zasobniku nic neni.
     *
     * @return ija.ija2016.project.game.cards.CardInterface nebo null, pokud je zasobnik prazdny
     */
    @Override
    public CardInterface pop() {
        if (this.cards.isEmpty()) {
            return null;
        }

        return this.cards.pop();
    }
}