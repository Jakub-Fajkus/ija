package ija.ija2016.project.model.cards;

/**
 * Basic implementation of CardInterface.
 */
public class Card implements CardInterface {
    private CardInterface.Color color;
    private int value;
    private boolean facingUp;

    public Card(CardInterface.Color c, int value, boolean facingUp) {
        this.color = c;
        this.value = value;
        this.facingUp = facingUp;
    }

    public Card(CardInterface card) {
        this.color = card.getColor();
        this.value = card.getValue();
        this.facingUp = card.isTurnedFaceUp();
    }

    @Override
    public Card.Color getColor() {
        return this.color;
    }

    @Override
    public int getValue() {
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
        return this.color.similarColorTo(c.getColor());
    }


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
        return this.getValueAsString() + "(" + this.color + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (this.value != card.value) return false;
        return this.color == card.color;
    }

    @Override
    public int hashCode() {
        int result = this.color != null ? this.color.hashCode() : 0;
        result = 31 * result + this.value;
        return result;
    }
}
