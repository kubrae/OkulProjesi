package school_management;

public class Student extends Person {

    private int schoolNum;
    private String classes;

    public Student(String nameSurname, String tcNum, int age, int schoolNum, String classes) {
        super(nameSurname, tcNum, age);
        this.schoolNum = schoolNum;
        this.classes = classes;
    }


    public int getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(int schoolNum) {
        this.schoolNum = schoolNum;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
