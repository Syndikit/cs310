/*
* Author: Michael Austin
* Academic Integrity Statement: I have not violated the WVU Academic Integrity Policy or cheated. The work below
* is solely my own. Etc. Etc.
*
* It wasn't explicitly stated that we could not use Math.log(), but it also wasn't stated that we could, so I just
* implemented everything in the Java-y version of my Prolog script. Ignored a change of base in the typical sense and
* just did the iterative division that a logarithm essentially is...
*
* Overloaded lgstar methods. Examples of calling in the Caller class, attached.
 */

public class LgStar {

    public static int lgbase2(int N){
        int result = -1;
        if (N > 1){
            while(N > 0){
                N /= 2;
                result += 1;
            }
        }else if(N < 0){
            return -1;
        }else{
            result = 0;
        }
        return result;
    }

    public static int lgstar(int N){
        int count = 0;
        int Iters = lgbase2(N);
        if(Iters == 0){
            return count;
        }
        else{
            while (Iters > 0){
                count++;
                Iters = lgbase2(Iters);

            }
        }
        return count;
    }

    static boolean lgstar(int N, int Iters){
        boolean res = false;
        int test = lgstar(N);
        if(Iters == test){
            res = true;
        }
        return res;
    }
}
