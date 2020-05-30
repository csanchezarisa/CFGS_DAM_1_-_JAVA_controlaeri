import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class avio {

    private ArrayList<String> matricules = controladorAeri.getMatricules(); // Petita funció que utilitza l'ArrayList de la classe principal per mantenir en memoria les matricules introduides
    private final int tamanyMatricula = 3;
    private Scanner teclat = new Scanner(System.in);

    // Característiques dels avions
    private String matricula = "";
    private int autonomia = 0;
    private int capacitatCarrega = 0;
    private int posicioX = 0;
    private int posicioY = 0;
    private boolean inicialitzat = false;

    avio() {
        this.matricula = "";
        this.autonomia = 0;
        this.capacitatCarrega = 0;
        this.posicioX = 0;
        this.posicioY = 0;
        this.inicialitzat = false;
    }

    public void inicialitzarAvio(int mapaX, int mapaY) {
        this.matricula = setMatricula();
        matricules.add(matricula);
        this.capacitatCarrega = demanarCapacitatCarrega();
        this.autonomia = demanarAutonomia();
        assignarPosicions(mapaX, mapaY);
        this.inicialitzat = true;
    }


    // Diu si l'avió ha estat inicialitzat per l'usuari o no
    public boolean getInicializat() {
        return this.inicialitzat;
    }


    // Retorna la matrícula
    public String getMatricula() {
        return this.matricula;
    }

    public int getAutonomia() {
        return this.autonomia;
    }

    public int getCapacitatCarrega() {
        return this.capacitatCarrega;
    }

    public int getPosicioX() {
        return this.posicioX;
    }

    public int getPosicioY(){
        return this.posicioY;
    }



    // Metode que s'encarregarà de demanar una matricula vàlida a l'usuari
    // Utilitzarà la constat "tamanyMatricula" per definir quina mida ha de tenir
    public String setMatricula() {
        do {
            System.out.println("Introdueix la matricula:");
            matricula = validarMatricula(teclat.nextLine());

            if (!compararMatricules(matricula))
                System.out.println("Introdueix una matrícula no repetida!");
        } while (!compararMatricules(matricula));

        return matricula;
    }

    // Mètode que s'utilitza per corregir el tamany de les matrícules introduides
    // Si la matricula és més gran o més petita que el tamany decidit en la constant, aquesta es modificarà
    private String validarMatricula(String matricula) {
        String matriculaValidada = "";

        if (matricula.length() == tamanyMatricula)
            matriculaValidada = matricula;

        else if (matricula.length() > tamanyMatricula) {
            for (int i = 0; i < tamanyMatricula; i++) {
                matriculaValidada = matriculaValidada + matricula.charAt(i);
            }
        } else if (matricula.length() < tamanyMatricula) {
            matriculaValidada = matricula;
            for (int i = matriculaValidada.length(); i < tamanyMatricula; i++) {
                matriculaValidada = matriculaValidada + "X";
            }
        }

        return matriculaValidada;
    }

    // Comprova que la matricula introduida és única
    private boolean compararMatricules(String matricula) {
        boolean correcte = true;

        for (int i = 0; i < matricules.size(); i++) {
            if (matricula.equals(matricules.get(i))) {
                correcte = false;
            }
        }

        return correcte;
    }


    // Mètode que s'encarrega de demanar la capacitat de càrrega per l'avió.
    private int demanarCapacitatCarrega() {
        System.out.println("Introduix la capacitat de càrrega:");
        int capacitatCarrega = introduirNombrePositiu();

        return capacitatCarrega;
    }


    // Mètode que s'encarrega de demanar l'autonomia
    private int demanarAutonomia() {
        System.out.println("Introdueix l'autonomia de l'avió");
        int autonomia = introduirNombrePositiu();

        return autonomia;
    }


    // Mètode que s'encarrega de validar que el nombre introduit és possitiu.
    // En cas contrari el demana un altre cop
    public int introduirNombrePositiu() {
        int nombre = 0;

        do {
            nombre = teclat.nextInt();

            if (nombre < 0)
                System.out.println("Introdueix un nombre positiu...");
        }
        while (nombre < 0);

        return nombre;
    }


    // Mètode que centralitza la gestió de les posicions. Crida a demanar a l'usuari la introducció de les posicions i crida a la comprovació de les mateixes
    private void assignarPosicions(int mapaX, int mapaY) {
        boolean posicionsNoUsades = false;

        int posicioY = 0;
        int posicioX = 0;

        do {
            posicioX = setPosicio(mapaX, 'X');
            posicioY = setPosicio(mapaY, 'Y');

            posicionsNoUsades = controladorAeri.comprovarEspaiLliure(posicioY, posicioX);

            if (!posicionsNoUsades)
                System.out.println("Aquesta posició ja està utilitzada per un altre avió!");
        }
        while (!posicionsNoUsades);

        this.posicioY = posicioY;
        this.posicioX = posicioX;
    }


    // Mètode que s'encarrega de introduir les posicions X i Y, validant els valors
    private int setPosicio(int midaTauler, char eix) {
        boolean dadaCorrecte = true;
        int posicio = 0;

        do {
            dadaCorrecte = true;
            System.out.println("Introdueix la posició " + eix);

            posicio = teclat.nextInt();

            if (!(posicio >= 0 && posicio < midaTauler)) {
                dadaCorrecte = false;
                System.out.println("Introdueix un valor correcte");
                System.out.println("Recorda que el tauler, de eix " + eix + " mesura " + midaTauler);
            }
        }
        while (!dadaCorrecte);

        return posicio;
    }


    // Mètode que permet a l'usuari canviar l'autonomia de l'avió
    public void setAutonomia() {
        int autonomia = demanarAutonomia();
        this.autonomia = autonomia;
    }


    // Mètode que permet a l'usuari canviar la capacitat de càrrega de l'avió
    public void setCapacitatCarrega() {
        int capacitatCarrega = demanarCapacitatCarrega();
        this.capacitatCarrega = capacitatCarrega;
    }

    public void modificarX(int moviment, int midaEix) throws InterruptedException {
        int futuraPosicioX = this.posicioX + moviment;
        if (controladorAeri.comprovarEspaiLliure(this.getPosicioY(), futuraPosicioX)) {
            if (futuraPosicioX >= 0 && futuraPosicioX < midaEix && this.autonomia > 0) {
                this.posicioX = futuraPosicioX;
                this.autonomia--;
            }
            else if (autonomia <= 0) {
                System.out.println("No es pot fer el moviment, l'avió s'ha quedat sense combustible!");
                Thread.sleep(1000);
            }
            else {
                System.out.println("No es pot fer el moviment, es troba en el marge!");
                Thread.sleep(1000);
            }
        }
        else {
            System.out.println("No es pot fer el moviment, hi ha un altre avió!");
            Thread.sleep(1000);
        }
    }

    public void modificarY(int moviment, int midaEix) throws InterruptedException {
        int futuraPosicioY = this.posicioY + moviment;
        if (controladorAeri.comprovarEspaiLliure(futuraPosicioY, this.getPosicioX())) {
            if (futuraPosicioY >= 0 && futuraPosicioY < midaEix && this.autonomia > 0) {
                this.posicioY = futuraPosicioY;
                this.autonomia--;
            }
            else if (this.autonomia <= 0) {
                System.out.println("No es pot fer el moviment, l'avió s'ha quedat sense combustible!");
                Thread.sleep(1000);
            }
            else {
                System.out.println("No es pot fer el moviment, es troba en el marge!");
                Thread.sleep(1000);
            }
        }
        else {
            System.out.println("No es pot fer el moviment, hi ha un altre avió!");
            Thread.sleep(1000);
        }
    }
}

