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

    const [receivedData, setReceivedData] = useState('');

    const [refresh, setRefresh] = useState(false);

    const initialFetch = useRef(true);

    const { state } = useLocation();

    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');

    const navigate = useNavigate();

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
                        //setMainImage(response.data.images[0].image);

                        if (response.data.images && response.data.images.length > 0 && response.data.images[0].image) {
                            setMainImage(response.data.images[0].image);
                        } else {
                            // Opção 1: Não fazer nada ou definir uma imagem padrão
                            // setMainImage('default-image-url.jpg');
                            
                            // Opção 2: Definir como null ou string vazia
                            setMainImage(null); // ou '' dependendo de como você quer lidar com a ausência da imagem
                        }

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
            }
        };

        const dataBody = {
            id_cart: receivedData,
            id_product: product_id,
            quantity: 1
        };

        const addProduct = async () => {
            try {
                
                const response = await axios.post(BASE_URL + `/cartitens`, dataBody, config);

                if (response.status === 201) {
                    console.log('Cart Item');
                    console.log(response.data);

                    setRefresh(prevRefresh => !prevRefresh);

                } else {
                    alert('Erro ao Adicionar item no Carrinho. Por favor, tente novamente.');
                }
            } catch (error) {
                console.error('Erro ao adicionar dados:', error);
            }
        };

        addProduct();

    };

    const handleDataUpdate = (data) => {
        setReceivedData(data);
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
                            <Cart onDataUpdate={handleDataUpdate} onRefresh={refresh}/>
                        </div>
                    </div>

                    {/*}
                    <div className="main-image card-img-top">
                        <img src={mainImage} alt="Imagem Principal" className="img-fluid img-detail-personal" />
                    </div>
                    */}

                    <div className="main-image card-img-top">
                    {mainImage ? (
                        <img src={mainImage} alt="Imagem Principal" className="img-fluid img-detail-personal" />
                    ) : (
                        <div className="img-placeholder">No image available</div>
                    )}
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
                        <div className="col-md-10">
                            <p><strong>Description:</strong> { product.description }</p>
                            <p><strong>Price US$:</strong> { product.price }</p>
                            <p><strong>Category:</strong> { product.category.description }</p>
                            <p><strong>Unit:</strong> { product.unit.description }</p>
                            <p><strong>Weight:</strong> { product.weight }</p>
                        </div>
                        <div className="col-md-2">
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