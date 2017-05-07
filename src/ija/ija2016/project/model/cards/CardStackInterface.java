package ija.ija2016.project.model.cards;

public interface CardStackInterface extends CardDeckInterface {
    /**
     * Vloží karty ze zásobníku stack na vrchol zásobníku.
     * <p>
     * Dokud jsou na zasobniku stack karty, bere z nej tu na vrcholu a pridava ji do aktualniho zasobniku
     *
     * @param stack Zásobník vkládaných karet
     * @return Uspěšnost akce.
     */
    boolean put(CardStackInterface stack);

    /**
     * Metoda odebere ze zásobníku sekvenci karet od zadané karty až po vrchol zásobníku.
     * Pokud je hledaná karta na vrcholu, bude v sekvenci pouze jedna karta.
     *
     * @param card Hledaná karta.
     * @return Zásobník karet obsahující odebranou sekvenci. Pokud hledaná karta v zásobníku není, vrací null.
     */
    CardStackInterface pop(CardInterface card);

    /**
     * Create a copy of the object.
     * The object containing the cards should be copied as well
     * (so the copy could be modified without affecting the original object)
     *
     * @return
     */
    CardStack clone();
}
