import axios from "axios";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { BASE_URL } from "../const/Constants";
import AddInventoryItem from "./AddInventoryItem";

export default function ItemList(props) {

    const [tableData, setTableData] = useState([]);
    const [selectedItem, setSelectedItem] = useState({});
    const [hidden, setHidden] = useState(true);
    const {register, handleSubmit, reset} = useForm();

    useEffect(() => {
        getAll();
    }, []);

    const getAll = () => {
        axios.get(BASE_URL)
            .then(response => {
                console.log(response.data);
                setTableData(response.data.data);
            })
            .catch(err => { console.log(err) });
    }

    const handleFlagChange = flag => {
        if (flag) {
            getAll();
            setSelectedItem({});
        }
    }

    const handleEdit = id => {
        axios.get(BASE_URL + "/" + id)
            .then(response => {
                console.log(response.data);
                setSelectedItem(response.data.data);
            })
            .catch(err => { console.log(err) });
        console.log(id);
    }

    const handleDelete = id => {
        setHidden(false);
        console.log(id);
    }

    return <>
        <hr />
        <AddInventoryItem flag={handleFlagChange} data={selectedItem} />
        <hr />
        <div hidden={hidden}>
            Please enter deletion comment<input type="text"></input>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price Per Unit</th>
                    <th>Available Stock</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {tableData.map(item => {
                    return <tr key={item.id}>
                        <td>{item.name}</td>
                        <td>{item.pricePerUnit}</td>
                        <td>{item.availableStock}</td>
                        <td>{item.category}</td>
                        <td>{item.description}</td>
                        <td><button onClick={() => handleEdit(item.id)}>Edit</button>
                            <button onClick={() => handleDelete(item.id)}>Delete</button>

                        </td>
                    </tr>
                })}
            </tbody>
        </table>
    </>
}