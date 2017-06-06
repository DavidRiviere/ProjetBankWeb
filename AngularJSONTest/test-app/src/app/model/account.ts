import { CountryCode }    from './countryCode';
import { Agency }    from './agency';
import { AccountType }    from './AccountType';
import { Owner }    from './owner';
import { Transaction }    from './transaction';
export class Account {

  constructor(
    public description?: string,
    public creationDate?: string,
    public number?: string,
    public initialBalance?: number,
    public interestRate?: number,
    public agioRate?: number,
    public overdraft?: number,
    public countryCode?: CountryCode,
    public agency?: Agency,
    public accountType?: AccountType,
    public owners?: Array<Owner>,
    public transactions?: Array<Transaction>,
    public alertThreshold?: number

  ) {  }
}