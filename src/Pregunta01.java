import java.util.Random;

import static java.lang.Math.exp;

/**
 * Created by edgar on 11/09/17.
 */
public class Pregunta01 {
    public static void main(String[] args){
        int max [] = {50,100,1000,10000,50000,100000,500000,1000000};
        Random generator = new Random();
        for (int i=0; i<8; i++) {

            double fx = 0;
            for (int n=1; n<max[i]; n++) {

                double x = generator.nextDouble();
                fx = fx + Math.sqrt(1 - x*x); //s = f(x)
            }
            double Ivalue = fx/(double)max[i];

            System.out.println("n=" + max[i] + "\tPi/4 = " + Ivalue + "\tPi = " + Ivalue*4.0);
        }
    }
}
