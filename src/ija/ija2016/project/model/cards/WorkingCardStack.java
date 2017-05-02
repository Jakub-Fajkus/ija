package ija.ija2016.project.model.cards;

public class WorkingCardStack extends CardStack {

    public WorkingCardStack(int size) {
        super(size);
    }

    @Override
    public boolean put(CardInterface card) {
        if (this.isEmpty()) {
            //kral
            return card.value() == 13 && super.put(card);
        }

        //zajistit stridani barev
        if (this.cards.peek().color().similarColorTo(card.color())) {
            return false;
        }

        if (this.cards.peek().value() != (card.value() + 1)) {
            return false;
        }

        return super.put(card);
    }
}
