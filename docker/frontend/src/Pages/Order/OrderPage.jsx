import React from 'react';
import { useState } from "react"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import Order from '../../Components/Order/Order';
import Footer from '../../Components/Footer/Footer';

const OrderPage = () => {

  const user = localStorage.getItem('user');
  const token = localStorage.getItem('token');
  
    return (
      <div>
        <Header />
        <Order />
        <Footer />
      </div>
    );
};

export default OrderPage;