package ija.ija2016.project.model.cards;

import java.io.Serializable;

public interface CardDeckInterface extends Serializable {

    /**
     * Odebere kartu z vrcholu balíčku. Pokud je balíček prázdný, vrací null.
     *
     * @return Karta z vrcholu balíčku.
     */
    CardInterface pop();

    /**
     * Vloží kartu na vrchol balíčku.
     *
     * @param card CardInterface
     * @return Úspěšnost akce.
     */
    boolean put(CardInterface card);

    /**
     * Vlozi vsechny karty na zasobnik v poradi, v jakem jsou v poli - 0. index bude na dne zasobniku
     *
     * @param cards Cards to put to the deck
     * @return true on success, false otherwise
     */
    boolean put(CardInterface[] cards);

    /**
     * @return Aktuální počet karet v balíčku.
     */
    int size();

    /**
     * Vrátí kartu z vrcholu zásobníku (karta zůstává na zásobníku). Pokud je balíček prázdný, vrací null.
     *
     * @return Karta z vrcholu balíčku.
     */
    CardInterface get();

    /**
     * Vrátí kartu na uvedenem indexu. Spodni karta je na indexu 0, vrchol je na indexu size()-1.
     * Pokud je balíček prázdný, nebo index mimo rozsah, vrací null.
     *
     * @param index int  Pozice karty v balicku.
     * @return Karta z vrcholu balíčku.
     */
    CardInterface get(int index);

    /**
     * Test, zda je balíček karet prázdný.
     *
     * @return Vrací true, pokud je balíček prázdný.
     */
    boolean isEmpty();

}
