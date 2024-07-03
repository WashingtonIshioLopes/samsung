import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { FaShoppingCart } from 'react-icons/fa';
import { FaStar } from 'react-icons/fa';

import Cart from '../Shopping/Cart';
import './CatalogProducts.css'
import BASE_URL from '../../config';

const CatalogProducts = (props) => {

    const [products, setProducts] = useState([]);

    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState();

    const [quantity, setQuantity] = useState();

    const [filter, setFilter] = useState();

    const [receivedData, setReceivedData] = useState('');

    //const { token } = props;

    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');

    const initialFetch = useRef(true);

    const navigate = useNavigate();

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

            const fetchCategories = async () => {
                try {
                    
                    console.log(BASE_URL  + '/products');
                    const response = await axios.get(BASE_URL  + '/categories', config);
    
                    if (response.status === 200) {
                        console.log('Lista de Categorias.');
                        console.log(response.data);
                        setCategories(response.data);
                    } else {
                        alert('Erro em busca Categortias. Por favor, tente novamente.');
                    }
                } catch (error) {
                    console.error('Erro ao buscar dados:', error);
                }
            };
    
            fetchCategories();

            fetchProducts();

        }

    }, []);

    const handleClickFavorites = () => {
        console.log('Click Favorites!');

        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        const fetchFavorites = async () => {
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

        fetchFavorites();

    }

    const handleClickFilter = () => {
        console.log('Click Filter!');
        console.log('Category: ', selectedCategory);
        console.log('Description: ', filter);

        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            },
            params: {
                // Aqui você define os parâmetros de consulta
                // Exemplo: search por nome, id, etc.
                id_category: selectedCategory,
                description: filter
            }
        };

        const fetchFiltered = async () => {
            try {
                
                console.log(BASE_URL  + '/products');
                const response = await axios.get(BASE_URL  + '/products/search', config);

                if (response.status === 200) {
                    console.log('Lista de Produtos.');
                    console.log(response.data);
                    setProducts(response.data);
                } else {
                    alert('Erro em busca Produtos com Filtro. Por favor, tente novamente.');
                }
            } catch (error) {
                console.error('Erro ao buscar dados:', error);
            }
        };

        fetchFiltered();

    }

    const handleClickDetails = (product_id) => {
        console.log('Click Details!');

        console.log("Product Id");
        console.log(product_id);

        navigate('/product', { state: { product_id: product_id } });
    }

    const handleChange = (event) => {
        setSelectedCategory(event.target.value); // Atualiza o estado do option selecionado
    };

    const handleDataUpdate = (data) => {
        setReceivedData(data);
    };

    const handleClickIcon = (product_id) => {
        console.log("Product Id");
        console.log(product_id);

        /*
        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            },
            params: {
                id_user: user,
                id_product: product_id,
            }
        };

        const addFavorite = async () => {
            try {
                
                console.log(BASE_URL  + '/favorites');
                const response = await axios.post(BASE_URL  + '/favorites', config);

                if (response.status === 200) {
                    console.log('Favoritos.');
                    console.log(response.data);
                    setProducts(response.data);
                } else {
                    alert('Erro em criar Favoritos. Por favor, tente novamente.');
                }
            } catch (error) {
                console.error('Erro ao buscar dados:', error);
            }
        };

        addFavorite();
        */
        
    };

    return (

        <>
            <div className="container">
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="mb-3">Pesquisa</h3>
                    </div>
                    <div className="col-md-4">
                        <button type="button" className="btn btn-primary px-5 mb-5 w-100" onClick={handleClickFavorites} >My Favorites</button>
                    </div>
                </div>
                
                <div className="row">

                    <div className="col-md-12">
                        <div className="form-group mb-0">
                            <label>Categories:</label>
                            <select className="form-select" value={selectedCategory} onChange={handleChange}>
                                <option value="">Selecione uma opção</option>
                                {categories.map(category => (
                                    <option key={category.id} value={category.id}>{category.description}</option>
                                ))}
                            </select>
                        </div>
                    </div>

                    <div className="col-md-12">
                        <div className="form-group mb-0">
                            <label>Search:</label>
                            <input type="text" className="form-control" id="pesquisa" aria-describedby="filterHelp" placeholder="Filter" onChange={(e) => setFilter(e.target.value)}/>
                        </div>
                    </div>

                    <div className="col-md-2 d-flex align-items-end">
                        <button className="btn btn-primary ml-20" onClick={handleClickFilter} style={{ marginTop: '10px', marginBottom: '5px' }} >Search</button>
                    </div>

                </div>

                <div className="row align-items-center">
                    <div className="col-md-8">
                        <h3 className="mt-4">Produtos</h3>  
                    </div>
                    <div className="col-md-4 d-flex justify-content-end">
                        <Cart onDataUpdate={handleDataUpdate}/>
                    </div>
                </div>

                <div className="row">

                    {products.map(product => (

                        <div className="col-md-4" key={ product.id }>
                            <div className="card mb-4 shadow-sm">
                                {/* <img className="card-img-top" src={product.images[0].image} alt="Card image cap" /> */ }
                                {product?.images?.[0]?.image ? (
                                <img
                                    className="card-img-top"
                                    src={product.images[0].image}
                                    alt="Card image cap"
                                />
                                ) : (
                                <div className="card-img-top-placeholder">
                                    No image available
                                </div>
                                )}                                
                                <div className="card-body">
                                    <h5 className="card-title">{product.name}</h5>
                                    <p className="card-text">{ product.description }</p>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <small className="text-muted">{ "Price (US$): " + product.price }</small>
                                        <small className="text-muted">{ "Category: " + product.category.description }</small>
                                    </div>

                                    <div className="d-flex justify-content-between align-items-right">
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-primary" onClick={ () => handleClickDetails(product.id) } style={{ marginTop: '10px', marginBottom: '5px' }} >Details</button>
                                        </div>
                                        <div className="icons-container">
                                            <FaStar
                                                size={20}
                                                color="black"
                                                style={{ marginTop: '20px', marginLeft: '10px', cursor: 'pointer' }}
                                                onClick={ () => handleClickIcon(product.id) }
                                            />
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    ))}

                </div>
            </div>

        </>

    );
};

export default CatalogProducts;