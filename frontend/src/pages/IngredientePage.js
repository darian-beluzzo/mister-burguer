// import React from 'react';

// const IngredientePage = () => (
//       <h1>IngredientePage</h1>
//     );

// IngredientePage.displayName = 'IngredientePage';

// export default IngredientePage;

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

class IngredientePage extends Component {
    state = {
      ingredientes: [],
    };
  
    async componentDidMount() {
      await api.get("ingrediente")
      .then(({ data }) => {
        this.setState({ ingredientes: data })
      })
      .catch((error) => {
            this.setState({ ingredientes: [] })  
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
            <TableCell>Valor</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {this.state.ingredientes.map(row => (
            <TableRow key={row.id}>
              <TableCell component="th" scope="row">{row.nome}</TableCell>
              <TableCell>{row.valor}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
}
}

IngredientePage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(IngredientePage);
