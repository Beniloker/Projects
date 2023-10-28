import HeaderComponent from "./component/HeaderComponent";
import FooterComponent from "./component/FooterComponent";
import ListEmployeeComponent from "./component/ListEmployeeComponent";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import AddEmployeeComponent from "./component/AddEmployeeComponent";
import addEmployeeComponent from "./component/AddEmployeeComponent";

function App() {
    return (
        <div>
            <BrowserRouter>

                <HeaderComponent/>
                <div className="container">
                    <Routes>
                        <Route path="/" element={<ListEmployeeComponent/>}/>
                        <Route path="/employee" element={<ListEmployeeComponent/>}/>
                        <Route path="/add-employee" element={<AddEmployeeComponent/>}/>
                        <Route path="/add-employee/:id" element={<AddEmployeeComponent/>}/>
                    </Routes>
                </div>
                <FooterComponent/>
            </BrowserRouter>
        </div>
    );
}

export default App;
