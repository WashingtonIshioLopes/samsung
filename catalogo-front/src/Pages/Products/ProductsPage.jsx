import React from 'react';
import { useState, useEffect  } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import CarouselProducts from '../../Components/Products/CarouselProducts';
import Footer from '../../Components/Footer/Footer';

const ProductsPage = () => {
  
    //const { state } = useLocation();

    const navigate = useNavigate();

    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');

    const handleClick = () => {
        console.log("Token");
        console.log(token);
        navigate('/catalog');
    }

    return (
        <>
            <Header />
            
            <div className="container">
                <div className="row">
                    <div className="col-md-12 d-flex justify-content-end">
                        <div className="text-center">
                            <button className="btn btn-primary px-5 mb-5" onClick={handleClick} style={{ marginTop: '10px' }}>Catalogo</button>
                        </div>
                    </div>
                </div>
                <CarouselProducts token={token}/>
            </div>

            <Footer />
        </>
    );
};

export default ProductsPage;