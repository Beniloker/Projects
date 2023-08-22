import Head from 'next/head';
import UserList from "../components/main_components/UserList";
import NavbarOtherPages from "../components/navbars/NavbarOtherPages";

export default function Home() {
    return (<div>
        <Head>
            <title>Personal Financing Platform</title>
        </Head>
        <NavbarOtherPages/>
        <main>
            <h1 className="text-center text-gray-600 font-semibold text-2xl">Welcome to Personal Financing Platform
                User list page</h1>
            <UserList/>
        </main>

    </div>)
}