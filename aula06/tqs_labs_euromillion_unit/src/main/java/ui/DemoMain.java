package ui;

import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;
import org.apache.log4j.Logger;

public class DemoMain {

    /**
     * demonstrates a client for ramdom euromillions bets
     */
    public static void main(String[] args) {

        Logger log = Logger.getLogger(DemoMain.class.getName());

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();
        log.info("Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        log.info("You played:");
        log.info(thisWeek.format());

        log.info("Draw results:");
        log.info(draw.getDrawResults().format());

        log.info("Your score:");
        for (Dip dip : draw.findMatches(thisWeek)) {
            log.info(dip.format());

        }
    }
}
