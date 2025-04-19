package lib;

public class TaxFunction {

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     */
    public static int calculateTax(EmployeeIncomeData data) {
        int tax;

        if (data.numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        int numberOfChildren = data.numberOfChildren;
        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }

        int nonTaxableIncome = 54000000;
        if (data.isMarried) {
            nonTaxableIncome += 4500000;
        }
        nonTaxableIncome += numberOfChildren * 1500000;

        int yearlyIncome = (data.monthlySalary + data.otherMonthlyIncome) * data.numberOfMonthWorking;
        tax = (int) Math.round(0.05 * (yearlyIncome - data.deductible - nonTaxableIncome));

        return Math.max(tax, 0);
    }
}
