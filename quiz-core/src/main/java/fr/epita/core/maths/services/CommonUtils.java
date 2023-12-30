package fr.epita.core.maths.services;

public class CommonUtils {
    public static int factorial(int n) {
        int result = 1;

        while (n > 0){
            result = n * result;
            n = n - 1;
        }
        return result;
    }
}
