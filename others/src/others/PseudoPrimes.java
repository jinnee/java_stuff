
package others;


import java.util.HashMap;

public class PseudoPrimes {

    public boolean isPseudoPrime(Integer pnumber) {
        Integer tmp = (pnumber - 1) / 2;
        HashMap<Integer, Integer> perm2 = new HashMap();

        for (int i = 1; i <= tmp; i++) {
            perm2.put(i, 2 * i);
            perm2.put(i + tmp, 2 * i - 1);
        }

        Integer pn = 1;
        Integer counter = 0;

        do {
            counter++;
            System.out.println(pn);
            pn = perm2.get(pn);
        } while (!pn.equals(1));

        if ((pnumber - 1) % counter == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        PseudoPrimes ip = new PseudoPrimes();
//341,561
        System.out.println(ip.isPseudoPrime(900233));

//        for (int i = 3; i < 1000; i +=2) {
//            if (ip.isPrime(i)) {
//                System.out.println(i);
//            }
//        }
    }
}
