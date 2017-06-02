import { Address }    from './address';
import { Bank }    from './bank';
export class Agency {
  constructor(
    public name: string,
    public counterCode: string,
    public bank: Bank,
    public address: Address,
    public id?: number
  ) {  }
}