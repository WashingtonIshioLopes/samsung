import React from 'react';
import { useState } from "react"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import Footer from '../../Components/Footer/Footer';

const Product = () => {
  
    return (
      <div>
        <Header />
        <h2>Product</h2>
        <Footer />
      </div>
    );
};

export default Product;