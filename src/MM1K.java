/**
 * Created by edgar on 11/09/17.
 */

import java.util.Random;
import static java.lang.Math.*;

public class MM1K {
    public static void main (String args[]) {

//    *** Variable definitions and initializations ***

        Random generator = new Random();   double u;
        int N = 100000;  double lambda = 1.0; double mu = 1.25;  double infty = 999*N*mu;
        int lost_customers = 0;
        int n = 0;  int n_a = 0;  int n_d = 0;
        double t = 0;  double t_a;  double t_d = infty;
        double t_lambda = 0; double t_mu = 0; double tot_lambda = 0; double tot_mu = 0;
        double[] TA = new double [N+1];   double response = 0;   double wait = 0;
        u = generator.nextDouble(); t_a = -log(u)/lambda;   tot_lambda = t_a;

//    *** Begin  simulation proper ***

        while (n_d < N) {

            if (t_a < t_d) {
                // Arrival event
                t = t_a;
                u = generator.nextDouble();
                t_lambda = -log(u)/lambda;
                t_a  = t + t_lambda;
                tot_lambda += t_lambda;

                if(n==10){//cola de atención está llena
                    lost_customers++;
                }
                else{
                    //System.out.println("Clientes en atencion: "+n);
                    n++;
                    n_a++;
                    if(n_a <= N) {
                        TA[n_a] = t;
                    }

                    if (n==1) {                     // Arrival to an empty system
                        u = generator.nextDouble(); t_mu = -log(u)/mu;
                        t_d = t+t_mu;   tot_mu += t_mu;
                    }
                }
            }
            else {                             // Departure event
                t = t_d;   n--;   n_d++;
                response += t-TA[n_d];
                if (n==0) {
                    t_d = infty;
                }
                else {
                    u = generator.nextDouble(); t_mu = -log(u)/mu;
                    t_d = t+t_mu;  tot_mu += t_mu;
                }
            }
        }

        System.out.println("Se perdieron "+(double)lost_customers/(double)n_a + " clientes");

//    *** Generate statistics and print report ***

        wait = (response-tot_mu)/N;
        System.out.println("  ");
        System.out.println("Mean interarrival time: " + tot_lambda/n_a);
        System.out.println("Mean queueing time: " + wait);
        System.out.println("Mean service time: " + tot_mu/N);
    }
}
