/**
 * @flow
 */

import { handleActions } from 'redux-actions'
import { SET_ACCOUNT } from '../constants/wifi-account'

const initialState = {
  username: null,
  password: null
}

export default handleActions({
  [SET_ACCOUNT]: (_state, { payload }) => payload
}, initialState)
