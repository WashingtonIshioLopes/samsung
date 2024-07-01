import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import './App.css'


import Login from './Pages/Login/Login';
import Products from './Pages/Products/Products';
import Product from './Pages/Product/Product';
import Catalog from './Pages/Catalog/Catalog';
import Checkout from './Pages/Checkout/Checkout';

function App() {

    return (
      <>
        <Router>
              <Routes>
                  <Route path="/" element={<Login />} />
                  <Route path="/products" element={<Products />} />
                  <Route path="/product" element={<Product />} />
                  <Route path="/catalog" element={<Catalog />} />
                  <Route path="/checkout" element={<Checkout />} />
              </Routes>
        </Router>
      </>
  )
}

export default App
