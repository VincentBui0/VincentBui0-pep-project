package Service;

import DAO.AccountDAO;
import Model.Account;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;
    /**
     * no-args constructor for creating a new AccountService with a new AccountDAO.
     * There is no need to change this constructor.
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    /**
     * Constructor for a AccountService when a AccountDAO is provided.
     * This is used for when a mock AccountDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of AccountService independently of AccountDAO.
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * ## 1: Our API should be able to process new User registrations.
     * TODO: Use the AccountDAO to add a new account to the database.
     *
     * @param account an object representing a new Account.
     * @return the newly added account if the add operation was successful, including the account_id. We do this to
     *         inform our provide the front-end client with information about the added Account.
     */
        public Account addAccount(Account account){
            if((account.getUsername()).length() == 0 || (account.getPassword()).length() < 4 || accountDAO.getAccount(account) != null) {
                return null;
            }
            return accountDAO.addAccount(account);
        }

     /*
     ## 2: Our API should be able to process User logins.
     ** TODO: Use the AccountDAO to persist an account. The given Account will not have an id provided.
     *
     * @param account an account object.
     * @return The new user if login is successful.
     */
    public Account getAccount(Account account) {
        return accountDAO.getAccount(account);
    }
}
