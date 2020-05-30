import javax.imageio.stream.ImageOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class controladorAeri {

    private static ArrayList<String> matricules = new ArrayList<String>(); // Simplemnt emmagatzemarà les matricules introduides, per evitar que hi hagi repeticions

    public Scanner teclat = new Scanner(System.in);
    public static final int MIDATAULERX = 10;
    public static final int MIDATAULERY = 10;

    private static avio avio1 = new avio();
    private static avio avio2 = new avio();
    private static avio avio3 = new avio();

    public static avio[] avions = {avio1, avio2, avio3};

    public static void main(String[] args) throws InterruptedException {
        controladorAeri launcher = new controladorAeri();
        launcher.launch();
    }

    private void launch() throws InterruptedException {

        char opcioMenuPrincipal = '0';

        do {
            mostraMenuPrincipal();
            System.out.print("Escull una opció de menú: ");
            opcioMenuPrincipal = teclat.nextLine().charAt(0);

            switch (opcioMenuPrincipal) {
                case '1':
                    inicialitzarAvions();
                    break;
                case '2':
                    if (comprovarMinimAvionsInicialitzats())
                        seleccionarAvioPerGestionar();
                    else
                        System.out.println("No hi ha cap avió inicialitzat!");
                    errorPausarPrograma();
                    break;
                case '3':
                    System.out.println("Sortint del controlador aeri...");
                    errorPausarPrograma();
                    break;
                default:
                    System.out.println("Introdueix un punt de mnú correcte!");
                    errorPausarPrograma();
            }

            netejarPantalla();
        }
        while (opcioMenuPrincipal != '3');
    }


    //******************************************************************************************************************
    // .: 0 - MENÚ PRINCIPAL :.
    //******************************************************************************************************************
    // Mostra el menú principal
    private void mostraMenuPrincipal() {
        System.out.println("+-----------------------------------+");
        System.out.println("+-------    MENÚ PRINCIPAL    ------+");
        System.out.println("+-----------------------------------+");
        System.out.println("|       1. Inicialitzar avions      |");
        System.out.println("|       2. Gestionar avions         |");
        System.out.println("|       3. Sorir                    |");
        System.out.println("+-----------------------------------+");
    }


    //******************************************************************************************************************
    // .: 1 - MENÚ PER INICIALITZAR AVIONS :.
    //******************************************************************************************************************
    // S'encarrega de permetre a l'usuari inicialitzar els avions amb els valors que ell consideri
    private void inicialitzarAvions() throws InterruptedException {
        netejarPantalla();
        mostraMenuInicialitzarAvions();

        System.out.print("Quin avió vols inicialitzar? ");
        char avioPerInicialitzar = teclat.nextLine().charAt(0);

        switch (avioPerInicialitzar) {
            case '1':
                if (avions[0].getInicializat()) {
                    System.out.println("Ja està inicialitzat!");
                    errorPausarPrograma();
                }
                else {
                    System.out.println("Inicialitzant avió...");
                    avions[0].inicialitzarAvio(MIDATAULERX, MIDATAULERY);
                }
                break;
            case '2':
                if (avions[1].getInicializat()) {
                    System.out.println("Ja està inicialitzat!");
                    errorPausarPrograma();
                }
                else {
                    System.out.println("Inicialitzant avió...");
                    avions[1].inicialitzarAvio(MIDATAULERX, MIDATAULERY);
                }
                break;
            case '3':
                if (avions[2].getInicializat()) {
                    System.out.println("Ja està inicialitzat!");
                    errorPausarPrograma();
                }
                else {
                    System.out.println("Inicialitzant avió...");
                    avions[2].inicialitzarAvio(MIDATAULERX, MIDATAULERY);
                }
                break;
            case '4':
                break;
            default:
                System.out.println("Introdueix una opció vàlida!");
                errorPausarPrograma();
        }
    }

    // Mostra el menú d'inicialització d'avions
    private void mostraMenuInicialitzarAvions() {
        System.out.println("+---------------------------------------+");
        System.out.println("+----    INICIALITZADOR D'AVIONS    ----+");
        System.out.println("+---------------------------------------+");

        for (int i = 0; i < avions.length; i++) {
            if (avions[i].getInicializat())
                System.out.println("|       Avió " + (i + 1) + ": Inicialitzat - " + avions[i].getMatricula() +"      |");
            else
                System.out.println("|       Avió " + (i + 1) +": No inicialitzat         |");
        }

        System.out.println("|       4 - Sortir                      |");
        System.out.println("+---------------------------------------+");
    }


    //******************************************************************************************************************
    // .: 2 - MENÚ PER GESTIONAR AVIONS :.
    //******************************************************************************************************************
    // Mètode que revisa els avions dins de l'array per veure si n'hi ha algun d'inicialitzat
    private boolean comprovarMinimAvionsInicialitzats() {
        boolean minimCorrecte = false;

        for (int i = 0; i < avions.length; i++) {
            if (avions[i].getInicializat())
                minimCorrecte = true;
        }

        return minimCorrecte;
    }

    // Mètode que s'encarrega de peretre seleccionar quin avió es vol gestionar
    private void seleccionarAvioPerGestionar() throws InterruptedException {
        netejarPantalla();

        mostraMenuSeleccionarAvioPerGestionar();
        System.out.print("Quin avió vols gestionar? ");
        char avioPerGestionar = teclat.nextLine().charAt(0);

        switch (avioPerGestionar) {
            case '1':
                if (avions[0].getInicializat())
                    gestionarAvio(0);
                else {
                    System.out.println("Aquest avió no està inicialitzat!");
                    errorPausarPrograma();
                }
                break;
            case '2':
                if (avions[1].getInicializat())
                    gestionarAvio(1);
                else {
                    System.out.println("Aquest avió no està inicialitzat!");
                    errorPausarPrograma();
                }
                break;
            case '3':
                if (avions[2].getInicializat())
                    gestionarAvio(2);
                else {
                    System.out.println("Aquest avió no està inicialitzat!");
                    errorPausarPrograma();
                }
                break;
            case '4':
                break;
            default:
                System.out.println("Introdueix un avió valid!");
                errorPausarPrograma();
        }
    }

    // Mostra per pantalla el menú amb els avions que es poden gestionar, és a dir, que estiguin inicialitzats
    private void mostraMenuSeleccionarAvioPerGestionar() {
        System.out.println("+---------------------------------------+");
        System.out.println("+-------    GESTIONAR AVIONS    --------+");
        System.out.println("+---------------------------------------+");

        for (int i = 0; i < avions.length; i++) {
            if (avions[i].getInicializat())
                System.out.println("|       Avió " + (i + 1) + ": " + avions[i].getMatricula() + "                     |");
        }

        System.out.println("|       4 - Sortir                      |");
        System.out.println("+---------------------------------------+");
    }

    // Permet escollir què es vol gestionar de l'avió seleccionat
    private void gestionarAvio(int avioPerGestionar) throws InterruptedException {
        char opcioEscollida = ' ';
        do {
            netejarPantalla();

            mostraMenuGestionarAvio(avioPerGestionar);
            System.out.print("Escull una opció del menú: ");
            opcioEscollida = teclat.nextLine().charAt(0);

            switch (opcioEscollida) {
                case '1': gestionarParametresAvio(avioPerGestionar);
                    break;
                case '2': moureAvio(avioPerGestionar);
                    break;
                case '3':
                    break;
                default:
                    System.out.println("Introdueix una opció vàlida!");
                    errorPausarPrograma();
            }
        }
        while (opcioEscollida != '3');
    }

    // Mostra el menú amb els paràmetres gestionables de l'avió
    private void mostraMenuGestionarAvio(int avio) {
        System.out.println("+---------------------------------------+");
        System.out.println("+-------    AVIÓ " + (avio + 1) + " - " + avions[avio].getMatricula() + "        --------+");
        System.out.println("+---------------------------------------+");
        System.out.println("|   1. Canviar paràmetres               |");
        System.out.println("|   2. Moure l'avió                     |");
        System.out.println("|   3. Sortir                           |");
        System.out.println("+---------------------------------------+");
    }


    //******************************************************************************************************************
    // .: 2.1 - CANVIAR PARÀMETRES  :.
    //******************************************************************************************************************
    // Permet escollir i modificar els paràmetres de l'avió seleccionat
    private void gestionarParametresAvio(int avio) throws InterruptedException {
        char opcioMenu = ' ';
        do {
            netejarPantalla();

            mostrarMenuGestionarParametresAvio(avio);
            System.out.print("Escull el paràmetre a modificar: ");
            opcioMenu = teclat.nextLine().charAt(0);

            switch (opcioMenu) {
                case '1': avions[avio].setMatricula();
                    break;
                case '2': avions[avio].setAutonomia();
                    break;
                case '3': avions[avio].setCapacitatCarrega();
                    break;
                case '4':
                    break;
                default:
                    System.out.println("Introdueix una opció vàlida!");
                    errorPausarPrograma();
            }
        }
        while (opcioMenu != '4');
    }

    // Mostra el menú amb els paràmetres modificables
    private void mostrarMenuGestionarParametresAvio(int avio) {
        System.out.println("+---------------------------------------------------+");
        System.out.println("+-------    PARÀMETRES DE L'AVIÓ " + (avio + 1) + " - " + avions[avio].getMatricula() + "        ----|");
        System.out.println("+---------------------------------------------------+");
        System.out.println("|   1. Matrícula: " + avions[avio].getMatricula() + "                               |");
        System.out.println("|   2. Autonomia: " + avions[avio].getAutonomia() + "                                 |");
        System.out.println("|   3. Capacitat de càrrega: " + avions[avio].getCapacitatCarrega() + "                      |");
        System.out.println("|   4. Sortir                                       |");
        System.out.println("+---------------------------------------------------+");
    }


    //******************************************************************************************************************
    // .: 2.2 - MOURE L'AVIÓ  :.
    //******************************************************************************************************************
    // Permet moure l'avió, mostrar el mapa amb la situació actual dels avions
    private void moureAvio(int avio) throws InterruptedException {
        char opcioEscollida = ' ';
        do {
            netejarPantalla();

            mostrarMapaAvions();
            errorPausarPrograma();
            System.out.println("\nAutonomia: " + avions[avio].getAutonomia());
            mostrarTeclesMoviment();
            opcioEscollida = Character.toUpperCase(teclat.nextLine().charAt(0));

            switch (opcioEscollida) {
                case 'Q':
                    break;
                case 'W':
                    avions[avio].modificarY(-1, MIDATAULERY);
                    break;
                case 'A':
                    avions[avio].modificarX(-1, MIDATAULERX);
                    break;
                case 'S':
                    avions[avio].modificarY(1, MIDATAULERY);
                    break;
                case 'D':
                    avions[avio].modificarX(1, MIDATAULERX);
                    break;
                default:
                    System.out.println("Introdueix una opció vàlida!");
                    errorPausarPrograma();
            }
        }
        while (opcioEscollida != 'Q');
    }

    // Mostrarà el mapa amb els avions incrustats juntament amb un missatge de situació
    private void mostrarMapaAvions() {
        String[][] mapaAvions = generarMapaAvions();

        for (int y = 0; y < mapaAvions[0].length; y++) {
            for (int x = 0; x < mapaAvions.length; x++) {
                System.out.print(mapaAvions[y][x]);
            }
            System.out.println();
        }
        mostrarAlertesColisions(mapaAvions);
    }

    // Genera l'array de String amb el mapa actualizat amb l'estat dels avions.
    private String[][] generarMapaAvions() {
        String[][] mapaAvions = new String[MIDATAULERY + 2][MIDATAULERX + 2];

        for (int y = 0; y < mapaAvions[0].length; y++) {
            for (int x = 0; x < mapaAvions.length; x++) {
                if (y == 0 || y == (mapaAvions[0].length - 1))
                    mapaAvions[y][x] = "###";
                else if (x == 0 || x == (mapaAvions.length - 1))
                    mapaAvions[y][x] = " | ";
                else
                    mapaAvions[y][x] = " ~ ";
            }
        }

        for (int i = 0; i < 3; i++) {
            if (avions[i].getInicializat())
                mapaAvions[avions[i].getPosicioY() + 1][avions[i].getPosicioX() + 1] = avions[i].getMatricula();
        }

        return mapaAvions;
    }

    // Mostrarà un missatge o un altre depenent de si hi ha o no perill de col·lisió
    private void mostrarAlertesColisions(String[][] mapa) {
        if (comprovarVoltantAvions(mapa))
            System.out.println("No hi ha cap perill de col·lisió.");
        else
            System.out.println("Vigila, hi ha perill de col·lisió!");
    }

    // Comprova els voltants dels avions inicialitzats.
    private boolean comprovarVoltantAvions(String[][] mapa) {
        boolean voltantsLliures = true;

        for (int i = 0; i < avions.length; i++) {
            if (avions[i].getInicializat()) {
                for (int y = (avions[i].getPosicioY()); y <= (avions[i].getPosicioY() + 2); y++) {
                    for (int x = (avions[i].getPosicioX()); x <= (avions[i].getPosicioX() + 2); x++) {
                        if (!(mapa[y][x].equals(" ~ ") || mapa[y][x].equals("###") || mapa[y][x].equals(" | ") || mapa[y][x].equals(avions[i].getMatricula()))) {
                            voltantsLliures = false;
                            // Per sortir del bucle...
                            x = avions[i].getPosicioX() + 2;
                            y = avions[i].getPosicioY() + 2;
                        }
                    }
                }
            }
        }

        return voltantsLliures;
    }

    // Mostra amb quines tecles es mourà l'avió.
    private void mostrarTeclesMoviment() {
        System.out.println("\n    Q W");
        System.out.println("    A S D");
    }

    //******************************************************************************************************************
    // .: 3 - EXTRES :.
    //******************************************************************************************************************
    // S'utilitza per poder comunicar la memòria de les matrícules introduides amb la clase avio
    public static ArrayList<String> getMatricules() {
        return matricules;
    }

    // Neteja la pantalla
    public void netejarPantalla() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    // Pausa l'execució del fil principal del programa durant 1 segon
    public void errorPausarPrograma() throws InterruptedException {
        Thread.sleep(1000);
    }

    // S'utilitza des de la classe avions i des d'aquesta. S'encarrega de determinar si les posicions introduides estan o no utilitzades.
    public static boolean comprovarEspaiLliure(int Y, int X) {
        boolean posicioLliure = true;

        for (int i = 0; i < avions.length; i++) {
            if (avions[i].getInicializat() && Y == avions[i].getPosicioY() && X == avions[i].getPosicioX())
                posicioLliure = false;
        }

        return posicioLliure;
    }
}
