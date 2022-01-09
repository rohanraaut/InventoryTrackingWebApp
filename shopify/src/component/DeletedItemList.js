import axios from "axios";
import React, { useEffect, useState } from "react";
import { BASE_URL } from "../const/Constants";

export default function DeletedItemList(props) {

    const [tableData, setTableData] = useState([]);
    useEffect(() => {
        getDeletedItems();
    }, []);

    useEffect(() => {
        if (props.deleted) {
            getDeletedItems();
            props.deleteFlag(false);
        }
    }, [props.deleted]);

    const getDeletedItems = () => {
        axios.get(BASE_URL + "/deleted-items")
            .then(response => {
                console.log(response.data);
                setTableData(response.data.data);
            })
            .catch(err => { console.log(err) });
    }

    const handleUndelete = (id) => {
        axios.get(BASE_URL + "/undelete?id=" + id)
            .then(response => {
                console.log(response.data);
                props.isUndeleted(true);
                getDeletedItems();
            })
            .catch(err => { console.log(err) });
    }

    return <>
        <hr />
        <h3> Deleted Items </h3>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Comment </th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {tableData.map(item => {
                    return <tr key={item.id}>
                        <td>{item.name}</td>
                        <td>{item.description}</td>
                        <td>{item.deleteComment}</td>
                        <td><button onClick={() => handleUndelete(item.id)}>Undelete</button></td>
                    </tr>
                })}
            </tbody>
        </table>
    </>
}