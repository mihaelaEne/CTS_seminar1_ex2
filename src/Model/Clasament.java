package Model;

import Exceptions.ExceptionNumarInvalid;
import Exceptions.ExceptionVectorGol;
import Exceptions.ExceptionVectorNull;

import java.util.*;
import java.util.stream.Collectors;

public class Clasament {
    private int[] punctaje;

    public Clasament() {
        this.punctaje = new int[0];
    }

    public void verificaVector() throws ExceptionVectorGol, ExceptionVectorNull {
        if (this.punctaje == null) {
            throw new ExceptionVectorNull();
        }
        if (this.punctaje.length == 0) {
            throw new ExceptionVectorGol();
        }
    }

    public void citesteVector() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți numărul de elemente din vector (numarul de note):");
        int n = scanner.nextInt();
        scanner.nextLine();

        if (n <= 0) {
            throw new IllegalArgumentException("Nu puteți avea un vector fără elemente.");
        }

        this.punctaje = new int[n];
        System.out.println("Introduceți elementele vectorului (notele):");
        for (int i = 0; i < n; i++) {
            this.punctaje[i] = scanner.nextInt();
        }
    }

    public void sorteazaSiAfiseazaVector() {
        int[] vectorFaraDuplicate = Arrays.stream(this.punctaje).distinct().toArray();
        Arrays.sort(vectorFaraDuplicate);
        int[] vectorSortat = new int[vectorFaraDuplicate.length];
        for (int i = 0; i < vectorFaraDuplicate.length; i++) {
            vectorSortat[i] = vectorFaraDuplicate[vectorFaraDuplicate.length - 1 - i];
        }
        System.out.println("Clasamentul notelor este: " + Arrays.toString(vectorSortat));
    }

    public int getPozitie(int pct) throws ExceptionVectorGol, ExceptionVectorNull, ExceptionNumarInvalid {
        verificaVector();
        List<Integer> listaSortataFaraDuplicate = Arrays.stream(this.punctaje).boxed()
                .sorted(Collections.reverseOrder())
                .distinct()
                .collect(Collectors.toList());
        int position = listaSortataFaraDuplicate.indexOf(pct);
        if (position == -1) {
            throw new ExceptionNumarInvalid();
        }
        return position + 1;
    }

    public void executareProces() {
        Scanner scanner = new Scanner(System.in);
        int alegere;
        do {
            try {
                citesteVector();
                sorteazaSiAfiseazaVector();
                System.out.println("Introduceți nota pentru a afla poziția:");
                int nota = scanner.nextInt();
                int pozitie = getPozitie(nota);
                System.out.println("Nota " + nota + " se află pe poziția " + pozitie);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Apăsați 1 pentru a repeta procesul sau 2 pentru a ieși:");
            alegere = scanner.nextInt();
        } while (alegere == 1);
    }
}
