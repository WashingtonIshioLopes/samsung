import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

import './Checkout.css'
import BASE_URL from '../../config';

const Checkout = () => {

    const [products, setProducts] = useState([]);
    const [payments, setPayments] = useState([]);
    const [selectedPayment, setSelectedPayment] = useState();   

    const initialFetch = useRef(true);

    const { state } = useLocation();

    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');

    const navigate = useNavigate();

    useEffect(() => {

        if (initialFetch.current) {
            initialFetch.current = false;

            const config = {
                headers: {
                    Authorization: `Bearer ${token}`
                },
                params: {
                    id_cart: state.cart
                }
            };
    
            const fetchCartItens = async () => {
                try {
                    
                    console.log(BASE_URL  + '/cartitens/search');
                    const response = await axios.get(BASE_URL  + '/cartitens/search', config);
        
                    if (response.status === 200) {
                        console.log('Carts Itens.');
                        console.log(response.data);
                        setProducts(response.data);
                        
                    } else {
                        alert('Erro em busca Carrinhos Itens. Por favor, tente novamente.');
                    }
                } catch (error) {
                    console.error('Erro ao buscar dados:', error);
                }
            };


            const config2 = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            };

            const fetchPayments = async () => {
                try {
                    
                    console.log(BASE_URL  + '/payments');
                    const response = await axios.get(BASE_URL  + '/payments', config2);
    
                    if (response.status === 200) {
                        console.log('Lista de Pagamentos.');
                        console.log(response.data);
                        setPayments(response.data);
                    } else {
                        alert('Erro em busca Pagamentos. Por favor, tente novamente.');
                    }
                } catch (error) {
                    console.error('Erro ao buscar dados:', error);
                }
            };
    
            fetchPayments();
    
            fetchCartItens();

        }
    
    }, []);

    const handleCheckout = (event) => {

        console.log('Finalizando pagamento com método:', selectedPayment);

        alert('Transacao com PIN Pad não operacional. Clique em OK para continuar...');

        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        const totalPrice = calculateTotal();

        const dataBody = {
            id_user: user,
            total: totalPrice,
            status: "close"
        };

        const updateCart = async () => {
            try {
                
                console.log(BASE_URL  + '/carts');
                const response = await axios.put(BASE_URL  + `/carts/${state.cart}`, dataBody, config);
                console.log(response);
                if (response.status === 200) {
                    console.log('Atualizando cart');
                    console.log(response.data);

                    const codeNumber = generateProtocolNumber();

                    const dataBody = {
                        id_user: user,
                        id_cart: state.cart,
                        id_payment: selectedPayment,
                        code: codeNumber,
                        status: "close"
                    };

                    console.log('Data Body para Checkout');
                    console.log(dataBody);

                    const addCheckout = async () => {
                        try {
                            console.log(BASE_URL  + '/checkout');
                            const response = await axios.post(BASE_URL  + `/checkout`, dataBody, config);
                            console.log(response);
                            if (response.status === 201) {
                                console.log('Criando order');
                                console.log(response.data);
                                alert('Compra Realizada com Sucesso !');
                                navigate('/order', { state: { id_checkout: response.data.id, total: totalPrice, code: codeNumber, cart: state.cart } });
                            } else {
                                alert('Erro em criando Carrinho. Por favor, tente novamente.');
                            }
                        } catch (error) {
                            console.error('Erro ao gravar dados:', error);
                        }
                    };
            
                    addCheckout();

                } else {
                    alert('Erro em alterando Carrinho. Por favor, tente novamente.');
                }
            } catch (error) {
                console.error('Erro ao gravar dados:', error);
            }
        };

        updateCart();

    };

    const generateProtocolNumber = () => {
        return '#' + Math.random().toString(36).substr(2, 9);
    };

    // Função para calcular o total da compra
    const calculateTotal = () => {
        let total = 0;
        products.forEach(product => {
            total += product.quantity * product.product.price;
        });
        return total;
    };

    const handleChange = (event) => {
        console.log("Pagamento Selecionado")
        console.log(event.target.value);
        setSelectedPayment(event.target.value); // Atualiza o estado do option selecionado
    };

    return (
        <div className="container mt-5">
            <div className="row">
                {/* Coluna esquerda para a lista de produtos */}
                <div className="col-md-6">
                    <h3>Lista de Produtos</h3>
                    <ul className="list-group mb-3">
                        {products.map(product => (
                            <li key={product.id} className="list-group-item d-flex justify-content-between align-items-center">
                                <div>
                                    <h5 className="mb-0">{product.product.name}</h5>
                                    <small>Quantidade: {product.quantity}</small>
                                </div>
                                <span className="badge bg-primary rounded-pill">${product.product.price}</span>
                            </li>
                        ))}
                    </ul>
                    <div className="text-end">
                        <h5>Total: ${calculateTotal()}</h5>
                    </div>
                </div>

                {/* Coluna direita para informações de pagamento */}
                <div className="col-md-6">
                    <h3>Forma de Pagamento</h3>
                    <select className="form-select mb-3" value={selectedPayment} onChange={handleChange}>
                        <option value="">Selecione a forma de pagamento</option>
                        {payments.map(payment => (
                            <option key={payment.id} value={payment.id}>{ payment.name }</option>
                        ))}
                    </select>

                    <button className="btn btn-primary" onClick={handleCheckout}>Finalizar Pagamento</button>
                </div>
            </div>
        </div>
    );
  };

export default Checkout;