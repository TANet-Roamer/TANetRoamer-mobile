/**
 * @flow
 */

import React, { PropTypes, Component } from 'react'
import { StyleSheet, View } from 'react-native'
import { Container, Content, Input, Text } from 'native-base'
import { connect } from 'react-redux'
import MKColor from 'react-native-material-kit/lib/MKColor'
import Icon from 'react-native-vector-icons/MaterialIcons'
import NatvigatorStack from './NavigatorStack'
import FlatButton from './FlatButton'
import RaisedButton from './RaisedButton'
import { pop } from './actions/nav'
import { storeAccount } from './actions/wifi-account'

type Props = {
  pop: Function,
  storeAccount: Function
}

type State = {
  username: ?string,
  password: ?string
}

class LoginScene extends Component {
  props: Props
  state: State = {
    username: null,
    password: null
  }

  handleLogin = () => {
    const { username, password } = this.state
    this.props.storeAccount({
      username,
      password
    })
  }

  handleCancel = () => {
    this.props.pop()
  }

  handleUsernameChange = (username: string) => {
    this.setState({
      username: username || null
    })
  }

  handlePasswordChange = (password: string) => {
    this.setState({
      password: password || null
    })
  }

  render() {
    const { username, password } = this.state
    const enabled = !!(username && password)

    return (
      <Container>
        <Content>
          <View style={ styles.inputGroup }>
            <Icon name='account-box' style={ styles.icon } />
            <Input
              returnKeyType='next'
              onChangeText={ this.handleUsernameChange }
              placeholder='Username'
              style={ styles.input } />
          </View>
          <View style={ styles.inputGroup }>
            <Icon name='lock' style={ styles.icon } />
            <Input
              secureTextEntry
              returnKeyType='done'
              onChangeText={ this.handlePasswordChange }
              placeholder='Password'
              style={ styles.input } />
          </View>
          <RaisedButton enabled={ enabled } onPress={ this.handleLogin }>
            <Text
              pointerEvents='box-none'
              style={ enabled ? styles.raisedText : styles.raisedDisabledText } >
              Login
            </Text>
          </RaisedButton>
          <FlatButton onPress={ this.handleCancel }>
            <Text
              pointerEvents='box-none' >
              Cancel
            </Text>
          </FlatButton>
        </Content>
      </Container>
    )
  }

  static propTypes = {
    pop: PropTypes.func.isRequired,
    storeAccount: PropTypes.func.isRequired
  }
}

NatvigatorStack.register(
  'login',
  connect(null, { pop, storeAccount })(LoginScene),
  {
    title: 'Login'
  }
)

const styles = StyleSheet.create({
  icon: {
    fontSize: 32
  },
  input: {
    paddingLeft: 5
  },
  inputGroup: {
    flexDirection: 'row',
    alignItems: 'center',
    borderBottomColor: MKColor.Grey,
    borderBottomWidth: 1
  },
  raisedText: {
    color: 'white',
    fontWeight: 'bold'
  },
  raisedDisabledText: {
    color: MKColor.Grey,
    fontWeight: 'bold'
  }
})

export default LoginScene
