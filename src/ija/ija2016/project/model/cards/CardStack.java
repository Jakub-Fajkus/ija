package ija.ija2016.project.model.cards;

public interface CardStack extends CardDeck{
    /**
     * Vloží karty ze zásobníku stack na vrchol zásobníku.
     * Karty vkládá ve stejném pořadí, v jakém jsou uvedeny v zásobníku stack.
     *
     * @param stack Zásobník vkládaných karet
     * @return Uspěšnost akce.
     */
    boolean put(CardStack stack);

    /**
     * Metoda odebere ze zásobníku sekvenci karet od zadané karty až po vrchol zásobníku.
     * Pokud je hledaná karta na vrcholu, bude v sekvenci pouze jedna karta.
     *
     * @param card Hledaná karta.
     * @return Zásobník karet obsahující odebranou sekvenci. Pokud hledaná karta v zásobníku není, vrací null.
     */
    CardStack pop(Card card);

    CardStack flip();
}
