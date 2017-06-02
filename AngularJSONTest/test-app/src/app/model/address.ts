import { Cpville }    from './cpville';
export class Address {
  constructor(
    public line1: string,
    public line2: string,
    public cpVille: Cpville,
    public id?: number
  ) {  }
}