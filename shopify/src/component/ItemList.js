import axios from "axios";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { BASE_URL } from "../const/Constants";
import AddInventoryItem from "./AddInventoryItem";
import DeletedItemList from "./DeletedItemList";

export default function ItemList(props) {

    const [tableData, setTableData] = useState([]);
    const [selectedItem, setSelectedItem] = useState({});
    const [hidden, setHidden] = useState(true);
    const { register, handleSubmit, reset } = useForm();
    const [comment, setComment] = useState();
    const [deleted, setDeleted] = useState(false);

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
        if (comment) {
            let deleteData = { id: id, deleteComment: comment }
            axios.post(BASE_URL + "/delete", deleteData)
                .then(response => {
                    setHidden(true);
                    setComment("");
                    getAll();
                    setDeleted(true);
                })
                .catch(err => { console.log(err) });
        }
        console.log(id);
    }

    const handleCommentChange = e => {
        setComment(e.target.value);
    }

    const handleDeleteFlagChange = flag => {
        setDeleted(flag);
    }

    const handleUndeleteFlag = (flag) => {
        if (flag) {
            getAll();
        }
    }

    return <>
        <hr />
        <AddInventoryItem flag={handleFlagChange} data={selectedItem} />
        <hr />
        <div hidden={hidden}>
            Please enter deletion comment: <input type="text" onChange={handleCommentChange}></input>
        </div>
        <h3> List of Added Inventory List </h3>
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
        <DeletedItemList isUndeleted={handleUndeleteFlag} deleted={deleted} deleteFlag={handleDeleteFlagChange} />
    </>
}