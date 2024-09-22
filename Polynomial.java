public class Polynomial {
    double [] co;
    public Polynomial() {
        co = new double[1];
        co[0] = 0;
    }
    public Polynomial(double[] arg) {
        int len = arg.length;
        co = new double[len];
        System.arraycopy(arg, 0, co, 0, len);
    }
    public Polynomial add(Polynomial arg) {
        int len;
        if(this.co.length < arg.co.length) {
            len = arg.co.length;
        }
        else {
            len = this.co.length;
        }
        double[] r = new double[len];
        for (int i=0; i<len; i++) {
            double this_co = i < this.co.length ? this.co[i] : 0;
            double arg_co = i < arg.co.length ? arg.co[i] : 0;
            r[i] = this_co + arg_co;
        }
        return new Polynomial(r);
    }
    public double evaluate(double x) {
        double tmp = 1;
        double total = 0;
        for(int i=0; i<this.co.length; i++) {
            for (int j=0; j<i; j++) {
                tmp = tmp*x;
            }
            total += this.co[i]*tmp;
            tmp = 1;
        }
        return total;
    }
    public boolean hasRoot(double x) {
        double tmp = 1;
        double total = 0;
        for(int i=0; i<this.co.length; i++) {
            for (int j=0; j<i; j++) {
                tmp = tmp*x;
            }
            total += this.co[i]*tmp;
            tmp = 1;
        }
        return total == 0;
    }
}