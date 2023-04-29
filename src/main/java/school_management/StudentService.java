package school_management;



public class StudentService extends PersonService {

    public StudentService() {
        Student student1 = new Student("Feride Yabaci", "11111111111", 15, 500, "10");
        Student student2 = new Student("Sevinc Aksoy", "22222222222", 16, 600, "11");
        this.personList.add(student1);
        this.personList.add(student2);
    }


    @Override
    public void addPerson() {
        boolean isValid;
        do {

            String name = nameValidate();
            String idNo = tcSorgu();            //tcNo kurallara uygun girilmiş ise tcNo'yu döndürüyor.
            int count = matchPersonNum(idNo);   //girilen tcNo zaten var mı kontrolü
            if (count > 0) {
                System.out.println("Bu TC'ye sahip sistemde ogrenci vardir! Tekrar deneyiniz..");
                isValid = false;
            } else {
                System.out.print("Yas : ");
                int age = scan.nextInt();

                int scNo = schoolNumberSorgu(); //girilen numara zaten var mi kontrolu

                System.out.print("Sinifi : ");
                scan.nextLine();
                String sinifSeviye = scan.nextLine();
                this.personList.add(new Student(name, idNo, age, scNo, sinifSeviye));
                System.out.println("------> Eklenen Ogrenci Bilgileri : ");
                System.out.printf("%-20s %-15s %-6s %-7s %-6s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Numara ", "Sinifi ");
                System.out.printf("%-20s %-15s %-6s %-7s %-6s \n", name, idNo, age, scNo, sinifSeviye);
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
                matchStudentList(tc);   //eslesen kisiyi goruntule
                select = repeatSorgu();
            } else if (count == 0) {
                System.out.println("Aradiginiz ogrencinin kaydi bulunmamaktadir.");
                select = repeatSorgu();
            }
        } while (select != 'Q');
    }

    @Override
    public void viewPerson() {
        System.out.println("------------------> OKULA KAYITLI OGRENCILER <------------------");
        System.out.printf("%-20s %-15s %-6s %-7s %-6s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Numara ", "Sinifi ");
        for (Person w : this.personList) {
            Student student = (Student) w;
            System.out.printf("%-20s %-15s %-6s %-7s %-6s \n", student.getNameSurname(), student.getTcNum(), student.getAge(), student.getSchoolNum(), student.getClasses());
        }
    }

    @Override
    public void deletePerson() {
        int count = 0;
        char select = 1;
        do {
            System.out.print("Lutfen silmek istediginiz ogrencinin TC'sini giriniz : ");
            String dtTC = scan.next();
            for (Person w : this.personList) {

                Student student =(Student) w;

                if (w.getTcNum().equals(dtTC)) {
                    System.out.println("Silinen Ogrenci : ");
                    System.out.printf("%-20s %-15s %-6s %-7s %-6s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Numara ", "Sinifi ");
                    System.out.printf("%-20s %-15s %-6s %-7s %-6s \n\n", student.getNameSurname(), student.getTcNum(), student.getAge(), student.getSchoolNum(), student.getClasses());
                    this.personList.remove(w);
                    System.out.println("... Ogrenci silinmistir.\n");
                    count++;
                    select = 'Q';
                    break;
                }
            }
            if (count == 0) {
                System.out.println("Silmek istediginiz ogrencinin kaydi bulunmamaktadir.");
                select = repeatSorgu();
            }
        } while (select != 'Q');
    }


    public void matchStudentList(String tc) {
        System.out.println("Aramanizla Eslesen Kisi Bilgileri : ");
        System.out.printf("%-20s %-15s %-6s %-7s %-6s \n", "Adi - Soyadi ", "TC No ", "Yas ", "Numara ", "Sinifi ");
        for (Person w : this.personList) {
            Student student = (Student) w;
            if (w.getTcNum().equals(tc)) {
                System.out.printf("%-20s %-15s %-6s %-7s %-6s \n", student.getNameSurname(), student.getTcNum(), student.getAge(), student.getSchoolNum(), student.getClasses());
            }
        }
    }

    public int schoolNumberSorgu() {
        int count = 0;
        int num;
        do {
            System.out.print("Numara : ");
            num = scan.nextInt();
            for (Person w : personList) {
                Student student = (Student) w;
                if (student.getSchoolNum() == num) {
                    System.out.println("Bu numara baska bir ogrenciye aittir. Tekrar deneyiniz..");
                    count++;
                } else {
                    count = 0;
                }
            }
        } while (count != 0);
        return num;

    }
}