import java.util.Random;

import static java.lang.Math.*;

/**
 * Created by edgar on 11/09/17.
 */
public class MachineRepairman {

    public MachineRepairman() {
    }

    public void run(){
        int M=10;
        int N=6;
        double t=0;
        double [] EL = new double [N+1];
        double infty = 99999999.99;
        int n = M;
        int b = 0;
        Random generator = new Random();
        double u1; double u2;
        double sigma1 = 100; double mu1 = 600; double sigma2 = 1;
        double mu2 = 15;
        for (int i=0; i<N; i=i+2) {
            u1 = generator.nextDouble();
            u2 = generator.nextDouble();
            double x1 = cos(2*PI*u1) * sqrt(-2 * log(u2));
            double x2 = sin(2*PI*u1) * sqrt(-2 * log(u2));
            EL[i] = sigma1*x1 + mu1; EL[i+1] = sigma1*x2 + mu1;
        }
        EL[N] = infty;

        while (n >= N) {
            int index= 0;
            double small = EL[0];
            for (int i=1; i<=N; i++) {
// Get next event
                if (small > EL[i]) { small = EL[i]; index = i;}
            }
            t = EL[index];
            if (index == N) {
// Next event is a repair completion
                n++; b--;
                EL[N] = infty;
                if (b > 0) {
// Get time until next repair completion
                    u1 = generator.nextDouble();
                    u2 = generator.nextDouble();
                    double x1 = cos(2*PI*u1) * sqrt(-2 * log(u2));
                    EL[N] = t + sigma2*x1 + mu2;
                }
            }
            else {
// Next event is a breakdown
                n--;
                b++;
                if ( n > N ) {
// Get time until the new machine fails
                    u1 = generator.nextDouble();
                    u2 = generator.nextDouble();
                    double x1 = cos(2*PI*u1) * sqrt(-2 * log(u2));
                    EL[index] = t + sigma1*x1 + mu1;
                }
                if (b==1) {
// Repair queue was empty
                    u1 = generator.nextDouble();
                    u2 = generator.nextDouble();
                    double x1 = cos(2*PI*u1) * sqrt(-2 * log(u2));
                    EL[N] = t + sigma2*x1 + mu2;
                }
            }
        }
        System.out.println("System halts at time " + t);
    }
}
