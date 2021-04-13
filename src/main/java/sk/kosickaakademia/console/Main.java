package sk.kosickaakademia.console;

import sk.kosickaakademia.calc.Convert;

public class Main {
    public static void main(String[] args) {
        System.out.println( "Hello World!" );
        Convert convert = new Convert("EUR");
        convert.convert(10);
    }
}
