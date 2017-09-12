import java.util.Random;

import static java.lang.Math.log;

/**
 * Created by edgar on 11/09/17.
 */
public class Shopping {
    public Shopping() {
    }

    public void run(){
        double t=0; double T = 100;
        int s = 4; int S = 10;
        int n = S;
        int m = 0;
        double [] EL = new double [2];
        double lambda = 2; double gamma = .4;
        double infty = 99999999.99;
        double c = 3; double d = 5; double h=.5; double z = 4;
        double totH = 0; double totS = S*c+z; double totR = 0;
        Random generator = new Random(); double u = generator.nextDouble();
        EL[0] = -log(u)/lambda;
        EL[1] = infty;

        while (t < T) {
            if (EL[0] < EL[1]) {
// Next event is a customer arrival.
                totH = totH+n*(EL[0]-t)*h;
                t = EL[0];
                u = generator.nextDouble();
                int x = 1;
                if (u >=.25 && u < .75) x = 2;
                if (u >= .75) x = 3;
                int r = x; if (r > n) r = n;
                n = n-r;
                totR = totR + r*d;
                if (n<s && m==0) {
                    m = S-n;
                    u = generator.nextDouble();
                }
                u = generator.nextDouble();
                EL[1] = t-log(u)/gamma;
                EL[0] = t-log(u)/lambda;
            }
            else {
// Next event is an order arrival.
                totH = totH+n*(EL[1]-t)*h;
                t = EL[1];
                totS = totS + m*c+z;
                n = n+m; m=0; EL[1] = infty;
            }
        }

        double profit = totR-totS-totH; double meanProfit = profit/t;
        System.out.println(" " );
        System.out.println("System halts at time " + t);
        System.out.println("Profit/Loss: " + profit);
        System.out.println("Profit/Loss per unit time: " + meanProfit);
    }
}
