import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { FaShoppingCart } from 'react-icons/fa';

import './Cart.css'
import BASE_URL from '../../config';

const Cart = (props) => {

  const [quantity, setQuantity] = useState(0); // Estado para a quantidade de itens no carrinho

  //const { token } = props;

  const user = localStorage.getItem('user');
  const token = localStorage.getItem('token');

  const initialFetch = useRef(true);

  useEffect(() => {

      if (initialFetch.current) {
          initialFetch.current = false;

          const config = {
              headers: {
                  Authorization: `Bearer ${token}`
              },
              params: {
                user_id: user,
                status: "open"
              }
          };
  
          const fetchCart = async () => {
              try {
                  
                  console.log(BASE_URL  + '/carts');
                  const response = await axios.get(BASE_URL  + '/carts', config);
  
                  if (response.status === 200) {
                      console.log('Carts.');
                      console.log(response.data);
                      console.log(response.data[0].status);

                      if(response.data[0].status === "open"){
                        console.log('Cart Opened.');
                        setQuantity(response.data[0].itens.length);
                      }
                      else{
                        console.log('Cart Closed.');
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

  }, []);

  return (
      <div className="shopping-cart">
          <div className="cart-icon">
              <FaShoppingCart size={24} color="blue" style={{ marginRight: '5px'}} />
          </div>
          <span className="badge bg-secondary">{quantity}</span>
      </div>
  );
}

export default Cart;