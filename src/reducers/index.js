import { combineReducers } from 'redux'
import nav from './nav'
import wifiAccount from './wifi-account'

export default combineReducers({
  nav,
  wifiAccount
})
