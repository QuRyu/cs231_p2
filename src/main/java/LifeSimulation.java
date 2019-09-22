import java.util.Random;

public class LifeSimulation {

    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(10,10);
        Random gen = new Random();
        double density = 0.3;

        // initialize the grid to be 30% full
        for (int i = 0; i < scape.getRows(); i++) {
            for (int j = 0; j < scape.getCols(); j++ ) {
                scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
            }
        }

        LandscapeDisplay display = new LandscapeDisplay(scape, 8);

        for (int i = 0; i < 10; i++) {
            scape.advance();
            display.repaint();
            display.saveImage("/Users/HereWegoR/Desktop/round" + i + ".png");

            Thread.sleep(250);
        }
    }
}
