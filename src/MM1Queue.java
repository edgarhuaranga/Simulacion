import java.util.Random;

import static java.lang.StrictMath.log;

/**
 * Created by edgar on 11/09/17.
 */
public class MM1Queue {
    int N;
    double lambda;
    double mu;


    public MM1Queue() {
        N = 100;
        lambda = 1.0;
        mu = 1.25;
    }

    public MM1Queue(int n, double lambda, double mu) {
        N = n;
        this.lambda = lambda;
        this.mu = mu;
    }

    public void run(){
        Random generator = new Random();
        double u;
        double infty = 999*N*mu;
        int n = 0; int n_a = 0; int n_d = 0;
        double t = 0; double t_a; double t_d = infty;
        double t_lambda = 0; double t_mu = 0; double tot_lambda = 0;
        double tot_mu = 0;
        double[] TA = new double [N+1]; double response = 0; double wait = 0;
        u = generator.nextDouble(); t_a = -log(u)/lambda;
        tot_lambda = t_a;
//*** Begin simulation proper ***
        while (n_d < N) {
            if (t_a < t_d) {
                // Arrival event
                t = t_a;
                n++;
                n_a++; if(n_a <= N) {TA[n_a] = t; }
                u = generator.nextDouble(); t_lambda = -log(u)/lambda;
                t_a = t + t_lambda; tot_lambda += t_lambda;
                if (n==1) {
                // Arrival to an empty system
                    u = generator.nextDouble(); t_mu = -log(u)/mu;
                    t_d = t+t_mu;
                    tot_mu += t_mu;
                }
            }
            else {
// Departure event
                t = t_d;
                n--;
                n_d++;
                response += t-TA[n_d];
                if (n==0) {
                    t_d = infty;
                }
                else {
                    u = generator.nextDouble(); t_mu = -log(u)/mu;
                    t_d = t+t_mu; tot_mu += t_mu;
                }
            }
        }

        wait = (response-tot_mu)/N;
        System.out.println(" ");
        System.out.println("Mean interarrival time: " + tot_lambda/n_a);
        System.out.println("Mean queueing time: " + wait);
        System.out.println("Mean service time: " + tot_mu/N);
    }
}
