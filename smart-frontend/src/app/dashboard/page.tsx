"use client"
import { useRouter } from "next/navigation";
import { useEffect } from "react";

const Dashboard = ({ isAuthenticated }: { isAuthenticated: string }) => {
  const router = useRouter();

  useEffect(() => {
    // If not authenticated, redirect to login
    if (!isAuthenticated) {
      // You can choose to redirect to a login page or any other page
      router.replace('/');
    }
  }, [isAuthenticated]);

  if (!isAuthenticated) {
    // You can choose to render a loading state or null
    return null;
  }

  return (
    <div>Dashboard</div>
  )
}

Dashboard.getInitialProps = async (context: any) => {
  const isAuthenticated = localStorage.getItem("isLogged");

  return { isAuthenticated };
}

export default Dashboard;