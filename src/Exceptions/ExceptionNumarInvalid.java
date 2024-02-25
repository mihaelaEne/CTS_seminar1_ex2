package Exceptions;

public class ExceptionNumarInvalid extends RuntimeException{
    public ExceptionNumarInvalid() {
        System.out.println("Numarul este invalid, nu se afla in lista.");
    }
}
