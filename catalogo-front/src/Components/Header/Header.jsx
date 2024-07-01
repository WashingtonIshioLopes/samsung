import React, { useState } from 'react';

import './Header.css'
import samsungAd from '../../assets/Images/Ad/Ad12.png';

const Header = () => {
    return (
        <>
          <header className="bg-light py-3">
              <div className="container">
                  <div className="row align-items-center">
                      <div className="col-md-12 text-center">
                          <img src={samsungAd} className="img-fluid header-image" alt="Logo" />
                      </div>
                  </div>
              </div>
          </header>
        </>
    );
  };

export default Header;