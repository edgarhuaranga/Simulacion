import java.util.Random;

import static java.lang.Math.log;

/**
 * Created by edgar on 11/09/17.
 */
public class ClosedNetworks {
    public ClosedNetworks() {
    }

    public void run(){
        int M=4;
        int N=10;
        double t=0; double T=100;
        int [] SS = new int [M];
        double [] EL = new double [M];
        double [] mu = {3, 1, .75, 2};
        double [][] RR = {{.2,.4,.4,1}, {.5,1,1,1}, {0,.4,.4,1}, {0,0,1,1}};
        double infty = 99999999.99;
        Random generator = new Random();
        double u;
        SS[0] = N;
        for (int i=1; i<M; i++) { SS[i] = 0;}
        for (int i=0; i<M; i++) {
            EL[i] = infty;
            if ( SS[i] > 0 ) {
                u = generator.nextDouble(); EL[i] = -log(u)/mu[i];
            }
        }

        while (t <= T) {
            int source = 0;
            double small = EL[0];
            for (int i=1; i<M; i++) {
                if (small > EL[i]) { small = EL[i]; source = i;}
            }

            u = generator.nextDouble();
// Get destination
            int dest = 0;
            while (u > RR[source][dest]) dest++;
            t = EL[source]; SS[source]--; SS[dest]++;
// Next state
            EL[source] = infty;
            if (SS[source] > 0) {
                u = generator.nextDouble(); EL[source] = t-log(u)/mu[source];
            }
            if (SS[dest] == 1) {
                u = generator.nextDouble(); EL[dest] = t-log(u)/mu[dest];
            }

            System.out.println("State at time T:"+T);
            System.out.println(SS[0] + " " + SS[1] + " " + SS[2] + " " + SS[3]);
        }


    }
}
