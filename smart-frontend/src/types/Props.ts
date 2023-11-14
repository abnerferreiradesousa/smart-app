export interface IFormUserProps {
  buttonText: string
  linkPath: string
  linkText: string
  title: string
  handleUser(email: string, senha: string): void
}
