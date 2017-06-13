export class User {
   public login: string;
   public pswd: string;
   public id?:number;

    constructor(values: Object = {}) {
        Object.assign(this, values);
  }
}