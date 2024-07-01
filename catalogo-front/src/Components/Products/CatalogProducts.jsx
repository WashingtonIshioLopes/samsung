import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';


import BASE_URL from '../../config';

const CatalogProducts = (props) => {

    const [products, setProducts] = useState([]);

    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState();

    const [filter, setFilter] = useState();

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

    const handleClickDetails = (produc_id) => {
        console.log('Click Details!');

        console.log("Product Id");
        console.log(produc_id);

        console.log("Token");
        //console.log(state.token);

        //navigate('/product', { state: { token: state.token, product: product_id } });
    }

    const handleChange = (event) => {
        setSelectedCategory(event.target.value); // Atualiza o estado do option selecionado
    };

    return (

        <>

            <div className="container">
                <div className="row">
                    <div className="col-md-8">
                        <h3 className="mb-3">Pesquisa</h3>
                    </div>
                    <div className="col-md-4">
                        <button type="button" className="btn btn-sm btn-outline-secondary px-5 mb-5 w-100" onClick={handleClickFavorites} >My Favorites</button>
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
                            <label>Filter:</label>
                            <input type="text" className="form-control" id="pesquisa" aria-describedby="filterHelp" placeholder="Filter" onChange={(e) => setFilter(e.target.value)}/>
                        </div>
                    </div>

                    <div className="col-md-2 d-flex align-items-end">
                        <button className="btn btn-primary ml-20" onClick={handleClickFilter} >Filtrar</button>
                    </div>

                </div>
                
                <h3 className="mt-4">Produtos</h3>  
                
                <div className="row">

                    {products.map(product => (

                        <div className="col-md-4" key={ product.id }>
                            <div className="card mb-4 shadow-sm">
                                <img className="card-img-top" src={product.images[0].image} alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title">{product.name}</h5>
                                    <p className="card-text">{ product.description }</p>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-sm btn-outline-secondary" onClick={ () => handleClickDetails(product.id) } >Detalhes</button>
                                        </div>
                                        <small className="text-muted">{ product.weight }</small>
                                        <div className="icons-container">
                                            {/* <i className="fa fa-star" title="Favorito"></i> */}
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