
import React, { Component } from "react";
import api from "../services/api";

import { withStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
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
      <div>
      {this.state.lanches.map(lanche => (
          <Card >
              <CardMedia style={{height: 0, paddingTop: '56.25%'}}
              image="" //{props.course.fields.courseImage.fields.file.url}
              title={lanche.nome}
              />
              <CardContent>
              <Typography gutterBottom variant="headline" component="h2">
                  {lanche.nome}
              </Typography>
              <Typography component="p">
                  {lanche.ingredientes.map(ingrediente => ingrediente.nome+", ")}
              </Typography>
              </CardContent>
              <CardActions>
              <Button size="small" color="primary" href=""//{props.course.fields.url}
               target="_blank">
                  Adicionar
              </Button>
              </CardActions>
          </Card>
          ))}
    </div>
      );
  }
}

export default withStyles(styles)(PedidoPage);