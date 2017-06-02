export class Account {
  constructor(
    public description: string,
    public creationDate: Date,
    public number: string,
    public initialBalance: number
  ) {  }
}