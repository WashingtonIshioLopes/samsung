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
                    
                    console.log(BASE_URL  + '/productfeatured');
                    const response = await axios.get(BASE_URL  + '/productfeatured', config);
    
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
                        <Carousel.Item key={product.product.id}>
                                {/*
                                <img
                                    className="d-block carousel-img"
                                    src={product.product.images[0].image}
                                    alt={product.product.name}
                                />       
                                */}

                                {product?.product?.images?.[0]?.image ? (
                                <img
                                    className="d-block carousel-img"
                                    src={product.product.images[0].image}
                                    alt={product?.product?.name || ''}
                                />
                                ) : (
                                <div className="d-block carousel-img-placeholder">
                                    {product?.product?.name || 'No image available'}
                                </div>
                                )}

                                <Carousel.Caption className="carousel-caption-personal">
                                    <h3 style={{ color: 'black' }}>{product.product.name}</h3>
                                    <p style={{ color: 'black' }}>{product.product.description}</p>
                                </Carousel.Caption>  
                                
                        </Carousel.Item>
                    ))}
                </Carousel>
            </div>
        </>
    );
};

export default CarouselProducts;