import React from 'react';
import { useState } from "react"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import BASE_URL from '../../config';
import samsungLogo from '../../assets/Images/Logo/Logo02.png';

function Sign() {

    const [document, setDocument] = useState(""); 
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        try {

            event.preventDefault();
            console.log("Submit");
            console.log("Document Number: ", document);

            console.log(BASE_URL  + '/auth/login_document');
            const response = await axios.post(BASE_URL  + '/auth/login_document', { document });
            
            if (response.status === 200) {
                console.error('Credencias válidas.');
                console.log('Token: ', response.data);
                navigate('/Products', { state: { token: response.data.token } });
            } else {
                alert('Credenciais inválidas. Por favor, tente novamente.');
            }

        } catch (error) {
            console.error('Erro ao fazer login:', error); 
            alert('Erro ao fazer login. Tente novamente mais tarde.');
        }
      };

    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-md-6 offset-md-3">
                        <h2 className="text-center text-dark mt-5">Login Form</h2>
                        <div className="text-center mb-5 text-dark">Made with bootstrap</div>
                        <div className="card my-5">
                            <form className="card-body cardbody-color p-lg-5" onSubmit={handleSubmit}>
            
                                <div className="text-center">
                                    <img src={ samsungLogo } className="img-fluid profile-image-pic img-thumbnail rounded-circle my-3" width="200px" alt="profile" />
                                </div>
            
                                <div className="mb-3">
                                    <input type="text" className="form-control" id="document" aria-describedby="emailHelp" placeholder="Document" onChange={(e) => setDocument(e.target.value)}/>
                                </div>
            
                                <div className="text-center"><button type="submit" className="btn btn-color px-5 mb-5 w-100">Login</button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Sign;