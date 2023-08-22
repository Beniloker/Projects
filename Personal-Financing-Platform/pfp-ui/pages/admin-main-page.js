import Head from 'next/head';
import FinancialGoalButton from "../components/buttons/FinancialGoalButton";
import NavbarAdmin from "../components/navbars/NavbarAdmin";
import GenerateExpenseReport from "../components/buttons/GenerateExpenseReport";
import GenerateIncomeReport from "../components/buttons/GenerateIncomeReport";
import MonthlyExpenseButton from "../components/buttons/MonthyExpenseButton";
import ExpenseIncomeButton from "../components/buttons/Expense&IncomeButton";
import ExpenseAddButton from "../components/buttons/ExpenseAddButton";

export default function Home() {
    return (<div>
        <Head>
            <title>Personal Financing Platform</title>
        </Head>
        <NavbarAdmin/>
        <main>
            <h1 className="text-center text-gray-600 font-semibold text-2xl">Welcome to Personal Financing Platform
                Admin main page</h1>
            <div className="items-center flex px-20 place-content-center">
                <div className="flex px-2 items-center ">
                    <FinancialGoalButton/>
                </div>
                <div className="flex px-2 items-center">
                    <GenerateExpenseReport/>
                </div>
                <div className="flex px-2 items-center">
                    <GenerateIncomeReport/>
                </div>
                <div className="flex px-2 items-center">
                    <ExpenseIncomeButton/>
                </div>
                <div className="flex px-2 items-center">
                    <MonthlyExpenseButton/>
                </div>
                <div className="flex px-2 items-center">
                    <ExpenseIncomeButton/>
                </div>
                <div className="flex px-2 items-center">
                    <ExpenseAddButton/>
                </div>
            </div>
        </main>


    </div>)
}