import React, { Component } from 'react';
import api from '../services/api';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import Grid from '@material-ui/core/Grid';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import DetalhesPedidoDialog from '../components/DetalhesPedidoDialog';
import PedidoCustomDialog from '../components/PedidoCustomDialog';

const styles = theme => ({
  card: {
    maxWidth: 300,
    margin: '20px',
  },
  media: {
    height: 270,
  },
});

class PedidoPadraoPage extends Component {
  state = {
    lanches: [],
    lancheSelected: undefined,
    open: false,
    open2: false,
    ingredientes: [],
    calculoPedido: [],
  };

  async componentDidMount() {
    await api
      .get('lanche')
      .then(response => {
        this.setState({ lanches: response.data });
      })
      .catch(error => console.log(error));
  }

  handlePopupOpen = lanche => {
    this.setState({ lancheSelected: lanche });
    this.setState({ open: true });
  };

  handlePopupClose = () => {
    this.setState({ open: false });
    this.setState({ lancheSelected: undefined });
  };

  async handlePopupIngredientesOpen() {
    await api
      .get('ingrediente')
      .then(response => {
        this.setState({ ingredientes: response.data });
      })
      .catch(error => {
        console.log(error);
      });
    this.setState({ open2: true });
  }

  handlePopupIngredientesClose = () => {
    this.setState({ open2: false });
    this.setState({ ingredientes: [], calculoPedido: [] });
  };

  handleAlterarQuantidadeIngrediente = (event, index, quantidade) => {
    let ingredientes = this.state.ingredientes;
    ingredientes[index].quantidade = event.target.value;
    this.setState({ ingredientes: ingredientes });
  };

  handleCalcularValorIngredientes = async () => {
    let req = {};
    this.state.ingredientes.forEach(i => {
      req[i.id] = i.quantidade;
    });

    await api
      .post('/pedido/calcular', req)
      .then(response => {
        console.log(response.data);
        this.setState({ calculoPedido: response.data });
      })
      .catch(error => {
        console.log(error);
      });
  };

  render() {
    return (
      <div>
        <Grid container spacing={8}>
          {
            // Lanches
          }
          {this.state.lanches.map(lanche => (
            <Grid item md={3} key={lanche.id}>
              <Card className={this.props.classes.card}>
                <CardMedia className={this.props.classes.media} image={lanche.urlImagem} title={lanche.nome} />
                <CardContent>
                  <Typography gutterBottom variant="h5" component="h2">
                    {lanche.nome} (R$ {lanche.valor})
                  </Typography>
                  <Typography component="p">{lanche.descricaoIngredientes}</Typography>
                </CardContent>
                <CardActions>
                  <Button size="small" color="primary" onClick={() => this.handlePopupOpen(lanche)}>
                    Selecionar
                  </Button>
                </CardActions>
              </Card>
            </Grid>
          ))}

          {
            // Opção de lanche personalizado
          }
          <Grid item md={3}>
            <Card className={this.props.classes.card}>
              <CardMedia
                className={this.props.classes.media}
                image="https://s3-sa-east-1.amazonaws.com/delivery-direto/img/items/5953942b0d96f.jpeg"
                title="Personalize seu lanche"
              />
              <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                  Personalize seu lanche
                </Typography>
                <Typography component="p">Nesta opção você pode escolher os ingredientes do seu lanche.</Typography>
              </CardContent>
              <CardActions>
                <Button size="small" color="primary" onClick={() => this.handlePopupIngredientesOpen()}>
                  Selecionar
                </Button>
              </CardActions>
            </Card>
          </Grid>
        </Grid>

        {
          // Popup que exibe os detalhes do lanche (precificação)
        }
        <DetalhesPedidoDialog
          lancheSelected={this.state.lancheSelected}
          open={this.state.open}
          handlePopupClose={this.handlePopupClose}
        />

        {
          // Popup de customização do lanche
        }
        <PedidoCustomDialog
          calculoPedido={this.state.calculoPedido}
          open2={this.state.open2}
          ingredientes={this.state.ingredientes}
          handlePopupIngredientesClose={this.handlePopupIngredientesClose}
          handleCalcularValorIngredientes={this.handleCalcularValorIngredientes}
          handleAlterarQuantidadeIngrediente={this.handleAlterarQuantidadeIngrediente}
        />
      </div>
    );
  }
}

PedidoPadraoPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(PedidoPadraoPage);
