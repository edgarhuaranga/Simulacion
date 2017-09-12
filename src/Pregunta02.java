/**
 * Created by edgar on 11/09/17.
 */
public class Pregunta02 {
    static int[] z1 = {45, 100, 57};
    static int[] z2 = {17, 1, 24};
    static int[] a1 = {12, 0, 89};
    static int[] a2 = {0, 14, 28};
    static int m1 = 127;
    static int m2 = 31;

    public static void main(String[] args){



        for(int i=0; i<6; i++){
            int z1i = generateZ1(i);
            int z2i = generateZ2(i);
            int xi = (z1i - z2i)%m1;
            System.out.println("x["+i+"] =\t"+xi);
        }
    }

    public static int generateZ1(int i){
        if(i<3) return z1[i];
        else return (a1[0]*generateZ1(i-1) + a1[1]*generateZ1(i-2) + a1[2]*generateZ1(i-3))%m1;
    }

    public static int generateZ2(int i){
        if(i<3) return z2[i];
        else return (a2[0]*generateZ2(i-1) + a2[1]*generateZ2(i-2) + a2[2]*generateZ2(i-3))%m2;
    }
}
