/**
 * @flow
 */

import React, { PropTypes, Component } from 'react'
import { StyleSheet } from 'react-native'
import { connect } from 'react-redux'
import { Container, Content, Text } from 'native-base'
import NavigatorStack from './NavigatorStack'
import FlatButton from './FlatButton'
import { push } from './actions/nav'
import { fetchAccount, clearAccount } from './actions/wifi-account'

type Props = {
  accountInfo: Object,
  push: Function,
  fetchAccount: Function,
  clearAccount: Function
}

class AccountInfoScene extends Component {
  props: Props

  componentWillMount() {
    this.props.fetchAccount()
  }

  handleLogin = () => {
    this.props.push({ name: 'login' })
  }

  handleLogout = () => {
    this.props.clearAccount()
  }

  render() {
    const { accountInfo } = this.props
    const isLogin = accountInfo.username && accountInfo.password
    const btnText = isLogin ? 'Logout' : 'Login'
    const pressHandler = isLogin ? this.handleLogout : this.handleLogin
    return (
      <Container style={ styles.block }>
        <Content>
          <Text> { isLogin ? `Login as ${accountInfo.username}` : 'Please login' } </Text>
          <FlatButton onPress={ pressHandler }>
            <Text>
              { btnText }
            </Text>
          </FlatButton>
        </Content>
      </Container>
    )
  }

  static propTypes = {
    accountInfo: PropTypes.shape({
      username: PropTypes.string,
      password: PropTypes.string
    }).isRequired,
    push: PropTypes.func.isRequired,
    fetchAccount: PropTypes.func.isRequired,
    clearAccount: PropTypes.func.isRequired
  }
}

NavigatorStack.register(
  'home',
  connect(
    (store) => ({
      accountInfo: store.wifiAccount
    }
  ), { push, fetchAccount, clearAccount })(AccountInfoScene),
  {
    title: 'Settings'
  }
)

const styles = StyleSheet.create({
  block: {
    alignSelf: 'stretch'
  }
})
