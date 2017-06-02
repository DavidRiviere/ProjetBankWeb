export class TargetTransaction {
  constructor(
    public summary: string,
    public iban: string,
    public id?: number
  ) {  }
}