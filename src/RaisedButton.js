/**
 * @flow
 */
import MKButton from 'react-native-material-kit/lib/mdl/Button'
import MKColor from 'react-native-material-kit/lib/MKColor'
import React, { PropTypes, Component } from 'react'

class RaisedButton extends Component {
  render() {
    const { enabled } = this.props
    const backgroundColor = enabled ? MKColor.LightBlue : MKColor.Silver
    return (
      <MKButton
        { ...this.props }
        style={ {
          justifyContent: 'center',
          alignItems: 'center'
        } }
        height={ 32 }
        borderRadius={ 2 }
        elevation={ 4 }
        backgroundColor={ backgroundColor }
        shadowRadius={ 1 }
        shadowOffset={ { width: 0, height: 0.5 } }
        shadowOpacity={ 0.7 }
        shadowColor='black' />
    )
  }
  static propTypes = {
    ...MKButton.propTypes,
    enabled: PropTypes.bool.isRequired
  }

  static defaultProps = {
    enabled: true
  }
}

export default RaisedButton
