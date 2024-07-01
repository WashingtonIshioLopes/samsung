import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';


import BASE_URL from '../../config';

const CatalogProducts = (props) => {

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

    const handleClick = () => {
        console.log('Botão clicado!');
    }

    return (

        <>
            <p>Catalog</p>

            <div className="container">
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="mb-3">Pesquisa</h3>
                    </div>
                    <div className="col-md-4">
                        <button type="button" className="btn btn-sm btn-outline-secondary px-5 mb-5 w-100" onClick={handleClick} >Meus Favoritos</button>
                    </div>
                </div>
                
                <div className="row">
                    <form className="d-flex align-items-end w-100">
                        <div className="col-md-11">
                            <div className="form-group mb-0">
                                <label>Filtro:</label>
                                <input type="text" className="form-control" id="pesquisa" aria-describedby="filterHelp" placeholder="Filter" onChange={(e) => setFilter(e.target.value)}/>
                            </div>
                        </div>
                        <div className="col-md-1 d-flex align-items-end">
                            <button className="btn btn-primary ml-20" onClick={handleClick} >Filtrar</button>
                        </div>
                    </form>
                </div>
                
                <h3 className="mt-4">Produtos</h3>  
                
                <div className="row">

                    {products.map(product => (

                        <div className="col-md-4">
                            <div className="card mb-4 shadow-sm">
                                <img className="card-img-top" src={product.images[0].image} alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title">{product.name}</h5>
                                    <p className="card-text">{ product.description }</p>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-sm btn-outline-secondary" onClick={handleClick} >Detalhes</button>
                                        </div>
                                        <small className="text-muted">{ product.weight }</small>
                                        <div className="icons-container">
                                            <i className="fa fa-star" title="Favorito"></i>
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