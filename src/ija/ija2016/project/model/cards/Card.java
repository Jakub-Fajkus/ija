package ija.ija2016.project.model.cards;

//bez problemu s overovnanim

public class Card implements CardInterface {
    private CardInterface.Color color;
    private int value;
    private boolean facingUp;

    public Card(CardInterface.Color c, int value, boolean facingUp) {
        this.color = c;
        this.value = value;
        this.facingUp = facingUp;
    }

    public static Card.Color[] values() {
        return Color.values();
    }

    public static Card.Color valueOf(String name) {
        if (name == null) {
            throw new NullPointerException("the argument name is null");
        }
        //ija.ija2016.project.game.cards.CardInterface.Color.values()[0].name()
        return Card.Color.valueOf(name);
    }

    public static String beginningChar(Card.Color color) {
        return color.name().substring(0, 1);
    }

    @Override
    public Card.Color color() {
        return this.color;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public boolean isTurnedFaceUp() {
        return this.facingUp;
    }

    @Override
    public boolean turnFaceUp() {
        if (this.facingUp) {
            return false;
        }

        this.facingUp = true;

        return true;
    }

    /**
     * Otočí kartu lícem dolu. Pokud tak už je, nedělá nic.
     *
     * @return boolean Informace, zda došlo k otočení karty (=true) nebo ne.
     */
    @Override
    public boolean turnFaceDown() {
        if (!this.facingUp) {
            return false;
        }

        this.facingUp = false;

        return true;
    }

    @Override
    public boolean similarColorTo(CardInterface c) {
        return this.color.similarColorTo(c.color());
    }

    @Override
    public int compareValue(CardInterface c) {
        return this.value - c.value();
    }

    /**
     * @return
     */
    @Override
    public String getValueAsString() {
        if (this.value == 1) {
            return "A";
        } else if (this.value < 11) {
            return String.valueOf(this.value);
        } else if (this.value == 11) {
            return "J";
        } else if (this.value == 12) {
            return "Q";
        } else if (this.value == 13) {
            return "K";
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        String value;

        return this.getValueAsString() + "(" + this.color + ")" + (this.facingUp ? "U" : "D");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (value != card.value) return false;
        return color == card.color;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + value;
        return result;
    }
}
