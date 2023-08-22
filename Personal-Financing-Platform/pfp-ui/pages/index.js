import Head from 'next/head';
import Navbar from "../components/navbars/Navbar";
import Image from "next/image";
import React from "react";
export default function Home() {
    return (<div>
        <Head>
            <title>Personal Financing Platform</title>
        </Head>
        <Navbar/>
        <main>
            <h1 className="text-center text-gray-600 font-semibold text-4xl py-10 bg-gray-300 border-gray-800 rounded-xl shadow-2xl">Welcome to Personal Financing
                Platform</h1>
            <div className="px-20 place-content-center my-4 mx-2">
                <div className="flex px-14 py-16 items-center border-2 border-opacity-50 border-lime-800 rounded-xl bg-green-400 shadow-2xl">
                    <div className="mx-24">
                        <Image className="bg-white"
                               src="/MainPageScreen.png"
                               width={512}
                               height={512}
                               alt="Picture of Main Page"/>
                    </div>
                    <div>
                        <p className="text-center text-gray-800 font-semibold text-2xl mx-24">Main page where you arrived after successful log-in. <br/>
                            Here you can find all sections of our project, <br/>
                            buttons to Financial Goal, <br/>
                            Generate Expense report, <br/>
                            Generate Income report, <br/>
                            Monthly expense and Expense & Income</p>
                    </div>
                </div>
            </div>

            <div className="px-20 place-content-end my-4 mx-2">
                <div className="flex px-14 py-16 items-center border-2 border-opacity-50 border-yellow-600 rounded-xl bg-yellow-300 shadow-2xl">
                    <div>
                        <p className="text-center text-gray-800 font-semibold text-2xl mx-24">Financial Goal page <br/>
                            include all of your Goals in Bar Graph <br/>
                            and you can also manage it with six options in buttons.</p>
                    </div>
                    <div className="mx-24">
                        <Image className="bg-white"
                               src="/FinancialGoalScreen.png"
                               width={512}
                               height={512}
                               alt="Picture of Financial Goal page"/>
                    </div>
                </div>
            </div>

            <div className="px-20 place-content-end my-4 mx-2">
                <div className="flex px-14 py-16 items-center border-2 border-opacity-50 border-cyan-800 rounded-xl bg-cyan-500 shadow-2xl">
                    <div className="mx-24">
                        <Image className="bg-white"
                               src="/Monthly-expense-page.png"
                               width={512}
                               height={512}
                               alt="Picture of Expense Page"/>
                    </div>
                    <div>
                        <p className="text-center text-gray-800 font-semibold text-2xl mx-24">In Monthly expense screen you can see <br/>
                            everything about your expenses <br/>
                            for each month and each category of it <br/>
                            in two Pie Graph, you can select which month you want to see also.</p>
                    </div>
                </div>
            </div>

            <div className="px-20 place-content-end my-4 mx-2">
                <div className="flex px-14 py-16 items-center border-2 border-opacity-50 border-amber-800 rounded-xl bg-amber-400 shadow-2xl">
                    <div>
                        <p className="text-center text-gray-800 font-semibold text-2xl mx-24">Expense & Income page can explain you <br/>
                            how much you earn and <br/>
                            how much you spend through every month <br/>
                            and you can see it in Line Graph for better understanding.</p>
                    </div>
                    <div className="mx-24">
                        <Image className="bg-white"
                               src="/Expense&IncomeScreen.png"
                               width={512}
                               height={512}
                               alt="Picture of Expense Page"/>
                    </div>
                </div>
            </div>

            <div className="px-20 place-content-end my-4 mx-2">
                <div className="flex px-14 py-16 items-center border-2 border-opacity-50 border-red-800 rounded-xl bg-red-400 shadow-2xl">
                    <div>
                        <div className="ml-6 mr-2">
                            <Image className="bg-white"
                                   src="/GenerateExpenseReport.png"
                                   width={512}
                                   height={512}
                                   alt="Picture of Generate Report Page"/>
                        </div>
                    </div>
                    <div>
                        <div className="mr-24 ml-2">
                            <Image className="bg-white"
                                   src="/GenerateIncomeReport.png"
                                   width={512}
                                   height={512}
                                   alt="Picture of Generate Incomes Reposrt page"/>
                        </div>
                    </div>
                    <div>
                        <p className="text-center text-gray-800 font-semibold text-2xl mx-24">
                            In this two part of our application <br/>
                            you can create report about expenses and incomes. <br/>
                            You can chose start and end date for generating <br/>
                            exactly between this two days.</p>
                    </div>
                </div>
            </div>
        <div className="bg-gray-800">
            <h2 className="text-center text-amber-200 font-semibold text-2xl py-6">Developer who contribute on this
                project:</h2>
            <p className="text-center text-red-500 font-semibold text-xl py-4">Head of team: Bela Riboczki,</p>
            <p className="text-center text-gray-400 font-semibold py-1">Team member: Viktor Benedek,</p>
            <p className="text-center text-gray-400 font-semibold py-1">Team member: Erik Nagyfalusy,</p>
            <p className="text-center text-gray-400 font-semibold py-1">Team member: Levente Ács,</p>
            <p className="text-center text-gray-400 font-semibold py-1">Team member: Matej Thomka,</p>
            <p className="text-center text-gray-400 font-semibold py-1">Team member: Roman Milata.</p>
            <p className="text-center text-amber-200 font-semibold py-4">You can Sign in right now.</p>
        </div>
        </main>
    </div>)
}
