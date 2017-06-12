export class TransactionType {
    public description: string,
    public id?: number

    constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}