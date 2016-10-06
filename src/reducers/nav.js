/**
 * @flow
 */
import { handleActions } from 'redux-actions'
import { PUSH, POP } from '../constants/nav'

export default handleActions({
  [PUSH]: (_state, { payload }) => payload,
  [POP]: (_state, { payload }) => payload
}, {})
