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
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import InpuField from '@material-ui/core/TextField';


const styles = theme => ({
  card: {
    maxWidth: 300,
    margin: '20px',
  },
  media: {
    height: 270,
  },
  root: {
    width: '100%',
    maxWidth: 600,
    // marginTop: theme.spacing.unit * 3,
    overflowX: 'auto',
  },
  table: {
    minWidth: 400,
  },
  boldCell: {
    fontWeight: 'bold',
  },
  textField: {
    // marginLeft: theme.spacing.unit,
    // marginRight: theme.spacing.unit,
    width: 50,
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
    await api.get('lanche')
    .then((response) => {
        this.setState({ lanches: response.data });
      })
      .catch((error) => console.log(error));
      ;
  }

  handlePopupOpen = (lanche) => {
    this.setState({ lancheSelected: lanche});
    this.setState({ open: true });
  };

  handlePopupClose = () => {
    this.setState({ open: false });
    this.setState({ lancheSelected: undefined});
  };

  async handlePopupIngredientesOpen() {
    await api.get('ingrediente')
    .then((response) => {
        this.setState({ ingredientes: response.data });
      })
      .catch((error) => {
        // this.setState({ ingredientes: [] });
        console.log(error)
      });
      ;
    this.setState({ open2: true });
  };

  handlePopupIngredientesClose = () => {
    this.setState({ open2: false });
    this.setState({ ingredientes: [], calculoPedido: [] });
  };

  handleAlterarQuantidadeIngrediente(event, index, quantidade) {
    let ingredientes = this.state.ingredientes
    ingredientes[index].quantidade = event.target.value;
    this.setState({ ingredientes: ingredientes });
  };

   async handleCalcularValorIngredientes() {
    // let req = []
    // this.state.ingredientes.forEach(i => {
    //   req.push({'idIngrediente': i.id, 'quantidade': i.quantidade});
    // });

    let req = {}
    this.state.ingredientes.forEach(i => {
      req[i.id] = i.quantidade;
    });

    await api.post('/pedido/calcular', req)
    .then((response) =>  {
      console.log(response.data);
      this.setState({ calculoPedido: response.data });
    })
    .catch((error) => {
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
                  <Typography component="p">
                    {lanche.descricaoIngredientes}
                  </Typography>
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
        <Dialog
          open={this.state.open}
          onClose={this.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Detalhes do lanche</DialogTitle>
          <DialogContent>
          { this.state.lancheSelected !== undefined && (
          <Paper className={this.props.classes.root}>
            <Table className={this.props.classes.table}>
              <TableHead>
                <TableRow>
                  <TableCell>Nome</TableCell>
                  <TableCell>Valor</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {this.state.lancheSelected.ingredientes.map(row => (
                  <TableRow key={row.id}>
                    <TableCell component="th" scope="row">
                      {row.nome}
                    </TableCell>
                    <TableCell>{row.valor}</TableCell>
                  </TableRow>
                ))}
                <TableRow>
                  <TableCell className={this.props.classes.boldCell} align="right">
                    Total
                  </TableCell>
                  <TableCell className={this.props.classes.boldCell}>{this.state.lancheSelected.valor}</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </Paper>
          )}
          </DialogContent>
          <DialogActions>
            <Button color="primary" onClick={this.handlePopupClose}>
              OK
            </Button>
          </DialogActions>
        </Dialog>

        {
          // Popup de customização do lanche
        }
        <Dialog
        open={this.state.open2}
        onClose={this.handlePopupIngredientesClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">Escolha os ingredientes</DialogTitle>
        <DialogContent>
        <Paper className={this.props.classes.root}>
          <Table className={this.props.classes.table}>
            <TableHead>
              <TableRow>
                <TableCell>Nome</TableCell>
                <TableCell>Quantidade</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.ingredientes.map((row, index) => (
                <TableRow key={row.id}>
                  <TableCell component="th" scope="row">
                    {row.nome}
                  </TableCell>
                  <TableCell>
                      <InpuField
                      id="standard-number"
                      value={row.quantidade}
                      onChange={event => this.handleAlterarQuantidadeIngrediente(event, index)}
                      type="number"
                      className={this.props.classes.textField}
                      inputProps={{ min: "0", step: "1", defaultValue: 0 }}
                      InputLabelProps={{
                        shrink: true,
                      }}
                      margin="normal"
                    />
                  </TableCell>
                </TableRow>
              ))}
              
              {this.state.calculoPedido.itens.map((row, index) => (
                <TableRow key={row.index}>
                  <TableCell className={this.props.classes.boldCell} align="right">
                    {row.nome} (-{row.quantidadeDesconto})
                  </TableCell>
                  <TableCell className={this.props.classes.boldCell}>
                    {row.valorDesconto}
                  </TableCell>
                </TableRow>
              ))}
              
              <TableRow>
                <TableCell className={this.props.classes.boldCell} align="right">
                  Total
                </TableCell>
                <TableCell className={this.props.classes.boldCell}>
                  {this.state.calculoPedido.valorTotal}
                </TableCell>
              </TableRow>
 
              </TableBody>
          </Table>
        </Paper>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => this.handlePopupIngredientesClose()}>
            Cancelar
          </Button>
          <Button color="primary" onClick={() => this.handleCalcularValorIngredientes()}>
            Calcular
          </Button>
        </DialogActions>
      </Dialog>

      </div>
    );
  }
}

PedidoPadraoPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(PedidoPadraoPage);
