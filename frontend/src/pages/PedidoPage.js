
import React, { Component } from "react";
import api from "../services/api";
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import Grid from '@material-ui/core/Grid';
import CardContent from '@material-ui/core/CardContent'
import CardMedia from '@material-ui/core/CardMedia'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'

const styles = {
  card: {
    maxWidth: 345,
  },
  media: {
    height: 140,
  },
};

class PedidoPage extends Component {
  state = {
    lanches: [],
  };

  async componentDidMount() {
    const response = await api.get("lanche");
    // .then((response) => {
    //     console.log('Success')
    //   })
    //   .catch((error) => console.log(error));
    //   ;
    //   console.log("Response: "+response)
    this.setState({ lanches: response.data });
  }

  render() {
    return (
      //   <div>
      //   <h1>Pedidos</h1>
      // <ul>
      //     {this.state.lanches.map(lanche => (
      //       <li>{lanche.nome} ({lanche.ingredientes.map(ingrediente => ingrediente.nome+", ")})
      //       </li>
      //     ))}
      // </ul>
      // </div>
      <Grid container spacing={12}>
      {this.state.lanches.map(lanche => (
        <Grid item md={3}>
        <Card className={this.props.classes.card}>
          <CardMedia
            className={this.props.classes.media}
            image={lanche.urlImagem}
            title={lanche.nome}
          />
          <CardContent>
            <Typography gutterBottom variant="h5" component="h2">
                {lanche.nome} (R$ {lanche.valor.toLocaleString(navigator.language, { minimumFractionDigits: 2 })})
            </Typography>
            <Typography component="p">
            {lanche.ingredientes.map(ingrediente => ingrediente.nome+", ")}
            </Typography>
          </CardContent>
        <CardActions>
          <Button size="small" color="primary">
            Adicionar
          </Button>
        </CardActions>
      </Card>
      </Grid>
          ))}
          </Grid>
      );
  }
}
// {lanche.ingredientes.map(ingrediente => ingrediente.nome+", ")}

PedidoPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(PedidoPage);