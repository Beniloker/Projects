import React, { useState } from "react";
import { Dialog, Transition } from "@headlessui/react";
import { Fragment } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import API_URLS_CONFIG from "../../API_URLS_CONFIG";

const RegisterForm = () => {
    const router = useRouter();
    const USER_API_REGISTER_URL = API_URLS_CONFIG.BASE_URL + "/api/serv/register";
    const [isOpen, setIsOpen] = useState(false);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [unauthorized, setUnauthorized] = useState(false);

    function closeModal() {
        setIsOpen(false);
        setEmail("");
        setPassword("");
        setFirstName("");
        setLastName("");
    }

    function openModal() {
        setIsOpen(true);
        setUnauthorized(false);
    }

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    }

    const handleFirstNameChange = (event) => {
        setFirstName(event.target.value);
    }

    const handleLastNameChange = (event) => {
        setLastName(event.target.value);
    }

    const registerUser = async (e) => {
        e.preventDefault();
        try {
            let response = await axios.post(USER_API_REGISTER_URL, {
                method: "POST",
                firstName,
                lastName,
                email,
                password,
            });

            const { token } = response.data;

            localStorage.setItem("token", token);
            localStorage.setItem("user-email", email);

            closeModal();

            if (email === "admin@gmail.com") {
                await router.replace("/admin-main-page");
            } else {
                await router.push("/user-main-page");
            }
        } catch (err) { // Change `error` to `err` for error handling
            console.error("Registration Error:", err);
            setUnauthorized(true);
        }
    };

    return (
        <>
            <div className="container mx-auto my-8">
                <div className="h-12">
                    <button
                        onClick={openModal}
                        className="rounded bg-slate-600 text-white px-6 py-2 font-semibold"
                    >
                        Register
                    </button>
                </div>
            </div>
            <Transition appear show={isOpen} as={Fragment}>
                <Dialog
                    as="div"
                    className="fixed inset-0 z-10 overflow-y-auto"
                    onClose={closeModal}
                >
                    <div className="min-h-screen px-4 text-center">
                        <Transition.Child
                            as={Fragment}
                            enter="ease-out duration-300"
                            enterFrom="opacity-0 scale-95"
                            enterTo="opacity-100 scale-100"
                            leave="ease-in duration-200"
                            leaveFrom="opacity-100 scale-100"
                            leaveTo="opacity-0 scale-95"
                        >
                            <div className="inline-block w-full max-w-md p-6 my-8 overflow-hidden text-left align-middle transition-all transform bg-white shadow-xl rounded-md">
                                <Dialog.Title
                                    as="h3"
                                    className="text-lg font-medium leading-6 text-gray-900"
                                >
                                    Registration Page
                                </Dialog.Title>
                                <div className="flex max-w-md max-auto">
                                    <div className="py-2">
                                        <div className="h-14 my-4">
                                            <label
                                                className="block text-gray-600 text-sm font-normal"
                                            >
                                                First Name
                                                <input
                                                    type="text"
                                                    name="firstName"
                                                    value={firstName}
                                                    onChange={handleFirstNameChange}
                                                    className="h-10 w-96 border mt-2 px-2 py-2"
                                                    required
                                                ></input>
                                            </label>
                                        </div>
                                        <div className="h-14 my-4">
                                            <label
                                                className="block text-gray-600 text-sm font-normal"
                                            >
                                                Last Name
                                                <input
                                                    type="text"
                                                    name="lastName"
                                                    value={lastName}
                                                    onChange={handleLastNameChange}
                                                    className="h-10 w-96 border mt-2 px-2 py-2"
                                                    required
                                                ></input>
                                            </label>
                                        </div>
                                        <div className="h-14 my-4">
                                            <label
                                                className="block text-gray-600 text-sm font-normal"
                                            >
                                                E-mail
                                                <input
                                                    type="email"
                                                    name="email"
                                                    value={email}
                                                    onChange={handleEmailChange}
                                                    className="h-10 w-96 border mt-2 px-2 py-2"
                                                    required
                                                ></input>
                                            </label>
                                        </div>
                                        <div className="h-14 my-4">
                                            <label
                                                className="block text-gray-600 text-sm font-normal"
                                            >
                                                Password
                                                <input
                                                    type="password"
                                                    name="password"
                                                    value={password}
                                                    onChange={handlePasswordChange}
                                                    className="h-10 w-96 border mt-2 px-2 py-2"
                                                    required
                                                ></input>
                                            </label>
                                        </div>
                                        {unauthorized ? (
                                            <p className="my-4 text-red-500">Registration Failed!</p>
                                        ) : (
                                            <p></p>
                                        )}
                                        <div className="h-14 my-4 space-x-4 pt-4">
                                            <button
                                                type="submit"
                                                onClick={registerUser}
                                                className="rounded bg-green-400 hover-bg-green-700 text-white px-6 py-2 font-semibold"
                                            >
                                                Register
                                            </button>
                                            <button
                                                onClick={closeModal}
                                                className="rounded bg-red-400 hover-bg-red-700 text-white px-6 py-2 font-semibold"
                                            >
                                                Close
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </Transition.Child>
                    </div>
                </Dialog>
            </Transition>
        </>
    );
};

export default RegisterForm;