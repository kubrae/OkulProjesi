package school_management;


public class Teacher extends Person {
    private String branch;
    private String sicilNo;

    public Teacher(String nameSurname, String tcNum, int age, String branch, String sicilNo) {
        super(nameSurname, tcNum, age);
        this.branch = branch;
        this.sicilNo = sicilNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }
}
