import java.io.*;
import java.util.*;
public class Driver {
    public static void original_test() {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        int[] e1 = {0,1,2,3};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {0,-2,0,0,-9};
        int[] e2 = {0,1,2,3,4};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
        System.out.println("1 is a root of s");
        else
        System.out.println("1 is not a root of s");
    }
    public static void file_test() {
        try {
            //test getting a polynomial from a file
            File f = new File("polynomial.txt");
            Polynomial polyFromFile = new Polynomial(f);
            System.out.println("Polynomial from file: " + Arrays.toString(polyFromFile.co));
            System.out.println("Polynomial from file: " + Arrays.toString(polyFromFile.exp));

            //test saving the polynomial to a new file
            polyFromFile.saveToFile("outputPolynomial.txt");
            System.out.println("Saved to outputPolynomial.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while handling the file: " + e.getMessage());
        }
    }
    /*
    public static void test2() {
    }
    public static void test3() {
    }
    public static void test4() {
    }
    public static void test5() {
    }
    */
    public static void test_multiply() {
        double [] c1 = {5,4};
        int[] e1 = {3,2};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {4,-2,1};
        int[] e2 = {2,5,3};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial m = p1.multiply(p2);
        System.out.println("Multiply CO: " + Arrays.toString(m.co));
        System.out.println("Multiple EX: " + Arrays.toString(m.exp));
    }
    public static void main(String [] args) {
        original_test();
        file_test();
        test_multiply();
    }
}