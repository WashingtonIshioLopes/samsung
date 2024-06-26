import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate } from 'react-router-dom';
import { Carousel } from 'react-bootstrap';
import axios from 'axios';

import './CarouselProducts.css'
import BASE_URL from '../../config';

const CarouselProducts = (props) => {

    const [products, setProducts] = useState([]);

    //const { token } = props;

    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');

    const initialFetch = useRef(true);

    useEffect(() => {

        if (initialFetch.current) {
            initialFetch.current = false;

            const config = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            };
    
            const fetchProducts = async () => {
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
    
            fetchProducts();

        }

    }, []);

    return (
        <>
            <div className="full-width-carousel">
                <Carousel>
                    {products.slice(0, 10).map(product => (
                        <Carousel.Item key={product.id}>
                            <img
                                className="d-block w-100"
                                src={product.images[0].image}
                                alt={product.name}
                            />
                            <Carousel.Caption>
                                <h3>{product.name}</h3>
                                <p>{product.description}</p>
                            </Carousel.Caption>
                        </Carousel.Item>
                    ))}
                </Carousel>
            </div>
        </>
    );
};

export default CarouselProducts;