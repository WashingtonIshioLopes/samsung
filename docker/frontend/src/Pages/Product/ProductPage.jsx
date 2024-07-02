import React from 'react';
import { useState } from "react"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import DetailsProduct from '../../Components/Products/DetailsProduct';
import Footer from '../../Components/Footer/Footer';

const ProductPage = () => {
  
    return (
      <div>
        <Header />
        <DetailsProduct />
        <Footer />
      </div>
    );
};

export default ProductPage;