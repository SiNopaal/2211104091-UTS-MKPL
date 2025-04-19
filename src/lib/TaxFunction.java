package lib;

public class TaxFunction {

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     */
    public static int calculateTax(EmployeeIncomeData data) {
        if (data.numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        int numberOfChildren = Math.min(data.numberOfChildren, 3);

        // Hitung penghasilan tidak kena pajak
        int nonTaxableIncome = 54000000;
        if (data.isMarried) {
            nonTaxableIncome += 4500000;
        }
        nonTaxableIncome += numberOfChildren * 1500000;

        int yearlyIncome = (data.monthlySalary + data.otherMonthlyIncome) * data.numberOfMonthWorking;
        int taxableIncome = yearlyIncome - data.deductible - nonTaxableIncome;

        int tax = (int) Math.round(0.05 * Math.max(taxableIncome, 0));
        return tax;
    }
}
