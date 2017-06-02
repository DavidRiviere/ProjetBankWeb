export class Category {
  constructor(
    public description: string,
    public parentCategory?: Category,
    public id?: number
  ) {  }
}