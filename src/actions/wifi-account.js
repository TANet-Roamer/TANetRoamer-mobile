/**
 * @flow
 */

import { createAction } from 'redux-actions'
import { SET_ACCOUNT, FETCH_ACCOUNT, STORE_ACCOUNT, CLEAR_ACCOUNT } from '../constants/wifi-account'

export const setAccount = createAction(SET_ACCOUNT)
export const fetchAccount = createAction(FETCH_ACCOUNT)
export const storeAccount = createAction(STORE_ACCOUNT)
export const clearAccount = createAction(CLEAR_ACCOUNT)
