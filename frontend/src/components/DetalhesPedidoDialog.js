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
});

class DetalhesPedidoDialog extends Component {
  render() {
    return (
      <div>
        <Dialog open={this.props.open} aria-labelledby="form-dialog-title">
          <DialogTitle id="form-dialog-title">Detalhes do lanche</DialogTitle>
          <DialogContent>
            {this.props.lancheSelected !== undefined && (
              <Paper className={this.props.classes.root}>
                <Table className={this.props.classes.table}>
                  <TableHead>
                    <TableRow>
                      <TableCell>Nome</TableCell>
                      <TableCell>Valor</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {this.props.lancheSelected.ingredientes.map(row => (
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
                      <TableCell className={this.props.classes.boldCell}>{this.props.lancheSelected.valor}</TableCell>
                    </TableRow>
                  </TableBody>
                </Table>
              </Paper>
            )}
          </DialogContent>
          <DialogActions>
            <Button color="primary" onClick={this.props.handlePopupClose}>
              OK
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

DetalhesPedidoDialog.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(DetalhesPedidoDialog);
