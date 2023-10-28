import axios from "axios";

const BASE_URL = "http://localhost:8080/employee";
const GETALL_URL = "http://localhost:8080/employee/all";
const SAVE_URL="http://localhost:8080/employee/add"
const UPDATE_URL = "http://localhost:8080/employee/{id}/update"
const BYID_URL= "http://localhost:8080/employee/{id}";

class EmployeeService {

    getAllEmployee() {
        return axios.get(GETALL_URL)
    }

    saveEmployee(employeeData) {
        return axios.post(SAVE_URL, employeeData);
    }
    updateEmployee(id, employeeData){
        return axios.put(`${BASE_URL}/${id}/update`, employeeData);
    }
    getEmployeeById(id){
        return axios.get(`${BASE_URL}/${id}`);
    }
    deleteEmployee(id){
        return axios.delete(`${BASE_URL}/${id}/delete`);
    }
}

export default new EmployeeService();