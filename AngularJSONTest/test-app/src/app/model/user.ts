export class User {
   public login: string;
   public pswd: string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
  }
}