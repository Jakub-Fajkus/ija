package ija.ija2016.project.model.cards;

import java.io.Serializable;

public interface CardInterface extends Serializable {
    /**
     * Barva karty.
     *
     * @return Color
     */
    Color color();

    /**
     * Hodnota karty.
     *
     * @return int
     */
    int value();

    /**
     * Testuje, zda je karta otočená lícem nahoru.
     *
     * @return boolean Výsledek testu: true = karta je otočená lícem nahoru.
     */
    boolean isTurnedFaceUp();

    /**
     * Otočí kartu lícem nahoru. Pokud tak už je, nedělá nic.
     *
     * @return boolean Informace, zda došlo k otočení karty (=true) nebo ne.
     */
    boolean turnFaceUp();

    /**
     * Testuje, zda má karta podobnou barvu jako karta zadaná.
     * Podobnou barvou se myslí černá (piky, kříže) a červená (káry a srdce).
     *
     * @param c ija.ija2016.project.game.cards.CardInterface
     * @return boolean Informace o shodě barev karet.
     */
    boolean similarColorTo(CardInterface c);

    /**
     * Porovná hodnotu karty se zadanou kartou c. Pokud jsou stejné, vrací 0.
     * Pokud je karta větší než zadaná c, vrací kladný rozdíl hodnot.
     *
     * @param c ija.ija2016.project.game.cards.CardInterface
     * @return int
     */
    int compareValue(CardInterface c);

    enum Color {
        SPADES('S'), DIAMONDS('D'), HEARTS('H'), CLUBS('C');

        private final char character;

        Color(char character) {
            this.character = character;
        }

        public boolean similarColorTo(CardInterface.Color c) {
            return (c.isBlack() && this.isBlack()) || (!c.isBlack() && !this.isBlack());
        }

        public boolean isBlack() {
            return this.character == SPADES.character || this.character == CLUBS.character;
        }


        @Override
        public String toString() {
            return String.valueOf(this.character);
        }
    }
}
