import { Account }    from './account';
import { TransactionType }    from './transactionType';
import { Category }    from './category';
import { TargetTransaction }    from './targetTransaction';
import { PeriodicTransaction }    from './periodicTransaction';

export class Transaction {
  public description: string;
  public value: number;
  public account: Account;
  public transactionType: TransactionType;
  public category: Category;
  public targetTransaction: TargetTransaction;
  public periodicTransaction: PeriodicTransaction;
  public id?: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}