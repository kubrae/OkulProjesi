package school_management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class PersonService {
    Scanner scan = new Scanner(System.in);
    List<Person> personList = new ArrayList<>();

    public abstract void addPerson();

    public abstract void searchPerson();

    public abstract void viewPerson();

    public abstract void deletePerson();

    public void showMenu() {

        System.out.println();
        System.out.println("=======================>>  ISLEMLER  <<=======================");
        char select;
        do {
            System.out.println("1- EKLEME ");
            System.out.println("2- ARAMA ");
            System.out.println("3- LISTELEME ");
            System.out.println("4- SILME ");
            System.out.println("5- ANA MENU ");
            System.out.println("Q- CIKIS ");
            System.out.println("Seciminiz : ");
            select = scan.next().toUpperCase().charAt(0);
            switch (select) {
                case '1':
                    scan.nextLine();
                    addPerson();
                    break;
                case '2':
                    searchPerson();
                    break;
                case '3':
                    viewPerson();
                    break;
                case '4':
                    deletePerson();
                    break;
                case '5':
                    System.out.println("Ana menuye yonlendiriliyorsunuz....");
                    Runner.start();
                    break;
                case 'Q':
                    System.out.println("Iyi gunler dileriz...");
                    break;
                default:
                    System.out.println("Hatali giris! ");
                    break;
            }

        } while (select != 'Q');
    }


    public static PersonService createInstance(char select) {
        return select == '1' ? new StudentService() : new TeacherService();
    }


    public String tcSorgu() {
        String tcNo;
        boolean isValid = true;
        do {
            System.out.print("Kimlik No : ");
            tcNo = scan.next();
            boolean result1 = tcNo.replaceAll("[0-9]", "").length() == 0;
            boolean result2 = tcNo.length() == 11;
            if (!result1) {
                System.out.println("TC sadece rakamlardan olusmalidir.");
                isValid = false;
            }
            if (!result2) {
                System.out.println("TC kimlik numarasi 11 haneli olmalidir.");
                isValid = false;
            }
            if (result1 && result2) {
                isValid = true;
            }
        } while (!isValid);

        return tcNo;
    }

    public char repeatSorgu() {
        char select;
        do {
            System.out.println("Tekrar sorgulamak icin 1 , CIKIS icin Q ' ya basiniz: ");
            select = scan.next().toUpperCase().charAt(0);

            //Buradaki if yapısı sadeleştirilebilir..
            if (select == '1') {
                return select;
            } else if (select == 'Q') {
                System.out.println("Iyi gunler dileriz..");
            } else {
                System.out.println("Hatali giris!");
            }
        } while (select != 'Q');

        return select;
    }

    public int matchPersonNum(String tc) {
        int count = 0;
        for (Person w : this.personList) {
            if (w.getTcNum().equals(tc)) {
                count++;
            }
        }
        return count;
    }


    public String nameValidate() {
        boolean isValid = true, justLetter;
        String name;
        do {
            System.out.println("Isim - Soyisim: ");
            name = scan.nextLine();
            justLetter = name.replaceAll("[a-zA-Z ]", "").length() == 0;  //harf disinda bir sey var mi kontrolu
            String[] arr = name.trim().split(" ");
            int arrLength = arr.length;

            if (name.isBlank()) {
                System.out.println("Isim ve soyisim bilgisi bos olamaz! Tekrar deneyiniz...");
                isValid = false;
            } else if (!justLetter) {
                System.out.println("Isim soyisim harf disinda karakter iceremez!");
                isValid = false;
            } else if (arrLength == 1) {
                System.out.println("Isim veya soyismi eksik girdiniz! Tekrar deneyiniz..");
                isValid = false;
            } else {
                int count = 0;
                for (String w : arr) {
                    if (w.isBlank()) {
                        System.out.println("Isim ve soyisim arasinda 1'den fazla bosluk olamaz!");
                        count++;
                        isValid = false;
                        break;
                    }
                }
                if (count == 0) {
                    isValid = true;

                }
            }
        } while (!isValid);

        return name;
    }


}


