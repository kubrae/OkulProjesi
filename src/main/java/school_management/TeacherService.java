package school_management;

public class TeacherService extends PersonService {

    public TeacherService() {
        Teacher teacher1 = new Teacher("Aynur Ahtivar", "33333333333", 30, "Software", "1111111");
        Teacher teacher2 = new Teacher("Defne Ahtivar", "22222222222", 32, "Software", "2222222");
        personList.add(teacher1);
        personList.add(teacher2);
    }

    @Override
    public void addPerson() {
        boolean isValid;
        do {

            String name = nameValidate();
            String idNo = tcSorgu();            //tcNo kurallara uygun girilmiş ise tcNo'yu döndürüyor.
            int count = matchPersonNum(idNo);   //girilen tcNo zaten var mı kontrolü
            if (count > 0) {
                System.out.println("Bu TC'ye sahip sistemde ogretmen vardir! Tekrar deneyiniz..");
                isValid = false;
            } else {
                System.out.print("Yas : ");
                int age = scan.nextInt();
                System.out.print("Bolumu : ");
                scan.nextLine();
                String branch = scan.nextLine();

                String sicilNo = sicilNoSorgu(); //girilen numara zaten var mi kontrolu

                this.personList.add(new Teacher(name, idNo, age, branch, sicilNo));
                System.out.println("------> Eklenen Ogretmen Bilgileri : ");
                System.out.printf("%-20s %-15s %-7s %-10s %-10s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Bolumu ", "Sicil No ");
                System.out.printf("%-20s %-15s %-7s %-10s %-10s \n", name, idNo, age, branch, sicilNo);
                isValid = true;
            }
        } while (!isValid);

    }
    @Override
    public void searchPerson() {
        char select = 1;
        do {
            String tc = tcSorgu();
            int count = matchPersonNum(tc); //eslesen tc sayisi
            if (count == 1) {
                matchTeacherList(tc);   //eslesen kisiyi goruntule
                select = repeatSorgu();
            } else if (count == 0) {
                System.out.println("Aradiginiz ogretmenin kaydi bulunmamaktadir.");
                select = repeatSorgu();
            }
        } while (select != 'Q');

    }
    @Override
    public void viewPerson() {
        System.out.println("-------------------> OKULUMUZ OGRETMENLERI <-------------------");
        System.out.printf("%-20s %-15s %-7s %-10s %-10s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Bolumu ", "Sicil No ");
        for (Person w : this.personList) {
            Teacher teacher = (Teacher) w;
            System.out.printf("%-20s %-15s %-7s %-10s %-10s \n", teacher.getNameSurname(), teacher.getTcNum(), teacher.getAge(), teacher.getBranch(), teacher.getSicilNo());
        }

    }
    @Override
    public void deletePerson() {
        int count = 0;
        char select = 1;
        do {
            System.out.print("Lutfen silmek istediginiz ogretmenin TC'sini giriniz : ");
            String dtTC = scan.next();
            for (Person w : this.personList) {

                Teacher teacher = (Teacher) w;

                if (w.getTcNum().equals(dtTC)) {
                    System.out.println("------> Silinen Ogretmen : ");
                    System.out.printf("%-20s %-15s %-7s %-10s %-10s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Bolumu ", "Sicil No ");
                    System.out.printf("%-20s %-15s %-7s %-10s %-10s \n\n", teacher.getNameSurname(), teacher.getTcNum(), teacher.getAge(), teacher.getBranch(), teacher.getSicilNo());
                    this.personList.remove(w);
                    System.out.println("... Ogretmen silinmistir.\n");
                    count++;
                    select = 'Q';
                    break;
                }
            }
            if (count == 0) {
                System.out.println("Silmek istediginiz ogretmenin kaydi bulunmamaktadir.");
                select = repeatSorgu();
            }
        } while (select != 'Q');

    }
    public String sicilNoSorgu() {
        int count = 0;
        String sicil;
        boolean isValid = true;
        do {
            System.out.print("Sicil No : ");
            sicil = scan.next();

            boolean justDigit = sicil.replaceAll("[0-9]", "").length() == 0;
            if (!justDigit) {
                System.out.println("Sicil No sadece rakamlardan olusmalidir!");
                isValid = false;
            } else if (sicil.length() != 7) {
                System.out.println("Sicil No 7 haneli olmalidir! ");
                isValid = false;
            } else {
                for (Person w : personList) {
                    Teacher teacher = (Teacher) w;
                    if (teacher.getSicilNo().equals(sicil)) {
                        System.out.println("Bu Sicil Numarasi baska bir ogretmene aittir. Tekrar deneyiniz..");
                        count++;
                        isValid = false;
                        break;
                    } else {
                        isValid = true;
                    }
                }
            }
        } while (!isValid);
        return sicil;

    }
    public void matchTeacherList(String tc) {
        System.out.println("Aramanizla Eslesen Kisi Bilgileri : ");
        System.out.printf("%-20s %-15s %-7s %-10s %-10s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Bolumu ", "Sicil No ");
        for (Person w : this.personList) {
            Teacher teacher = (Teacher) w;
            if (w.getTcNum().equals(tc)) {
                System.out.printf("%-20s %-15s %-7s %-10s %-10s \n", teacher.getNameSurname(), teacher.getTcNum(), teacher.getAge(), teacher.getBranch(), teacher.getSicilNo());
            }
        }
    }


}
