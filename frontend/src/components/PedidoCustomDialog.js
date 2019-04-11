import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
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
  root: {
    width: '100%',
    maxWidth: 600,
    overflowX: 'auto',
  },
  table: {
    minWidth: 400,
  },
  boldCell: {
    fontWeight: 'bold',
  },
  textField: {
    width: 50,
  },
});

class PedidoCustomDialog extends Component {
  render() {
    return (
      <div>
        {
          // Popup de customização do lanche
        }
        <Dialog open={this.props.open2} aria-labelledby="form-dialog-title">
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
                  {this.props.ingredientes.map((row, index) => (
                    <TableRow key={row.id}>
                      <TableCell component="th" scope="row">
                        {row.nome}
                      </TableCell>
                      <TableCell>
                        <InpuField
                          id="standard-number"
                          value={row.quantidade}
                          onChange={event => this.props.handleAlterarQuantidadeIngrediente(event, index)}
                          type="number"
                          className={this.props.classes.textField}
                          inputProps={{ min: '0', step: '1', defaultValue: 0 }}
                          InputLabelProps={{
                            shrink: true,
                          }}
                          margin="normal"
                        />
                      </TableCell>
                    </TableRow>
                  ))}

                  {this.props.calculoPedido.itens !== undefined &&
                    this.props.calculoPedido.itens.map((row, index) => (
                      <TableRow key={row.index}>
                        <TableCell className={this.props.classes.boldCell} align="right">
                          {row.nome} (-{row.quantidadeDesconto})
                        </TableCell>
                        <TableCell className={this.props.classes.boldCell}>-{row.valorDesconto}</TableCell>
                      </TableRow>
                    ))}

                  <TableRow>
                    <TableCell className={this.props.classes.boldCell} align="right">
                      Total
                    </TableCell>
                    <TableCell className={this.props.classes.boldCell}>{this.props.calculoPedido.valorTotal}</TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </Paper>
          </DialogContent>
          <DialogActions>
            <Button onClick={this.props.handlePopupIngredientesClose}>Cancelar</Button>
            <Button color="primary" onClick={this.props.handleCalcularValorIngredientes}>
              Calcular
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

PedidoCustomDialog.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(PedidoCustomDialog);
