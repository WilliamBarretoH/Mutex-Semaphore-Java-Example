import java.util.ArrayList;
import java.util.List;

public class Taylor {

    public static int fatorial(int n){
        if(n == 0) return 1;
        return n * fatorial(n-1);
    }

    public static double radianos(double x){
        double pi = 3.1415;
        return x * pi /180;
    }

    public static double taylorRecursivo(double X, int n){
        double R = radianos(X);
        if(n == 0) {
            return 0;
        }
        return taylorRecursivo(X, n-1) + Math.pow(-1, (n-1))*Math.pow(R, (2*(n-1)))/fatorial(2*(n-1));
    }

    public static String formatFloat(double result){
        String string = String.valueOf(result);
        if(string.charAt(5) == '7' || string.charAt(5) == '8' || string.charAt(5) == '9'){
            double value = Double.parseDouble(string);
            value += 0.001;
            String stringFormated = String.valueOf(value);
            return String.format("%.5s", stringFormated);
        }
        return String.format("%.5s", string);

    }

    public static void main(String[] args) {
        int x = fatorial(5);
        System.out.println(taylorRecursivo(10,5));
        System.out.println(formatFloat(taylorRecursivo(10,5)));

    }

    public static double taylor(double R){
        R = radianos(R);
        int n = 0;
        double result = 0;
        while(n <= 5){
            result += Math.pow(-1, n)*Math.pow(R, (2*n))/fatorial(2*n);
            n+=1;
        }
        return result;
    }
}
