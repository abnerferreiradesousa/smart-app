"use client"
import FormUser from "./components/FormUser";

export default function Home() {
  const handleUser = (email: string, password: string) => {

  }

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <FormUser
        title="Sign In"
        buttonText="LOGIN"
        linkText="Ainda não tem conta?"
        linkPath="/CreateAccount"
        handleUser={handleUser}
      />
    </main>
  )
}

// pages/example.js

// import React from 'react';

// const ExamplePage = ({ data }) => {
//   return (
//     <div>
//       <h1>Data from Specific URL</h1>
//       <pre>{JSON.stringify(data, null, 2)}</pre>
//     </div>
//   );
// };

// export async function getServerSideProps() {
//   // Fetch data from a specific URL
//   const apiUrl = 'https://api.example.com/data';
//   const response = await fetch(apiUrl);
//   const data = await response.json();

//   return {
//     props: {
//       data,
//     },
//   };
// }

// export default ExamplePage;
