import { Frequency }    from './frequency';
export class PeriodicTransaction {
  constructor(
    public numberDefiningPeriodicity: number,
    public frequency: Frequency,
    public endDate: string,
    public id?: number
  ) {  }
}