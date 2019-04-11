import React from 'react';
import './App.css';

import { BrowserRouter, Switch, Route } from 'react-router-dom';
// import IndexPage from './pages/IndexPage';
import PedidoPadraoPage from './pages/PedidoPadraoPage';
import LanchePage from './pages/LanchePage';
import IngredientePage from './pages/IngredientePage';
import Template from './pages/Template';

// <Route component={IndexPage} exact path="/" />
const App = () => (
  <BrowserRouter>
    <Template>
      <Switch>
        <Route component={PedidoPadraoPage} exact path="/" />
        <Route component={PedidoPadraoPage} exact path="/pedido" />
        <Route component={LanchePage} exact path="/lanche" />
        <Route component={IngredientePage} exact path="/ingrediente" />
      </Switch>
    </Template>
  </BrowserRouter>
);

App.displayName = 'Mister Burger';

export default App;
