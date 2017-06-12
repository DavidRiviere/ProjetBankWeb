import { Account }    from './account';
import { Address }    from './address';
export class Owner {
    public name: string;
    public firstName: string;
    public birthday: string;
    public pswd: string;
    public salt: string;
    public email: string;
    public phoneNumber: string;
    public login: string;
    public address: Address;
    public newUser: boolean;
    public accounts: Array<Account>;
    public id?: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}