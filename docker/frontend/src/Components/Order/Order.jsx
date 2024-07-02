import React from 'react';
import { useState, useEffect, useRef } from "react"
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { FaPrint } from 'react-icons/fa';
import { FaShare } from 'react-icons/fa';

import './Order.css'
import BASE_URL from '../../config';

const Order = () => {
  
    const navigate = useNavigate();

    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');

    const initialFetch = useRef(true);

    const { state } = useLocation();

    useEffect(() => {

        if (initialFetch.current) {
            initialFetch.current = false;

            const config = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            };

            const dataBody = {
                id_ckeckout: state.id_checkout,
                total: state.total,
                status: "close"
                
            };

            console.log("DataBody para order");
            console.log(dataBody);
    
            const addOrder = async () => {
                try {
                    
                    console.log(BASE_URL  + '/orders');
                    const response = await axios.post(BASE_URL  + '/orders', dataBody, config);
    
                    if (response.status === 201) {
                        console.log('Pedido.');
                        console.log(response.data);

                    } else {
                        alert('Erro ao criar Pedido. Por favor, tente novamente.');
                    }
                } catch (error) {
                    console.error('Erro ao buscar dados:', error);
                }
            };
    
            addOrder();

        }

    }, []);

    // Dados fixos de logística e número da nota fiscal fictícia
    const logisticsInfo = {
        deliveryDate: '25 de Julho de 2024',
        deliveryAddress: 'Rua Exemplo, 123 - Cidade Exemplo',
        invoiceNumber: '202400123456789'
    };

    // Função para voltar às compras (exemplo simples, pode ser personalizado)
    const handleBackToShopping = () => {
        // Lógica para voltar à página de produtos, por exemplo
        console.log('Voltando às compras...');
        navigate('/products');
    };

    // Função para imprimir o comprovante
    const handlePrintReceipt = () => {
        // Lógica para imprimir o comprovante
        console.log('Imprimindo comprovante...');
    };

    // Função para compartilhar o comprovante
    const handleShareReceipt = () => {
        // Lógica para compartilhar o comprovante
        console.log('Compartilhando comprovante...');
    };

    return (
        <div className="container mt-5">
            <div className="card">
                <div className="card-body">
                    <h2 className="card-title">Order Success!</h2>
                    <p>Protocol Number: <strong>{state.code}</strong></p>
                    <p>Delivered Date: {logisticsInfo.deliveryDate}</p>
                    <p>Address: {logisticsInfo.deliveryAddress}</p>
                    <p>Invoice: {logisticsInfo.invoiceNumber}</p>
                    <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button className="btn btn-primary me-md-2 mb-2" onClick={handleBackToShopping}>Go Back to Shopping</button>
                        <button className="btn btn-secondary me-md-2 mb-2" onClick={handlePrintReceipt}><FaPrint className="me-1" /> Print</button>
                        <button className="btn btn-info mb-2" onClick={handleShareReceipt}><FaShare className="me-1" /> Share</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Order;