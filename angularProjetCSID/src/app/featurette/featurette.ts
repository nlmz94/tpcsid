export class Featurette {
  private name1: string;
  private text1: string;

  constructor(name1: string, text1: string) {
    this.name1 = name1;
    this.text1 = text1;
  }
  get name(): string { return this.name1; }
  get text(): string { return this.text1; }
}
