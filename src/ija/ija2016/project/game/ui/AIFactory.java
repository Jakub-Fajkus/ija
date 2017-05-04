package ija.ija2016.project.game.ui;

public class AIFactory {
    public AIStrategyInterface getAI() {
        return new NaiveAIStrategy();
    }

}
