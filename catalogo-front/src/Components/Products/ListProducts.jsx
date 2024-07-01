import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


import BASE_URL from '../../config';
import Product from './Product';

const ListProducts = (props) => {

    const [products, setProducts] = useState([]);

    const { token } = props;

    const initialFetch = useRef(true);

    useEffect(() => {

        if (initialFetch.current) {
            initialFetch.current = false;

            const config = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            };
    
            const fetchData = async () => {
                try {
                    
                    console.log(BASE_URL  + '/products');
                    const response = await axios.get(BASE_URL  + '/products', config);
    
                    if (response.status === 200) {
                        console.log('Lista de Produtos.');
                        console.log(response.data);
                        setProducts(response.data);
                    } else {
                        alert('Erro em busca Produtos. Por favor, tente novamente.');
                    }
                } catch (error) {
                    console.error('Erro ao buscar dados:', error);
                }
            };
    
            fetchData();

        }

    }, []);

    return (
        <p>Products List</p>
    );
};

export default ListProducts;