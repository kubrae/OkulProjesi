package school_management;

import java.util.Scanner;

public class Runner {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        start();

    }

    public static void start() {

        PersonService personService;

        System.out.println("=============================================================");
        System.out.println("------------ OGRENCI VE OGRETMEN YONETIM PANELI -------------");
        System.out.println("=============================================================");
        System.out.println();
        char select;
        do {
            System.out.println("1- OGRENCI ISLEMLERI");
            System.out.println("2- OGRETMEN ISLEMLERI");
            System.out.println("Q- CIKIS");
            System.out.print("Seciminiz : ");
            select = scan.next().toUpperCase().charAt(0);

            switch (select) {
                case '1':
                    personService = PersonService.createInstance(select);
                    personService.showMenu();
                    break;
                case '2':
                    personService = PersonService.createInstance(select);
                    personService.showMenu();
                    break;
                case 'Q':
                    System.out.println("Iyi gunler dileriz...");
                    break;
                default:
                    System.out.println("Hatali giris!");
                    break;

            }


        } while (select != 'Q');


    }


}
