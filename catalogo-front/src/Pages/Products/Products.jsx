import React from 'react';
import { useState, useEffect  } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import ListProducts from '../../Components/Products/ListProducts';
import Footer from '../../Components/Footer/Footer';

const Products = () => {
  
    const { state } = useLocation();

    return (
        <div>
            <Header />
            <h2>Products</h2>
            <ListProducts token={state.token}/>
            <Footer />
        </div>
    );
};

export default Products;