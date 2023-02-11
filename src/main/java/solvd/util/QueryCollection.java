package solvd.util;

public class QueryCollection {
    //AccountBranch
    public static final String deleteAccountBranchQuery = "DELETE FROM AccountBranch WHERE accountID = ?";
    public static final String selectAllAccountBranchQuery = "SELECT * FROM AccountBranch";
    public static final String updateAccountBranchByAccountIDQuery = "UPDATE AccountBranch SET accountID = ? , " +
            "branchID = ? WHERE accountID = ?";
    public static final String getAccountBranchQuery = "SELECT * FROM AccountBranch WHERE id = ?";
    public static final String insertAccountBranchQuery = "INSERT INTO AccountBranch (accountID, branchID) VALUES (?,?)";
    public static final String updateAccountBranchQuery = "UPDATE AccountBranch SET accountID = ? , branchID = ? WHERE id = ?";

    //Account
    public static final String selectAllAccountsQuery = "SELECT * FROM Account";
    public static final String updateAccountQuery = "UPDATE Account SET balance = ? , isActive = ?, accountType_id = ?," +
            " login_id = ? WHERE accountID = ? ";
    public static final String getAccountByIdQuery = "SELECT * FROM Account WHERE accountID = ?";
    public static final String deleteAccountQuery = "DELETE FROM Account WHERE accountID = ?";
    public static final String insertAccountQuery = "INSERT INTO Account (balance, isActive, accountType_id, login_id) " +
            "VALUES (?,?,?,?)";

    //Account Type
    public static final String selectAllAccountTypesQuery = "SELECT * FROM AccountType";
    public static final String updateAccountTypeQuery = "UPDATE AccountType SET description = ? WHERE id = ? ";
    public static final String getAccountTypeByIdQuery = "SELECT * FROM AccountType WHERE id = ?";
    public static final String deleteAccountTypeQuery = "DELETE FROM AccountType WHERE id = ?";
    public static final String insertAccountTypeQuery = "INSERT INTO AccountType (description) VALUES (?)";

    //Branch
    public static final String selectAllBranchesQuery = "SELECT * FROM Branch";
    public static final String updateBranchQuery = "UPDATE Branch SET adress = ? , phone = ? WHERE branchID = ?";
    public static final String getBranchByIdQuery = "SELECT * FROM Branch WHERE branchID = ?";
    public static final String deleteBranchQuery = "DELETE FROM Branch WHERE branchID = ?";
    public static final String insertBranchQuery = "INSERT INTO Branch (adress, phone) VALUES (?,?)";

    //Customer
    public static final String selectAllCustomersQuery = "SELECT * FROM CUSTOMER";
    public static final String updateCustomerQuery = "UPDATE Customer SET firstName = ? , lastName = ? , phone = ? WHERE id = ? ";
    public static final String getCustomerQuery = "SELECT * FROM Customer WHERE id = ?";
    public static final String deleteCustomerQuery = "DELETE FROM Customer WHERE id = ?";
    public static final String insertCustomerQuery = "INSERT INTO Customer (firstName, lastName, phone) VALUES (?, ?, ?)";

    //Employee
    public static final String selectAllEmployeesQuery = "SELECT * FROM Employee";
    public static final String updateEmployeeQuery = "UPDATE Employee SET firstName = ? , lastName = ? , position = ?, " +
            "salary = ? , branch_id = ? WHERE id = ? ";
    public static final String getEmployeeQuery = "SELECT * FROM Employee WHERE id = ?";
    public static final String deleteEmployeeQuery = "DELETE FROM Employee WHERE id = ?";
    public static final String insertEmployeeQuery = "INSERT INTO Employee (firstName, lastName, position, salary, branch_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    //Loan
    public static final String selectAllLoansQuery = "SELECT * FROM Loan";
    public static final String updateLoanQuery = "UPDATE Loan SET amount = ? , status = ?, loanType_id = ?," +
            " account_id = ?, employee_id = ? WHERE id = ? ";
    public static final String getLoanByIdQuery = "SELECT * FROM Loan WHERE id = ?";
    public static final String deleteLoanQuery = "DELETE FROM Loan WHERE id = ?";
    public static final String insertLoanQuery = "INSERT INTO Loan (amount, status, loanType_id, account_id, employee_id) " +
            "VALUES (?,?,?,?,?)";

    //Login
    public static final String selectAllLoginsQuery = "SELECT * FROM Login";
    public static final String updateLoginQuery = "UPDATE Login SET name = ? , password = ? WHERE id = ? ";
    public static final String getLoginByIdQuery = "SELECT * FROM Login WHERE id = ?";
    public static final String deleteLoginQuery = "DELETE FROM Login WHERE id = ?";
    public static final String insertLoginQuery = "INSERT INTO Login (name, password, customer_id) VALUES (?,?,?)";

    //Transaction
    public static final String selectAllTransactionsQuery = "SELECT * FROM Transaction";
    public static final String updateTransactionQuery = "UPDATE Transaction SET amount = ? , transaction_date = ? , " +
            "transactionType_id = ?, account_id = ? , transactionErrorLog_id = ?, employee_id = ? WHERE id = ? ";
    public static final String getTransactionQuery = "SELECT * FROM Transaction WHERE id = ?";
    public static final String deleteTransactionQuery = "DELETE FROM Transaction WHERE id = ?";
    public static final String deleteTransactionByTypeIdQuery = "DELETE FROM Transaction WHERE transactionType_id = ?";
    public static final String insertTransactionQuery = "INSERT INTO Transaction " +
            "(amount, transaction_date, transactionType_id, transactionErrorLog_id, account_id, employee_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    //Transaction Type
    public static final String selectAllTransactionTypesQuery = "SELECT * FROM TransactionType";
    public static final String updateTransactionTypeQuery = "UPDATE TransactionType SET description = ? WHERE id = ? ";
    public static final String getTransactionTypeByIdQuery = "SELECT * FROM TransactionType WHERE id = ?";
    public static final String deleteTransactionTypeQuery = "DELETE FROM TransactionType WHERE id = ?";
    public static final String insertTransactionTypeQuery = "INSERT INTO TransactionType (description) VALUES (?)";
}
