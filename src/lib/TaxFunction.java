package lib;

public class TaxFunction {

    private static final int PTKP_SINGLE = 54000000;
    private static final int PTKP_MARRIED = 4500000;
    private static final int PTKP_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN = 3;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        if (monthsWorked > 12) {
            System.err.println("More than 12 months worked in a year");
            monthsWorked = 12; // optional safety
        }

        if (numberOfChildren > MAX_CHILDREN) {
            numberOfChildren = MAX_CHILDREN;
        }

        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, monthsWorked);
        int ptkp = calculatePTKP(isMarried, numberOfChildren);
        int taxableIncome = calculateTaxableIncome(annualIncome, deductible, ptkp);

        return calculateFinalTax(taxableIncome);
    }

    private static int calculateAnnualIncome(int salary, int otherIncome, int monthsWorked) {
        return (salary + otherIncome) * monthsWorked;
    }

    private static int calculatePTKP(boolean isMarried, int numberOfChildren) {
        int ptkp = PTKP_SINGLE;
        if (isMarried) {
            ptkp += PTKP_MARRIED;
            ptkp += PTKP_PER_CHILD * numberOfChildren;
        }
        return ptkp;
    }

    private static int calculateTaxableIncome(int annualIncome, int deductible, int ptkp) {
        return annualIncome - deductible - ptkp;
    }

    private static int calculateFinalTax(int taxableIncome) {
        if (taxableIncome <= 0) return 0;
        return (int) Math.round(0.05 * taxableIncome);
    }
}
