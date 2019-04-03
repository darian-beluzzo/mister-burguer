import React, {Component} from 'react';
import api from "../services/api";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 3,
    overflowX: 'auto',
  },
  table: {
    minWidth: 700,
  },
});

class LanchePage extends Component {
    state = {
      lanches: [],
    };
  
    async componentDidMount() {
      await api.get("lanche")
      .then(({ data }) => {
        this.setState({ lanches: data })
      })
      .catch((error) => {
            this.setState({ lanches: [] })  
            console.log(error);
      });

    }
  
    render() {
        const { classes } = this.props;
  
  return (
    <Paper className={classes.root}>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell>Nome</TableCell>
            <TableCell>Ingredientes</TableCell>
            <TableCell>Valor</TableCell>
            <TableCell>Imagem</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {this.state.lanches.map(lanche => (
            <TableRow key={lanche.id}>
              <TableCell component="th" scope="row">{lanche.nome}</TableCell>
              <TableCell>{lanche.ingredientes.map(ingrediente => ingrediente.nome+", ")}</TableCell>
              <TableCell>R$ {lanche.valor.toLocaleString(navigator.language, { minimumFractionDigits: 2 })}</TableCell>
              <TableCell>{lanche.urlImagem}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
}
}

LanchePage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(LanchePage);
