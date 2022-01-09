import React from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import { BASE_URL } from "../const/Constants";

export default function AddInventoryItem(props) {
    const { register, handleSubmit, reset } = useForm();
    const onsubmit = data => {
        if (props.data.id) {
            data.id = props.data.id;
            data.name = data.name || props.data.name;
            data.pricePerUnit = data.pricePerUnit || props.data.pricePerUnit;
            data.availableStock = data.availableStock || props.data.availableStock;
            data.category = data.category || props.data.category;
            data.description = data.description || props.data.description;
        }
        console.log(data.name);
        axios.post(BASE_URL, data)
            .then(response => {
                console.log(response.data);
                reset();
                props.flag(true);
            })
            .catch(err => { console.log(err) });
    }
    return <>
        <form onSubmit={handleSubmit(onsubmit)}>
            Name <input type="text" defaultValue={props?.data?.name} {...register("name")} />
            Price per unit <input type="number" defaultValue={props?.data?.pricePerUnit} {...register("pricePerUnit")} />
            Available stock <input type="number" defaultValue={props?.data?.availableStock} {...register("availableStock")} />
            Category <input type="text" defaultValue={props?.data?.category} {...register("category")} />
            Description <input type="text" defaultValue={props?.data?.description} {...register("description")} />
            <button type="submit">{props.data.id ? "Update" : "Add"}</button>
        </form>
    </>
}