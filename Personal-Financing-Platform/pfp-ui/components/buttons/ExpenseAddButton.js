import React, {Fragment, useState} from 'react';
import axios from 'axios';
import {Dialog, Transition} from "@headlessui/react";
import Image from 'next/image'

const ExpenseAddButton = () => {
    //const [report, setReport] = useState(null);
    const [isOpen, setIsOpen] = useState(false);
    const [expenseAdd, setExpenseAdd] = useState('');
    const [categoryAdd, setCategoryAdd] = useState('');
    const [transactionDate, setTransactionDate] = useState('');
    // const [newExpense, setNewExpense] = useState('');
    const [file, setFile] = useState(null);
    const [isHovering, setIsHovering] = useState(false);

    const onMouseEnter = () => setIsHovering(true);
    const onMouseLeave = () => setIsHovering(false);

    function closeModal() {
        setIsOpen(false);
    }

    function openModal() {
        setIsOpen(true);
    }

    const handleTransactionDateChange = (event) => {
        setTransactionDate(event.target.value);
    }

    const handleExpenseAddChange = (event) => {
        setExpenseAdd(event.target.value);
    }

    const handleCategoryAddChange = (event) => {
        setCategoryAdd(event.target.value);
    }

    const addExpense = async () => {
        const [newExpense, setNewExpense] = useState({
            expense: '',
            category: '',
            transactionDate: '',
        });

        try {
            const response = await axios.post('http://127.0.0.1:8080/api/expense/add', newExpense, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    Authorization: 'Bearer ' + localStorage.getItem('token'),
                },
                body: JSON.stringify(newExpense),
            });

            if (response.ok) {
                const data = await response.json();
                // Handle successful response here
                console.log(data);
            } else {
                // Handle error response here
                console.error('Failed to add expense:', response.status);
            }
        } catch (error) {
            console.error('Failed to add expense:', error);
        }
    };

//     return (
//         <>
//             <div className="container mx-auto my-8">
//                 <div className="h-12"
//                      onMouseEnter={onMouseEnter}
//                      onMouseLeave={onMouseLeave}
//                 >
//                     <button
//                         onClick={openModal}
//                         className="rounded bg-white text-black px-6 py-2 font-semibold w-[auto]">
//                         {isHovering ? (
//                             <Image
//                                 className="bg-white"
//                                 src="/folder_transparent.gif"
//                                 width={256}
//                                 height={256}
//                                 alt="Picture of Generate expense report"
//                             />
//                         ) : (
//                             <Image
//                                 className="bg-white"
//                                 src="/folder.png"
//                                 width={256}
//                                 height={256}
//                                 alt="Picture of Generate expense report"
//                             />
//                         )}
//                         Add Expense
//                     </button>
//                 </div>
//             </div>
//             <Transition appear show={isOpen} as={Fragment}>
//                 <Dialog
//                     as="div"
//                     className="fixed inset-0 z-10 overflow-y-auto"
//                     onClose={closeModal}>
//                     <div className="min-h-screen px-4 text-center">
//                         <Transition.Child as={Fragment}
//                                           enter="ease-out duration-300"
//                                           enterFrom="opacity-0 scale-95"
//                                           enterTo="opacity-100 scale-100"
//                                           leave="ease-in duration-200"
//                                           leaveFrom="opacity-100 scale-100"
//                                           leaveTo="opacity-0 scale-95">
//                             <div
//                                 className="inline-block w-full max-w-md p-6 my-8 overflow-hidden text-left align-middle transition-all transform bg-white shadow-xl rounded-md">
//                                 <Dialog.Title
//                                     as="h3"
//                                     className="text-lg font-medium leading-6 text-gray-900">
//                                     Generate Expense Report page
//                                 </Dialog.Title>
//                                 <div className="flex max-w-md max-auto">
//                                     <div className="py-2">
//                                         <div className="h-14 my-4">
//                                             <label className="block text-gray-600 text-sm font-normal">
//                                                 Start Date
//                                             </label>
//                                             <input
//                                                 type="date"
//                                                 name="startDate"
//                                                 defaultValue={startDate}
//                                                 onChange={handleStartDateChange}
//                                                 className="h-10 w-96 border mt-2 px-2 py-2">
//                                             </input>
//                                         </div>
//                                         <div className="h-14 my-4">
//                                             <label className="block text-gray-600 text-sm font-normal">
//                                                 End Date
//                                             </label>
//                                             <input
//                                                 type="date"
//                                                 name="endDate"
//                                                 defaultValue={endDate}
//                                                 onChange={handleEndDateChange}
//                                                 className="h-10 w-96 border mt-2 px-2 py-2">
//                                             </input>
//                                         </div>
//                                         <style jsx>{`
//                                       .report-container {
//                                         background-color: #f2f2f2;
//                                         border: 1px solid #ccc;
//                                         padding: 10px;
//                                         font-family: Arial, sans-serif;
//                                         font-size: 14px;
//                                         color: #333;
//                                       }
//                                     `}</style>
//
//                                         <div className="report-container">
//                                             € {report}
//                                         </div>
//                                         <div className="h-14 my-4 space-x-4 pt-4">
//                                             <button onClick={generateExpenseReport}
//                                                     className="rounded bg-green-400 hover:bg-green-700 text-white px-6 py-2 font-semibold">
//                                                 Generate!
//                                             </button>
//                                             <button onClick={closeModal}
//                                                     className="rounded bg-red-400 hover:bg-red-700 text-white px-6 py-2 font-semibold">
//                                                 Close!
//                                             </button>
//                                         </div>
//                                     </div>
//
//                                 </div>
//                             </div>
//                         </Transition.Child>
//                     </div>
//                 </Dialog>
//
//             </Transition>
//
//         </>);
// };

    return (
        <>
            <div className="container mx-auto my-8">
                <div
                    className="h-12"
                    onMouseEnter={onMouseEnter}
                    onMouseLeave={onMouseLeave}
                >
                    <button
                        onClick={openModal}
                        className="rounded bg-white text-black px-6 py-2 font-semibold w-[auto]">
                        {isHovering ? (
                            <Image
                                className="bg-white"
                                src="/payment.gif"
                                width={256}
                                height={256}
                                alt="Picture of Generate expense report"
                            />
                        ) : (
                            <Image
                                className="bg-white"
                                src="/payment.png"
                                width={256}
                                height={256}
                                alt="Picture of Generate expense report"
                            />
                        )}
                        Add Expense
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
                            <div
                                className="inline-block w-full max-w-md p-6 my-8 overflow-hidden text-left align-middle transition-all transform bg-white shadow-xl rounded-md"
                            >
                                <Dialog.Title
                                    as="h3"
                                    className="text-lg font-medium leading-6 text-gray-900"
                                >
                                    Add Expense
                                </Dialog.Title>
                                <div className="mt-2">
                                    {/* Your form fields go here */}
                                    <form>
                                        {/* Example field */}
                                        <label htmlFor="expense" className="block text-sm font-medium text-gray-700">
                                            Expense
                                        </label>
                                        <input
                                            type="text"
                                            name="expense"
                                            id="expense"
                                            className="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                                        />
                                        {/* More fields */}
                                    </form>
                                </div>
                                <div className="mt-5 sm:mt-6">
                                    <button
                                        onClick={addExpense}
                                        className="rounded bg-blue-500 hover:bg-blue-700 text-white px-6 py-2 font-semibold"
                                    >
                                        Submit
                                    </button>
                                </div>
                            </div>
                        </Transition.Child>
                    </div>
                </Dialog>
            </Transition>
        </>
    );
};

export default ExpenseAddButton;