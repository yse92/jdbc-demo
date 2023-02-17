package solvd.util;

public class QueryCollection {
    //AccountBranch
    public static final String DELETE_FROM_ACCOUNT_BRANCH_WHERE_ACCOUNT_ID = "DELETE FROM AccountBranch WHERE accountID = ?";
    public static final String SELECT_FROM_ACCOUNT_BRANCH = "SELECT * FROM AccountBranch";
    public static final String UPDATE_ACCOUNT_BRANCH_BY_ACCOUNT_ID_QUERY = "UPDATE AccountBranch SET accountID = ? , " +
            "branchID = ? WHERE accountID = ?";
    public static final String SELECT_FROM_ACCOUNT_BRANCH_WHERE_ID = "SELECT * FROM AccountBranch WHERE id = ?";
    public static final String INSERT_ACCOUNT_BRANCH_QUERY = "INSERT INTO AccountBranch (accountID, branchID) VALUES (?,?)";
    public static final String UPDATE_ACCOUNT_BRANCH_QUERY = "UPDATE AccountBranch SET accountID = ? , branchID = ? WHERE id = ?";

    //Account
    public static final String SELECT_ALL_ACCOUNTS_QUERY = "SELECT * FROM Account";
    public static final String UPDATE_ACCOUNT_QUERY = "UPDATE Account SET balance = ? , isActive = ?, accountType_id = ?," +
            " login_id = ? WHERE accountID = ? ";
    public static final String GET_ACCOUNT_BY_ID_QUERY = "SELECT * FROM Account WHERE accountID = ?";
    public static final String DELETE_ACCOUNT_QUERY = "DELETE FROM Account WHERE accountID = ?";
    public static final String INSERT_ACCOUNT_QUERY = "INSERT INTO Account (balance, isActive, accountType_id, login_id) " +
            "VALUES (?,?,?,?)";

    //Account Type
    public static final String SELECT_ALL_ACCOUNT_TYPES_QUERY = "SELECT * FROM AccountType";
    public static final String UPDATE_ACCOUNT_TYPE_QUERY = "UPDATE AccountType SET description = ? WHERE id = ? ";
    public static final String GET_ACCOUNT_TYPE_BY_ID_QUERY = "SELECT * FROM AccountType WHERE id = ?";
    public static final String DELETE_ACCOUNT_TYPE_QUERY = "DELETE FROM AccountType WHERE id = ?";
    public static final String INSERT_ACCOUNT_TYPE_QUERY = "INSERT INTO AccountType (description) VALUES (?)";

    //Branch
    public static final String SELECT_ALL_BRANCHES_QUERY = "SELECT * FROM Branch";
    public static final String UPDATE_BRANCH_QUERY = "UPDATE Branch SET adress = ? , phone = ? WHERE branchID = ?";
    public static final String GET_BRANCH_BY_ID_QUERY = "SELECT * FROM Branch WHERE branchID = ?";
    public static final String DELETE_BRANCH_QUERY = "DELETE FROM Branch WHERE branchID = ?";
    public static final String INSERT_BRANCH_QUERY = "INSERT INTO Branch (adress, phone) VALUES (?,?)";

    //Customer
    public static final String SELECT_ALL_CUSTOMERS_QUERY = "SELECT * FROM CUSTOMER";
    public static final String UPDATE_CUSTOMER_QUERY = "UPDATE Customer SET firstName = ? , lastName = ? , phone = ? WHERE id = ? ";
    public static final String GET_CUSTOMER_QUERY = "SELECT * FROM Customer WHERE id = ?";
    public static final String DELETE_CUSTOMER_QUERY = "DELETE FROM Customer WHERE id = ?";
    public static final String INSERT_CUSTOMER_QUERY = "INSERT INTO Customer (firstName, lastName, phone) VALUES (?, ?, ?)";

    //Employee
    public static final String SELECT_ALL_EMPLOYEES_QUERY = "SELECT * FROM Employee";
    public static final String UPDATE_EMPLOYEE_QUERY = "UPDATE Employee SET firstName = ? , lastName = ? , position = ?, " +
            "salary = ? , branch_id = ? WHERE id = ? ";
    public static final String GET_EMPLOYEE_QUERY = "SELECT * FROM Employee WHERE id = ?";
    public static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM Employee WHERE id = ?";
    public static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO Employee (firstName, lastName, position, salary, branch_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    //Loan
    public static final String SELECT_ALL_LOANS_QUERY = "SELECT * FROM Loan";
    public static final String UPDATE_LOAN_QUERY = "UPDATE Loan SET amount = ? , status = ?, loanType_id = ?," +
            " account_id = ?, employee_id = ? WHERE id = ? ";
    public static final String GET_LOAN_BY_ID_QUERY = "SELECT * FROM Loan WHERE id = ?";
    public static final String DELETE_LOAN_QUERY = "DELETE FROM Loan WHERE id = ?";
    public static final String INSERT_LOAN_QUERY = "INSERT INTO Loan (amount, status, loanType_id, account_id, employee_id) " +
            "VALUES (?,?,?,?,?)";

    //Login
    public static final String SELECT_ALL_LOGINS_QUERY = "SELECT * FROM Login";
    public static final String UPDATE_LOGIN_QUERY = "UPDATE Login SET name = ? , password = ? WHERE id = ? ";
    public static final String GET_LOGIN_BY_ID_QUERY = "SELECT * FROM Login WHERE id = ?";
    public static final String DELETE_LOGIN_QUERY = "DELETE FROM Login WHERE id = ?";
    public static final String INSERT_LOGIN_QUERY = "INSERT INTO Login (name, password, customer_id) VALUES (?,?,?)";

    //Transaction
    public static final String SELECT_ALL_TRANSACTIONS_QUERY = "SELECT * FROM Transaction";
    public static final String UPDATE_TRANSACTION_QUERY = "UPDATE Transaction SET amount = ? , transaction_date = ? , " +
            "transactionType_id = ?, account_id = ? , transactionErrorLog_id = ?, employee_id = ? WHERE id = ? ";
    public static final String GET_TRANSACTION_QUERY = "SELECT * FROM Transaction WHERE id = ?";
    public static final String DELETE_TRANSACTION_QUERY = "DELETE FROM Transaction WHERE id = ?";
    public static final String DELETE_TRANSACTION_BY_TYPE_ID_QUERY = "DELETE FROM Transaction WHERE transactionType_id = ?";
    public static final String INSERT_TRANSACTION_QUERY = "INSERT INTO Transaction " +
            "(amount, transaction_date, transactionType_id, transactionErrorLog_id, account_id, employee_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    //Transaction Type
    public static final String SELECT_ALL_TRANSACTION_TYPES_QUERY = "SELECT * FROM TransactionType";
    public static final String UPDATE_TRANSACTION_TYPE_QUERY = "UPDATE TransactionType SET description = ? WHERE id = ? ";
    public static final String GET_TRANSACTION_TYPE_BY_ID_QUERY = "SELECT * FROM TransactionType WHERE id = ?";
    public static final String DELETE_TRANSACTION_TYPE_QUERY = "DELETE FROM TransactionType WHERE id = ?";
    public static final String INSERT_TRANSACTION_TYPE_QUERY = "INSERT INTO TransactionType (description) VALUES (?)";
}
