import 'bootstrap/dist/css/bootstrap.min.css';
import { FaUser } from "react-icons/fa"

import { useState} from "react"

import './Login.css'

function Login() {

  const [document, setDocument] = useState(""); 

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Submit");
    console.log("Credencial: ", document);
  }

  return (
    <form onSubmit={handleSubmit}>
      <h1>Acesse o sistema</h1>
      <div className="input-field">
          <input type="text" placeholder="Documento" onChange={(e) => setDocument(e.target.value)} />
          <FaUser className="Icons" />
      </div>
      <div>
        <button>Entrar</button> 
      </div>
    </form>
  );
}

export default Login 