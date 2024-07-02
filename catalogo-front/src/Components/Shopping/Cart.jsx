import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { FaShoppingCart } from 'react-icons/fa';

import './Cart.css'
import BASE_URL from '../../config';

const Cart = ({ onDataUpdate, onRefresh }) => {

  const [cart, setCart] = useState(0);
  const [quantity, setQuantity] = useState(0); // Estado para a quantidade de itens no carrinho

  //const { token } = props;

  const user = localStorage.getItem('user');
  const token = localStorage.getItem('token');

  const initialFetch = useRef(true);
  const refreshFetch = useRef(true);

  const navigate = useNavigate();

  const checkCart = () => {

    const config = {
        headers: {
            Authorization: `Bearer ${token}`
        },
        params: {
          id_user: user,
          status: "open"
        }
    };

    const fetchCart = async () => {
        try {
            
            console.log(BASE_URL  + '/carts/search');
            const response = await axios.get(BASE_URL  + '/carts/search', config);

            if (response.status === 200) {
                console.log('Carts.');
                console.log(response.data);

                if (response.data && response.data.length > 0 && response.data[0].status === "open") {
                  console.log('Cart Opened.');
                  setQuantity(response.data[0].itens.length);
                  setCart(response.data[0].id);
                  onDataUpdate(response.data[0].id);
                }
                else{
                  console.log('Cart Closed.');

                  const dataBody = {
                      id_user: user,
                      total: 0,
                      status: "open"
                  };

                  const addCart = async () => {
                      try {
                          
                          console.log(BASE_URL  + '/carts');
                          const response = await axios.post(BASE_URL  + '/carts', dataBody, config);
                          console.log(response);
                          if (response.status === 201) {
                              console.log('Criando cart.');
                              console.log(response.data);
                              setCart(response.data.id);
                              onDataUpdate(response.data.id);
                          } else {
                              alert('Erro em criando Carrinho. Por favor, tente novamente.');
                          }
                      } catch (error) {
                          console.error('Erro ao gravar dados:', error);
                      }
                  };
          
                  addCart();

                }

                
            } else {
                alert('Erro em busca Carrinhos. Por favor, tente novamente.');
            }
        } catch (error) {
            console.error('Erro ao buscar dados:', error);
        }
    };

    fetchCart();

  }

  useEffect(() => {

    if (initialFetch.current) {
      initialFetch.current = false;
      console.log('Verificando cart no Inicio');
      checkCart();
    }

  }, []);

  useEffect(() => {

    if (refreshFetch.current) {
      refreshFetch.current = false;
      console.log('Componente filho foi atualizado');
      checkCart();
      refreshFetch.current = true;
    }
      
  }, [onRefresh]); // Executa o efeito sempre que 'refresh' mudar

  const handleCheckout = () => {
    if(quantity > 0){
      console.log('Fazendo Checkout...');
      console.log(cart);
      navigate('/checkout', { state: { cart: cart } });
    }
    else{
      alert('Não existem produtos no Carrinho.');
    }
    
  }

  return (
    <div className="shopping-cart d-flex align-items-center">
        <button className="btn btn-primary me-2" onClick={handleCheckout}>Finalizar Compra</button>
        <div className="cart-icon">
            <FaShoppingCart size={24} color="blue" style={{ marginLeft: '10px' }} />
        </div>
        <span className="badge bg-secondary ms-2">{quantity}</span>
    </div>
  );
}

export default Cart;