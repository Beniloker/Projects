import React from 'react'
import SignIn from "../buttons/SignIn";
import RegisterForm from "../buttons/RegisterForm";

const Navbar = () => {

    return (<div className="bg-gray-800">
        <div className="h-16 px-8 flex items-center">
            <p className="text-white font-bold flex-auto">
                Personal Financing Platform
            </p>
            <div className="flex px-2">
                <RegisterForm/>
            </div>
            <div className="flex px-2">
                <SignIn/>
            </div>

        </div>
    </div>);
};

export default Navbar;