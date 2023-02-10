package solvd;

import solvd.model.Transaction;
import solvd.service.AccountBranchService;
import solvd.service.CustomerService;
import solvd.service.LoanService;
import solvd.service.TransactionService;
import solvd.service.impl.AccountBranchServiceImpl;
import solvd.service.impl.CustomerServiceImpl;
import solvd.service.impl.LoanServiceImpl;
import solvd.service.impl.TransactionServiceImpl;

public class Main {
    public static void main(String args[]) {
        /* Employee */

        //EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        //employeeService.save(new Employee("Mykola", "Litvin", "Loan processor", 27000f, 4)); //OK
        //Employee employee = employeeService.getById(5);
        //employee.setBranch_id(2); //OK
        //employeeService.update(employee, 5); //OK
        //employeeService.getAll().forEach(System.out::println); //OK

        /* AccountType */

        //AccountTypeServiceImpl service = new AccountTypeServiceImpl();
        //service.save(new AccountType("Current account")); //OK
        //service.update(new AccountType("Checking"), 5); //OK
        //service.getAll().forEach(System.out::println); //OK

        /* Account */

        //AccountServiceImpl accountService = new AccountServiceImpl();
        //System.out.println(accountService.getById(5).toString()); //OK
        //accountService.save(new Account(48888d, false, 1,  1)); //OK
        //accountService.update(new Account(48888d, false, 1,  1), 5); //OK
        //accountService.delete(10); //OK
        //System.out.println(accountService.getById(5)); //OK
        //accountService.getAll().forEach(System.out::println); //OK

        /* Branch */

        //BranchService branchService = new BranchServiceImpl();
        //branchService.save(new Branch("+380504350507","Lviv, Shevchenka street, 58")); //OK
        //branchService.update(new Branch("+380504350507","Lviv, Shevchenka street, 24b"), 5); //OK
        //branchService.delete(5); //OK
        //branchService.getAll().forEach(System.out::println); //OK

        /* AccountBranch */

        //AccountBranchServiceImpl accountBranchService = new AccountBranchServiceImpl();
        //System.out.println(accountBranchService.getById(4).toString()); //OK
        //new AccountBranchDao().insert(new AccountBranch(10, 5)); //OK
        //accountBranchService.getAll().forEach(System.out::println); //OK

        /* Login */

        //LoginService loginService = new LoginServiceImpl();
        //loginService.save(new Login("yartimosh95", "yt141095ua", 7)); //OK
        //loginService.update(new Login("timoshyar", "ytim8888", 7), 7); //OK
        //loginService.delete(7); //OK
        //loginService.getAll().forEach(System.out::println); //OK
        //System.out.println(loginService.getById(3).toString()); //OK

        /* Customer */

        //CustomerService customerService = new CustomerServiceImpl();
        //System.out.println(customerService.getById(7).toString()); //OK
        //System.out.println(customerService.save(new Customer("Eugen", "Timoshenko", "+380930250588"))); //OK
        //customerService.delete(8); //OK
        //customerService.update(new Customer("Yaroslav", "Vinichenko", "+380507528889"), 7); //OK
        //customerService.getAll().forEach(System.out::println); //OK

        /* Loan */

        //LoanService loanService = new LoanServiceImpl();
        //Loan loan = loanService.getById(5); //OK
        //loan.setEmployee_id(2);
        //loanService.update(loan, 5); //OK
        //loanService.save(new Loan(4905, "Active", 2, 1, 2)); //OK
        //loanService.getAll().forEach(System.out::println); //OK

        /* TransactionType */

        //TransactionTypeDao transactionTypeDao = new TransactionTypeDao();
        //transactionTypeDao.insert(new TransactionType("Wire transfer")); //OK
        //transactionTypeDao.delete(5); //OK + transactions with the same id deleted - OK!
        //TransactionType transactionType = transactionTypeDao.getEntityById(5); //OK
        //System.out.println(transactionType.toString());
        //transactionTypeDao.getAll().forEach(System.out::println); //OK

        /* Transaction */

        //TransactionService transactionService = new TransactionServiceImpl();
        //transactionService.getAll().forEach(System.out::println);
        //Transaction transaction = transactionService.getById(0); //OK
        //System.out.println(transaction.toString());
        //transaction.setTransactionType_id(5);
        //transactionService.update(transaction, 0); //OK
    }
}
