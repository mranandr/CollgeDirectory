import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import '../Login.css'
import UserService from "./UserService"; // Assuming UserService is in a separate file

export default function Login() {
  const [loginForm, setLoginForm] = useState({
    username: "",
    password: "",
  });
  const navigate = useNavigate();

  const onChangeForm = (event) => {
    const { name, value } = event.target;
    setLoginForm({ ...loginForm, [name]: value });
  };

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    console.log(loginForm);

    try {
      const response = await axios.post("http://localhost:8081/api/users/login", loginForm, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      // Save token to local storage
      localStorage.setItem("auth_token", response.data.access_token);
      localStorage.setItem("auth_token_type", response.data.token_type);

      // Set user details using UserService
      UserService.setUser(response.data.user);

      // Add success notification
      toast.success(response.data.detail);

      // Redirect based on role
      if (response.data.user.role === "STUDENT") {
        navigate("./student-dashboard");
      } else {
        toast.error("Unexpected role. Please try again.");
      }
    } catch (error) {
      console.log(error);
      if (error.response && error.response.data) {
        toast.error(error.response.data.detail);
      } else {
        toast.error("An error occurred. Please try again.");
      }
    }
  };

  return (
    <div>
      <h1 className="text-3xl font-bold text-center mb-4 cursor-pointer">
        Welcome to profileViewer
      </h1>
      <p className="w-80 text-center text-sm mb-8 font-semibold text-gray-700 tracking-wide cursor-pointer mx-auto">
        Please login to your account!
      </p>
      <form onSubmit={onSubmitHandler}>
        <div className="space-y-4">
          <input
            type="text"
            name="username"
            placeholder="Username"
            className="block text-sm py-3 px-4 rounded-lg w-full border outline-none focus:ring focus:outline-none focus:ring-yellow-400"
            onChange={onChangeForm}
          />
          <input
            type="password"
            name="password"
            placeholder="Password"
            className="block text-sm py-3 px-4 rounded-lg w-full border outline-none focus:ring focus:outline-none focus:ring-yellow-400"
            onChange={onChangeForm}
          />
        </div>
        <div className="text-center mt-6">
          <button
            type="submit"
            className="py-3 w-64 text-xl text-white bg-yellow-400 rounded-2xl hover:bg-yellow-300 active:bg-yellow-500 outline-none"
          >
            Sign In
          </button>
          <p className="mt-4 text-sm">
            You don't have an account?{" "}
            <Link to="/register">
              <span className="underline cursor-pointer">Register</span>
            </Link>
          </p>
        </div>
      </form>
    </div>
  );
}
