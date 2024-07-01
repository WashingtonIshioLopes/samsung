import React from 'react';
import { useState } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

import Header from '../../Components/Header/Header';
import CatalogProducts from '../../Components/Products/CatalogProducts';
import Footer from '../../Components/Footer/Footer';

const CatalogPage = () => {

    const { state } = useLocation();

    return (
      <div>
        <Header />
        <CatalogProducts token={state.token}/>
        <Footer />
      </div>
    );
};

export default CatalogPage;