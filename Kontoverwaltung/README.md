<div align="center">
  <img src="https://img.icons8.com/color/96/000000/java-coffee-cup-logo.png" alt="Java Icon">
</div>

# Bank Account Management System (Java Console Application)

A simple console application for managing bank accounts, written in Java. This application allows users to create, delete, deposit, withdraw, transfer funds between accounts, and view bank statements.

## Getting Started

To run this application, ensure you have Java installed on your computer. Follow these steps to get started:

1. **Clone** this repository to your local machine:

   ```shell
   git clone https://github.com/Jenby32/Kontoverwaltung/

2. **Navigate** to the project directory:
   ```shell
   cd bank-account-management

3. **Compile and run** the **App.java** file:
    ```shell
    javac App.java
    java App
    ```

## Features
### **1**.
**Create Bank Accounts:** Users can create three types of bank accounts: Girokonto, Sparkonto, and Kreditkonto. They provide account holder information, including name, bank code (BLZ), account number, overdraft limit, and account maintenance fees.

### 2. 
**Delete Bank Accounts:** Users can delete existing bank accounts, but accounts with a negative balance cannot be deleted.

### 3.
**Select and Manage Accounts:** Users can select a bank account to perform various actions like deposit, withdraw, view the bank statement, or transfer funds to other accounts.

### 4.
**Deposit Funds:** Users can deposit money into their selected bank account and see the updated balance.

### 5.
**Withdraw Funds:** Users can withdraw money from their selected bank account, provided they have sufficient funds.

### 6.
**View Bank Statement:** Users can view the bank statement of their selected account, displaying the transaction history.

### 7.
**Transfer Funds:** Users can transfer money from one account to another by specifying the recipient's account number. The application checks for account existence and sufficient balance.

## Usage
Follow the on-screen prompts in the console to navigate through the application's menu and perform desired actions.

## Contributing
Contributions to this project are welcome! Feel free to fork this repository, make improvements, and submit pull requests.
