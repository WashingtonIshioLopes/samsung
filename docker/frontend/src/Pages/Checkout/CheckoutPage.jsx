import React from 'react';
import { useState } from "react"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import Checkout from '../../Components/Shopping/Checkout';
import Footer from '../../Components/Footer/Footer';

const CheckoutPage = () => {

  const user = localStorage.getItem('user');
  const token = localStorage.getItem('token');
  
    return (
      <div>
        <Header />
        <Checkout />
        <Footer />
      </div>
    );
};

export default CheckoutPage;