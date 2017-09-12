/**
 * Created by edgar on 11/09/17.
 */
public class Pregunta03 {
    public static void main(String[] args){
        double[] randomNumbers = {0.11, 0.66, 0.75, 0.76, 0.98};
        int N = randomNumbers.length;
        double dmax_positive = -10000000.0;
        double dmax_negative = -10000000.0;

        for(int i=1; i<=N; i++){
            if(i==1){
                dmax_positive = (double)i/(double)N - randomNumbers[i-1];
                dmax_negative = randomNumbers[i-1] - (double)(i-1)/(double)N;
            }
            else{
                if(dmax_positive < (double)i/(double)N - randomNumbers[i-1]) {
                    dmax_positive = (double)i/(double)N - randomNumbers[i-1];
                }
                if(dmax_negative < randomNumbers[i-1] - (double)(i-1)/(double)N){
                    dmax_negative = randomNumbers[i-1] - (double)(i-1)/(double)N;
                }
            }

            System.out.print("z("+i+")=\t"+randomNumbers[i-1] + "\t"+(double)i/(double)N);
            System.out.print("\t"+((double)i/(double)N - randomNumbers[i-1]));
            System.out.println("\t"+ (randomNumbers[i-1] - (double)(i-1)/(double)N));

        }

        System.out.println("Dmax+ = "+dmax_positive);
        System.out.println("Dmax- = "+dmax_negative);
        double D = dmax_negative;
        if(dmax_positive > dmax_negative) D = dmax_positive;
        else D = dmax_negative;
        System.out.println("D = "+D);
    }
}
