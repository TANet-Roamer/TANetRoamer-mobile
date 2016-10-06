/**
 * @flow
 */

import React, { Component } from 'react'
import {
  StyleSheet,
  Navigator
} from 'react-native'
import { Provider } from 'react-redux'
import { Title } from 'native-base'
import MKColor from 'react-native-material-kit/lib/MKColor'
import store from './store'
import rootSaga from './sagas'
import NavigatorStack from './NavigatorStack'
import syncNavigator from './syncNavigator'

store.runSaga(rootSaga)

class AppNavigator extends Component {
  render() {
    return (
      <Provider store={ store }>
        <Navigator
          initialRoute={ { name: 'home' } }
          renderScene={ renderScene }
          style={ styles.content }
          navigationBar={
            <Navigator.NavigationBar
              routeMapper={ {
                LeftButton: renderNull,
                RightButton: renderNull,
                Title: renderTitle
              } }
              style={ {backgroundColor: MKColor.LightBlue} } />
          } />
      </Provider>
    )
  }
}

export default AppNavigator

const renderScene = ({ name }, navigator) => { // eslint-disable-line
  const route = NavigatorStack.getRoute(name)
  const Component = route.component
  syncNavigator.setNavigator(navigator)
  return (
    <Component
      route={ route } />
  )
}

const renderTitle = ({ name }) => { // eslint-disable-line
  const route = NavigatorStack.getRoute(name)

  return (
    <Title style={ styles.title }>
      { route.title || name }
    </Title>
  )
}

const renderNull = () => null

const styles = StyleSheet.create({
  title: {
    textAlign: 'center',
    color: MKColor.White
  },
  content: {
    paddingTop: 60
  }
})
