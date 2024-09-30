import java.io.*;
import java.util.*;

public class Polynomial {
    double [] co;
    int [] exp;
    public Polynomial() {
        co = new double[1];
        exp = new int[1];
        co[0] = 0;
        exp[0] = 0;
    }
    public Polynomial(double[] arg) {
        int len = arg.length;
        co = new double[len];
        exp = new int[len];
        System.arraycopy(arg, 0, co, 0, len);
        for (int i=0; i<len; i++) {
            exp[i] = i;
        }
    }
    public Polynomial(double[] c, int[] e) {
        int len = c.length;
        co = new double[len];
        exp = new int[len];
        System.arraycopy(c, 0, co, 0, len);
        System.arraycopy(e, 0, exp, 0, len);
    }
    public Polynomial(File f) throws IOException{
        Scanner scanner = new Scanner(f);
        if (scanner.hasNextLine()) {
            String pString = scanner.nextLine();
            String tString = pString.replaceAll("[-]", " -");
            tString = tString.replaceAll("[+]", " +");
            String[] terms = tString.split(" ");
            //System.out.println(Arrays.toString(terms));
            co = new double[terms.length];
            exp = new int[terms.length];
            for (int i=0; i<terms.length; i++) {
                if (terms[i].indexOf('x') == -1) {
                    co[i] = Integer.parseInt(terms[i]);
                    exp[i] = 0;
                }
                else {
                    String[] tmp = terms[i].split(String.valueOf('x'));
                    //System.out.println(tmp.length);
                    if (tmp.length == 0) {
                        co[i] = 1;
                        exp[i] = 1;
                    }
                    else {
                        if ("".equals(tmp[0])){
                            co[i] = 1;
                        }
                        else if ("-".equals(tmp[0])) {
                            co[i] = -1;
                        }
                        else {
                            co[i] = Double.parseDouble(tmp[0]);
                        }
                        if (tmp.length == 1 ) {
                            exp[i] = 1;
                        }
                        else {
                            exp[i] = Integer.parseInt(tmp[1]);
                        }
                    }
                }
            }
        } 
        else {
            co = new double[1];
            exp = new int[1];
            co[0] = 0;
            exp[0] = 0;
        }
        
        scanner.close();
    }
    public void saveToFile(String f) throws IOException {
        FileWriter writer = new FileWriter(f);
        String pString = "";
        if (exp[0] == 0) {
            if (this.co[0] % 1 == 0) {
                pString += (int)this.co[0];
            }
            else {
                pString += this.co[0];
            }
        }
        else {
            if (this.co[0] % 1 == 0) {
                pString += (int)this.co[0] + "x" + this.exp[0];
            }
            else {
                pString += this.co[0] + "x" + this.exp[0];
            }
        }
        for (int i=1; i<this.co.length; i++) {
            if (exp[i] == 0) {
                if (this.co[i] % 1 == 0) {
                    if (this.co[i] >= 0) {
                        pString += "+" + (int)this.co[i];
                    }
                    else {
                        pString += (int)this.co[i];
                    }
                }
                else {
                    if (this.co[i] >= 0) {
                        pString += "+" + this.co[i];
                    }
                    else {
                        pString += this.co[i];
                    }
                }
                
            }
            else {
                if (this.co[i] % 1 == 0) {
                    if (this.co[i] >= 0) {
                        pString += "+" + (int)this.co[i] + "x" + this.exp[i];
                    }
                    else {
                        pString += (int)this.co[i] + "x" + this.exp[i];
                    }
                }
                else {
                    if (this.co[i] >= 0) {
                        pString += "+" + this.co[i] + "x" + this.exp[i];
                    }
                    else {
                        pString += this.co[i] + "x" + this.exp[i];
                    }
                }
            }
        }
        writer.write(pString);
        writer.close();
    }
    public Polynomial add(Polynomial arg) {
        int newLen = 0;
        double[] tmp_c = new double[this.co.length + arg.co.length];
        int[] tmp_e = new int[this.co.length + arg.co.length];
        int tmp_index = 0;
        for (int i=0; i<this.co.length; i++) {
            for (int j=0; j<arg.co.length; j++) {
                if (arg.exp[j] == this.exp[i]) {
                    break;
                }
                else if (j == arg.co.length - 1) {
                    tmp_c[tmp_index] = this.co[i];
                    tmp_e[tmp_index] = this.exp[i];
                    tmp_index++;
                    newLen++;
                }
            }
        }
        for (int j=0; j<arg.co.length; j++) {
            for (int i=0; i<this.co.length; i++) {
                if (arg.exp[j] == this.exp[i]) {
                    if (arg.co[j] + this.co[i] == 0) {
                        break;
                    }
                    else {
                        tmp_c[tmp_index] = arg.co[j] + this.co[i];
                        tmp_e[tmp_index] = arg.exp[j];
                        tmp_index++;
                        newLen++;
                        break;
                    }
                }
                else if (i == this.co.length - 1) {
                    tmp_c[tmp_index] = arg.co[j];
                    tmp_e[tmp_index] = arg.exp[j];
                    tmp_index++;
                    newLen++;
                }
            }
        }
        double[] c = new double[newLen];
        int[] e = new int[newLen];
        for (int i=0; i<newLen;i++) {
            c[i] = tmp_c[i];
            e[i] = tmp_e[i];
        }
        //System.out.println(Arrays.toString(c));
        //System.out.println(Arrays.toString(e));
        //System.out.println(newLen);
        return new Polynomial(c, e);
    }
    public double evaluate(double x) {
        double tmp = 1;
        double total = 0;
        for(int i=0; i<this.co.length; i++) {
            for (int j=0; j<this.exp[i]; j++) {
                tmp = tmp * x;
            }
            total += this.co[i]*tmp;
            tmp = 1;
        }
            
        return total;
    }
    public boolean hasRoot(double x) {
        return (this.evaluate(x) == 0);
    }
    public Polynomial multiply(Polynomial p) {
        double[] tmp_co = new double[p.co.length + this.co.length];
        int[] tmp_exp = new int[p.co.length + this.co.length];
        int tmp_index = 0;
        boolean added = false;
        for(int i=0; i<p.co.length;i++) {
            for (int j=0; j<this.co.length; j++) {
                double new_co = p.co[i]*this.co[j];
                if (new_co == 0) {
                    break;
                }
                else {
                    int new_exp = p.exp[i]+this.exp[j];
                    for (int k=0; k<tmp_index; k++) {
                        if (new_exp == tmp_exp[k]) {
                            tmp_co[k] += new_co;
                            added = true;
                            break;
                        }
                    }
                    if (!added) {
                        tmp_co[tmp_index] = new_co;
                        tmp_exp[tmp_index] = new_exp;
                        tmp_index++;
                    }
                    added = false;
                }
            }
        }
        double[] n_co = new double[tmp_index];
        int[] n_exp = new int[tmp_index];
        for (int k=0; k<tmp_index; k++) {
            n_co[k] = tmp_co[k];
            n_exp[k] = tmp_exp[k];
        }
        return new Polynomial(n_co, n_exp);
    }
}