import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import './App.css'


import LoginPage from './Pages/Login/LoginPage';
import ProductsPage from './Pages/Products/ProductsPage';
import ProductPage from './Pages/Product/ProductPage';
import CatalogPage from './Pages/Catalog/CatalogPage';
import CheckoutPage from './Pages/Checkout/CheckoutPage';

function App() {

    return (
      <>
        <Router>
              <Routes>
                  <Route path="/" element={<LoginPage />} />
                  <Route path="/products" element={<ProductsPage />} />
                  <Route path="/product" element={<ProductPage />} />
                  <Route path="/catalog" element={<CatalogPage />} />
                  <Route path="/checkout" element={<CheckoutPage />} />
              </Routes>
        </Router>
      </>
  )
}

export default App
