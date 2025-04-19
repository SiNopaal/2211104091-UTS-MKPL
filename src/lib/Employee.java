package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; //true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
                    int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.yearJoined = yearJoined;
        this.monthJoined = monthJoined;
        this.dayJoined = dayJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

    public void setMonthlySalary(int grade) {
        switch (grade) {
            case 1 -> monthlySalary = 3000000;
            case 2 -> monthlySalary = 5000000;
            case 3 -> monthlySalary = 7000000;
            default -> monthlySalary = 0;
        }
        if (isForeigner) {
            monthlySalary *= 1.5;
        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber; // FIXED
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        LocalDate currentDate = LocalDate.now();
        monthWorkingInYear = (currentDate.getYear() == yearJoined)
                ? currentDate.getMonthValue() - monthJoined
                : 12;

        EmployeeIncomeData incomeData = new EmployeeIncomeData(
                monthlySalary,
                otherMonthlyIncome,
                monthWorkingInYear,
                annualDeductible,
                spouseIdNumber != null && !spouseIdNumber.isEmpty(),
                childIdNumbers.size()
        );

        return TaxFunction.calculateTax(incomeData);
    }
}
