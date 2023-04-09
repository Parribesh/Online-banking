TODO:

Role:

1) Create a jsp file (for ex. roleForm.jsp)

2) Create RoleRepository interface that extens JpaRepositry.

3) Create RoleService interface with CRUD methods that work on role entity.

4) Create RoleServiceImpl class that implements RoleService. Autowire RoleRepositry and provide the implementation for the  methods.

5) Create RoleController and go for the methods that will allow to submit the role form, save, delete and update role entity.

Method for updating the role entity is not required as this does not make much sense.

6) Create RoleValidator that will validate the input on the role form.

7) Display maximum available roleId whenever blank role form is loaded.

8) Display a list of available roles on CRUD operation as well as when blank role form is loaded.

=================================================================

User:

1) Create a jsp file (for ex. userForm.jsp)

2) Create UserRepository interface that extens JpaRepositry.

3) Create UserService interface with CRUD methods that work on user entity(User.java).

4) Create UserServiceImpl class that implements UserService. Autowire UserRepositry and provide the implementation for the  methods.

5) Create UserController and go for the methods that will allow to submit the user form, save, delete and update user entity.

When you update the user the roles assgined to the user  should be updatable.

6) Create UserValidator that will validate the input on the user form.

7) Display maximum available userId whenever blank user form is loaded.

8) Display a list of available users on each CRUD operation as well as when blank user form is loaded.

==================================================================

Customer:

A customer should only be created for available user only. Through validation you must  restrict the creation of Customer object.

For one user go for one customer.

1) Create a jsp file to create a customer form using Spring's form tag library.

2)Create Customer Repository that extends JpaRepository.

3)Create Customer service with CRUD methods for customer entity.

4) Create CustomerServiceImpl class that implements CustomerService.

Autowire CustomerRepositry and provide the implementation for the  methods.

5) Create CustomerController and go for the methods that handle Customer entity.

6) Create CustomerValidator that will validate the input on the customer form.

7) Display maximum available customerId whenever blank customer form is loaded.

9) Display a list of available users on each CRUD operation as well as when blank customer form is loaded.

Beside update/delete links you can provide a link for creating the account that will bring up the account form.

=================================================================

Account:

An account should be opened for an existing customer. A customer is always associated with one userId. A customer can have any number of accounts.

1) create a jsp file to associate it with Account entity, for example accountFor.jsp.

2) Create an interface  AccountRepository that extends JpaRepository.

3) Create AccountService interface that declares CRUD methods for Account entity.

4) Create AccountServiceImpl that implements Create AccountService. Autowire AccountRepository and use it in methods of AccountServiceImpl.

5)Create AccountController class annotated with @Controller and go for the methods that work on Account entity. Annotate methods with @RequestMapping ( you can also use @GetMapping or @PostMapping)

6) Create AccountValidator class that implements Spring's Validator interface. Annotate the class with @Component. Use this validator in your AccountController.

7) Display a list of all available accounts when CRUD methods are activated on account entity.

Note: when you implement security, then the logged in user if not admin/manager will see only his/her accounts displayed not others. Logged in Manager/Admin user will be able to see all the accounts.

Similarly, logged-in user if not admin/manager will see only the listing of branches but not the branch form, non manager/admin users should not be able to create/update/delete the branches.

Every logged-in user should be able to modify his/her user details, customer details. But don't allow them to delete. Similarly user should not be able to delete his/her account. don't provide delete link to normal user. manager/Admin will see the delete link also.

 

=============================================================================

Deposit/Withdrawal/Transfer:

As per your taste and convenience you can use different jsp files for these three functionalities, or you can implement them on same jsp.

1) The logged-in user can deposit in his/her account. This should reflect in historical table BranchTransation.

2) The logged-in user can withdraw from his/her account provided sufficient balance exist in the account.

The jsp page should display the withdrawal amount and existing amount. This should reflect in historical table BranchTransation.

3) The logged-in user can transfer from his account to any other account provided logged-in user has sufficient balance.

Jsp page should display the amount of money transferred and existing balance in the user's account only.

This should reflect in historical table BranchTransation.

4) The accounts should display the existing balance.

5) Go for validation. Only  existing accounts should be used. Non-existing account should not let submit the form.

===============================================================================================================================================

Transaction:

Admin user can see the transaction details of any account but non admin user will see the transactions of his/her account only.

1. Display all transaction for an account for a particular day of month.

2. Display all transactions of an account for a given period.
