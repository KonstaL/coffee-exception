import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';

const PostImageContainer = Styled.div`
  overflow:hidden;
  height:${props => props.maxHeight + 'px'};
  width:100%;
  background-image: url("${props => props.img}");
  background-size:cover;
  background-position: center;
  border: 1px solid rgb(230,230,230);
`;

class AboutUs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      paragraphs: this.props.body,
      image: this.props.image
    };
  }

  componentWillMount() {
    if (this.props.trim != null && this.props.trim) {
      let trimmedParagraph = this.state.paragraphs[0].props.children;
      if (trimmedParagraph.length > 250) {
        trimmedParagraph = trimmedParagraph.substr(0, 250) + '...';
      }
      const para = <p>{trimmedParagraph}</p>;
      this.setState({
        paragraphs: para
      });
    }
  }

  render() {
    return (
      <div className="post-container">
        <Row>
          <Col xl={{ size: 8, offset: 2 }}>
            <h2>About us</h2>
            <p>
              Wings ristretto, galão froth eu trifecta medium grounds. Beans to
              go ristretto pumpkin spice caffeine grounds froth french press.
              Sit, lungo, rich single shot, single origin, spoon, café au lait
              so kopi-luwak con panna siphon. Flavour aromatic, mazagran and
              aged iced decaffeinated percolator galão white.
            </p>
            <p>
              Cinnamon foam café au lait fair trade arabica chicory roast
              cultivar coffee chicory mocha. Bar frappuccino, irish grinder
              steamed saucer americano iced body viennese. Qui, americano, ut id
              café au lait crema kopi-luwak blue mountain half and half. Aroma
              espresso mazagran cup, sit percolator arabica doppio acerbic
              aftertaste arabica.
            </p>

            <h2>Why</h2>
            <p>
              At plunger pot organic siphon est sit, foam, irish extraction
              doppio sweet aged. Mug cappuccino, skinny espresso cortado coffee
              whipped cappuccino ristretto turkish irish. Turkish, steamed
              dripper spoon at shop black beans. Wings, foam crema aftertaste
              flavour saucer redeye.
            </p>

            <p>
              Bar, extra, trifecta whipped coffee blue mountain mazagran
              variety. That plunger pot, americano rich mocha chicory whipped.
              Flavour sugar, pumpkin spice seasonal half and half redeye skinny
              body. Fair trade espresso iced, froth strong, espresso affogato
              plunger pot rich white in steamed.
            </p>

            <h2>Our favorite coffee? </h2>

            <p>
              Americano spoon turkish carajillo variety et cappuccino beans in
              wings galão. Saucer grounds roast caramelization aftertaste shop
              extra eu affogato. Doppio turkish eu turkish strong cup as cup
              cinnamon ristretto. Affogato crema turkish cinnamon cortado
              barista beans single shot id as so strong.
            </p>

            <p>
              Cup crema so turkish percolator barista rich con panna.
              Decaffeinated, breve crema whipped body redeye body. And rich
              percolator wings con panna half and half strong café au lait
              robusta. Carajillo, galão, barista mazagran shop pumpkin spice qui
              cultivar.
            </p>
          </Col>
        </Row>

        {/* <div className="post-header-container">
          <h3>{this.props.title}</h3>
          <p>14/04/2018</p>
        </div>
        <div className="post-paragraphs">{this.state.paragraphs}</div>
        <div className="post-footer-container">
          <div className="post-author-profile">
            <div className="profile-image-frame">
              <img src={tempavatar} alt="avatar" />
            </div>
            <div className="profile-author-container">
              <p>Timo McFarlane</p>
              <p className="post-author-subheader">The one and only</p>
            </div>
            <div className="post-some-icons">
              <i className="material-icons">chat_bubble</i>
              <p>50</p>
            </div>
            <div className="post-some-icons">
              <i className="material-icons">local_cafe</i>
              <p>50</p>
            </div>
          </div>
        </div> */}
      </div>
    );
  }
}

export default AboutUs;
