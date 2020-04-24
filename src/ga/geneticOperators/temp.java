package ga.geneticOperators;

import javax.swing.*;

public class temp {

    int [] p1 = {1,2,3,4,5,6,7,8,9};
    int [] p2 = {9,8,4,5,6,2,1,3,7};

    int [] ch1, ch2;
    int [] ex2, ex1;
    int [] curte1, curte2;
    int cut1 = 4;
    int cut2 = 6;

    public void run(){
        int a = 0;
        int z = 0;
        for (int i = cut2+1; i < p1.length  ; i++) {
            ch1[z] = p1[i];
            ch2[z] = p2[i];
            z++;
        }
        for (int i = 0; i < cut1 ; i++) {
            ch1[z] = p1[i];
            ch2[z] = p2[i];
            z++;
        }

        for (int i = cut1; i < cut2; i++) {
            curte1[a] = p1[i];
            curte2[a] = p2[i];
            a++;
        }


        


    }


}
