import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

import Cart from '../Shopping/Cart';
import './DetailsProduct.css'
import BASE_URL from '../../config';

const DetailsProduct = () => {

    const [product, setProduct] = useState(null);
    const [mainImage, setMainImage] = useState("");

    const initialFetch = useRef(true);

    const { state } = useLocation();

    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');

    console.log();

    useEffect(() => {

        if (initialFetch.current) {
            initialFetch.current = false;

            const config = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            };
    
            const fetchProduct = async () => {
                try {
                    
                    console.log(BASE_URL  + `/products/${state.product_id}`);
                    const response = await axios.get(BASE_URL + `/products/${state.product_id}`, config);
    
                    if (response.status === 200) {
                        console.log('Produto: ....... ');
                        console.log(response.data);
                        setProduct(response.data);
                        setMainImage(response.data.images[0].image);

                    } else {
                        alert('Erro em busca do Produto. Por favor, tente novamente.');
                    }
                } catch (error) {
                    console.error('Erro ao buscar dados:', error);
                }
            };

            fetchProduct();

        }

    }, []);

    const handleClick = () => {
        console.log('Click Voltar!');

        navigate('/catalog');
    }

    if (!product) {
        return <p>Carregando detalhes do produto...</p>;
    }

    const handleThumbnailClick = (imageUrl) => {
        setMainImage(imageUrl);
    };

    const handleAddCart = (product_id) => {
        
        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            },
            params: {
                user_id: user,
                total: 0,
                status: "open"
            }
        };

        const addProduct = async () => {
            try {
                
                const response = await axios.post(BASE_URL + `/carts`, config);

                if (response.status === 200) {
                    console.log('Cart');
                    console.log(response.data);

                } else {
                    alert('Erro em busca do Produto. Por favor, tente novamente.');
                }
            } catch (error) {
                console.error('Erro ao buscar dados:', error);
            }
        };

        addProduct();

    };

    return (
        <>
            <div className="container">

                <div >

                    <div className="row align-items-center">
                        <div className="col-md-8">
                            <h3 className="mt-4">{product.name}</h3>  
                        </div>
                        <div className="col-md-4 d-flex justify-content-end">
                            <Cart />
                        </div>
                    </div>

                    <div className="main-image card-img-top">
                        <img src={mainImage} alt="Imagem Principal" className="img-fluid" />
                    </div>
                    <div className="row">
                        <div className="col-md-8">
                            <div className="thumbnails">
                                {product.images.map((produt_image, index) => (
                                    <img
                                        key={index}
                                        src={produt_image.image}
                                        alt={`Thumbnail ${index}`}
                                        className="img-thumbnail"
                                        onClick={() => handleThumbnailClick(produt_image.image)}
                                    />
                                ))}
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-11">
                            <p><strong>Description:</strong> { product.description }</p>
                            <p><strong>Price US$:</strong> { product.price }</p>
                            <p><strong>Category:</strong> { product.category.description }</p>
                            <p><strong>Unit:</strong> { product.unit.description }</p>
                            <p><strong>Weight:</strong> { product.weight }</p>
                        </div>
                        <div className="col-md-1">
                            <div className="btn-group">
                                <button type="button" className="btn btn-primary" onClick={ () => handleAddCart( product.id ) } style={{ marginTop: '10px', marginBottom: '5px' }} >Add to Cart</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default DetailsProduct;