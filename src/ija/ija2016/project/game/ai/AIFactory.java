/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.game.ai;

public class AIFactory {
    public AIStrategyInterface getAI() {
        return new NaiveAIStrategy();
    }

}
