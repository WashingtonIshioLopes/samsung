import React from 'react';
import { useState, useEffect  } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import CarouselProducts from '../../Components/Products/CarouselProducts';
import Footer from '../../Components/Footer/Footer';

const Products = () => {
  
    const { state } = useLocation();

    const navigate = useNavigate();

    const handleClick = () => {
        console.log("Token");
        console.log(state.token);
        navigate('/Catalog', { state: { token: state.token } });
    }

    return (
        <>
            <Header />
            
            <div className="container">
                <div className="row">
                    <div className="col-md-12 d-flex justify-content-end">
                        <div className="text-center">
                            <button className="btn btn-primary px-5 mb-5" onClick={handleClick}>Catalogo</button>
                        </div>
                    </div>
                </div>
                <CarouselProducts token={state.token}/>
            </div>

            <Footer />
        </>
    );
};

export default Products;