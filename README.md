## Expense Tracker Application

A readme that will be changed in the future.

## Classes

### AccountManager
- loggedInAccount: `Account`
- accounts: `ArrayList<Account>`
- private findAccount(name: string): `Account`
- public login(name: string, password: string): `boolean`
- public addAccount(name: string, password: string): `boolean`

### Account
- username: `string`
- password: `string`
- expenditures: `ArrayList<Expenditure>`
- expenditureCategories: `ArrayList<ExpenditureCategory>`
- public authenticate(password: string): `boolean`
- public addExpenditure(Expenditure expenditure): void
- public addExpenditureCategory(ExpenditureCategory expenditureCategory): void
- public getExpenditureCategory(expenditureCategoryId: `Integer`): ExpenditureCategory

### Expenditure
- expenditureCategoryId: `Integer`
- amount: `float`
- date: `string`

### ExpenditureCategory
- name: `string`
- description: `string`

