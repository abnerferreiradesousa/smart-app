import { FormEvent, useState } from "react";
import { IFormUserProps } from "@/types/Props";
import Link from "next/link";

const FormUser = ({ buttonText, linkPath, linkText, title, handleUser }: IFormUserProps) => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [name, setName] = useState<string>("");


  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();

    if (!email && !password) {
      handleUser(email, password);
    }

    localStorage.setItem("isLogged", "yes");
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="flex flex-col items-center gap-8 border border-gray-300 p-10 rounded-lg"
    >
      <h1 className="text-xl">{title}</h1>
      {/* <input
        type="Name"
        id="Name"
        name="Name"
        placeholder="Name"
        onChange={(e) => setName(e.target.value)}
      /> */}
      <input
        type="email"
        id="email"
        name="email"
        placeholder="Email"
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        id="password"
        name="password"
        placeholder="Senha"
        onChange={(e) => setPassword(e.target.value)}
      />
      <button type="submit" className="bg-blue-500 text-white w-full py-3 rounded-md">{buttonText}</button>
      <Link href={linkPath}>
        {linkText}
      </Link>
    </form>
  )
}

export default FormUser;